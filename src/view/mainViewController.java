package view;

import data_type.*;
import data_type.Puntuacion.Decorador;
import data_type.Puntuacion.Puntuacion;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;



public class mainViewController {

    private final static long ONE_SECOND = 1000;
    private final static long SECONDS = 60;

    private final static long ONE_MINUTE = ONE_SECOND * 60;
    private final static long MINUTES = 60;

    private final static long ONE_HOUR = ONE_MINUTE * 60;
    private final static long HOURS = 24;

    private final static long ONE_DAY = ONE_HOUR * 24;

    private long TIEMPO_PARTIDA = 5;

    private final static int INTENTOS = 10;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label timeLabel;

    @FXML
    private Label puntuationLabel;

    @FXML
    private Label  intentosLabel;

    GridPane playGridPane;

    private Partida partida;
    
    GestorBarajas gestor;
    
    private boolean partidaAcabada;

    private Map<Carta, ImageView> cardImageViewMap; 

    private long time;
    EstrategiaSeleccion modoJuego;
    
    private Perfil perfil;

    private Baraja baraja;
    private Ranking ranking;
    private Historial historial;

    private int intentos = 10;
    private boolean isLevel = false;
    private int lvl = 1;
     /**
     * Crea un gridPane con las cartas (mostrando la parte de atrás de la carta) con su posición random
     * (los parametros height y width en principio se usarán en los próximos sprints, creo)
     * @param cards lista de cartas de la baraja
     * @param height numero de columnas
     * @param width número de filas
     **/
    private void gridCreation(List<Carta> cards, ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width){
        int cardNumber = 0;
        playGridPane = new GridPane();
        cardImageViewMap = new HashMap<>();
        // aesthetics
        playGridPane.setPadding(new Insets(10));
        List<Carta> cardList = new ArrayList<>(cards);
        Collections.shuffle(cardList);
        for(int i = 0; i < (int) Math.sqrt(cardList.size()); i++){
            for(int j = 0; j < (int) Math.sqrt(cardList.size()) +1; j++){
                Image image = partida.getBaraja().getCaraPosterior().getImagen();
                ImageView imageView = new ImageView(image);
                imageView.setPreserveRatio(false);
                imageView.fitWidthProperty().bind(width.divide(9));
                imageView.fitHeightProperty().bind(height.divide(9*0.7));
                cardImageViewMap.put(cardList.get(cardNumber), imageView);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, clickOnCardEventHandler(cardList.get(cardNumber), imageView));
                playGridPane.add(imageView,j,i);
                cardNumber += 1;
            }
        }
        //more aesthetics
        playGridPane.setAlignment(Pos.CENTER);
        mainBorderPane.setCenter(playGridPane);
        mainBorderPane.setBackground(new Background(new BackgroundImage(
                partida.getBackground(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }

    /**
     * Manejador de eventos para TODAS las cartas
     * @param carta carta que se supone que hay debajo de cada imagen de cara posterior
     * @param imageView objeto de la imagen que se ha pulsado
     * @return
     */
    private EventHandler clickOnCardEventHandler(Carta carta, ImageView imageView) {
        return event ->{
            //patron estrategia
            modoJuego.pickCard(carta);
            //hago un nuevo hilo para que no lagee la interfaz
            new Thread(()->
            {
                imageView.setImage(carta.getImagen());

                if(partida.getSelectedCards().isEmpty()){
                    playGridPane.setDisable(true);
                    try {
                        Thread.sleep(400);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (Carta cardFromMap : cardImageViewMap.keySet())
                        if(!cardFromMap.isFound())
                            cardImageViewMap.get(cardFromMap).setImage(partida.getBaraja().getCaraPosterior().getImagen());
                    playGridPane.setDisable(false);
                }
            }).start();
        };
    }

    /**
     * Cambia el valor de la puntuación
     * @param puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        puntuationLabel.setText("PUNTUACIÓN " + puntuacion);
    }
   
    /**
     * Cambia el valor del tiempo
     * @param tiempoPartida 
     */
    public void setTime(long tiempoPartida){
        time =  tiempoPartida;       
    }
    
    /**
     * Reestablece la pantalla para jugar una partida
     */
    public void reiniciarTablero(){
        mainBorderPane.setCenter(playGridPane);
        mainBorderPane.setTop(timeLabel);
        mainBorderPane.setBottom(puntuationLabel);
        mainBorderPane.setPrefSize(1024, 768);
    }

    /**
     * Método que actualiza el contador de tiempo a cada segundo
     */
    private void updateTimer(){
        //partida.restartTimer();
        partida.getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(!partidaAcabada){
                        time -= ONE_SECOND;
                        if(time >= 0)
                            timeLabel.setText(formatTime(time));
                        if(time <= 0){
                            partida.stopTimer();
                        }
                    }
                    if(partidaAcabada)
                        this.cancel();
                });
            }
        },0,1000);
    }

    /**
     * Método para poner formato al label del tiempo
     * @param duration tiempo que ha pasado desde que ha empezado la partida
     * @return
     */
    public static String formatTime(long duration){
        String res;
        duration /= ONE_SECOND;
        int seconds = (int) (duration % SECONDS);
        duration /= SECONDS;
        int minutes = (int) (duration % MINUTES);
        duration /= MINUTES;
        int hours = (int) (duration % HOURS);
        int days = (int) (duration / HOURS);
        if (days == 0) {
            res = String.format("%02d:%02d", minutes, seconds);
        } else {
            res = String.format("%02d:%02d", minutes, seconds);
        }
        return res;
    }

    public void pantallaFinPartida(boolean victoria){
        partidaAcabada = true;
        VBox vBox;
        Label finPartida = new Label();
        Label timeText = new Label();
        Label puntuacionLabel = new Label();
        Label repetirPartida = new Label();
        Label puntuacion = new Label();
        Label tiempo = new Label();
        String textoFinPartida = "DERROTA";
        String textoRepetirPartida = "Haz clic para repetir partida";

        if(victoria)
            textoFinPartida = "¡VICTORIA!";

        finPartida.setText(textoFinPartida);
        timeText.setText("TIEMPO");
        puntuacionLabel.setText("PUNTUACIÓN");
        puntuacion.setText(Integer.toString(partida.getPuntuacion().getPuntos()));
        tiempo.setText(formatTime(TIEMPO_PARTIDA - time));
        repetirPartida.setText(textoRepetirPartida);

        repetirPartida.setTextFill(Paint.valueOf("white"));
        repetirPartida.setFont(Font.font(20));

        timeText.setTextFill(Paint.valueOf("white"));
        timeText.setStyle("-fx-font-weight: bold");
        timeText.setFont(Font.font(30));

        finPartida.setTextFill(Paint.valueOf("white"));
        finPartida.setStyle("-fx-font-weight: bold");
        finPartida.setFont(Font.font(70));

        tiempo.setTextFill(Paint.valueOf("white"));
        tiempo.setStyle("-fx-font-weight: bold");
        tiempo.setFont(Font.font(30));

        timeText.setTextFill(Paint.valueOf("white"));
        timeText.setStyle("-fx-font-weight: bold");
        timeText.setFont(Font.font(30));

        puntuacion.setTextFill(Paint.valueOf("white"));
        puntuacion.setFont(Font.font(30));

        puntuacionLabel.setTextFill(Paint.valueOf("white"));
        puntuacionLabel.setStyle("-fx-font-weight: bold");
        puntuacionLabel.setFont(Font.font(30));

        partida.restartTimer();
        vBox = new VBox(20, finPartida, puntuacionLabel, puntuacion, timeText, tiempo);
        mainBorderPane.setCenter(vBox);
        mainBorderPane.setBottom(repetirPartida);
        mainBorderPane.setAlignment(vBox, Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        mainBorderPane.setAlignment(repetirPartida, Pos.BOTTOM_LEFT);
    }


    public Partida getPartida() {
        return partida;
    }

    public void iniciarPartida(Baraja baraja){
        partidaAcabada = false;
        partida = Partida.getInstance(baraja,new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        partida.setBaraja(baraja);
        partida.setBackground(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
       // gridCreation(partida.getBaraja().getCartas(), mainBorderPane.heightProperty(), mainBorderPane.widthProperty());
        if(this.modoJuego == null) modoJuego = new SeleccionTrios();
        modoJuego.setPartida(partida);
        playGridPane = new GridPane();
        partida.setController(this);
        partida.setPuntuacion(new Puntuacion());
        intentos = INTENTOS;
        if(isLevel && lvl == 3)
            intentos = 5;
        setPuntuacion(0);
        partida.setErrorCounter(0);
        partida.setIntentos(intentos);
        partida.setNivel(isLevel);
        partida.setLevel(lvl);
        setIntentos(intentos);
        setTime(TIEMPO_PARTIDA);
        reiniciarTablero();     
        updateTimer();
        gridCreation(partida.getBaraja().getCartas(), mainBorderPane.heightProperty(), mainBorderPane.widthProperty());
        partida.startGame();
    }
    
    EventHandler<MouseEvent> reinicarPartida = (MouseEvent event) -> {
        if(partidaAcabada) {            
            iniciarPartida(partida.getBaraja());
        }
    };
    
    /**
     * Método que actualiza las estadisticas del perfil del jugador
     */
    public void actualizarPerfil() {
        if (partida.isNivel() == false) {
            if (partida.isVictoria()) {
                perfil.setVictorias(perfil.getVictorias() + 1);
                perfil.setPuntuacionTotal(perfil.getPuntuacionTotal() + partida.getPuntuacion().getPuntos());
                if (perfil.esPuntuacionMaxima(partida.getPuntuacion().getPuntos()))
                    perfil.setPuntuacionMaxima(partida.getPuntuacion().getPuntos());
            } else
                perfil.setDerrotas(perfil.getDerrotas() + 1);

        } else if (partida.isNivel() && partida.isVictoria() && partida.getLevel() <= 3) {
            perfil.setNivelActual(partida.getLevel() + 1);
        }
        if(!partida.isNivel()) {
            ranking.actualizarRanking(partida.getPuntuacion().getPuntos());
            Date fechaActual = new Date();
            historial.actualizarFecha(new SimpleDateFormat("dd-MM-yyyy").format(fechaActual));
        }
        try {
            perfil.guardarPerfil();
            if(!partida.isNivel()){
                ranking.guardarRanking();
                historial.guardarHistorial();
            }
        } catch (ParserConfigurationException ex) {
        } catch (TransformerException ex) {
        }

    }
    @FXML
    private void initialize(){
        //iniciarPartida(baraja);
       // gestor = new GestorBarajas();
//        this.baraja = gestor.getBarajaPorDefecto();
        mainBorderPane.addEventFilter(MouseEvent.MOUSE_CLICKED, reinicarPartida);
       
        //aesthetic puntuacion
        puntuationLabel.setFont(Font.font("anton"));
        puntuationLabel.setFont(Font.font(30));
        puntuationLabel.setTextFill(Color.web("#FFFFFF"));
        puntuationLabel.setStyle("-fx-font-weight: bold");

        intentosLabel.setFont(Font.font("anton"));
        intentosLabel.setFont(Font.font(30));
        intentosLabel.setTextFill(Color.web("#FFFFFF"));
        intentosLabel.setStyle("-fx-font-weight: bold");

        //aesthetic tiempo
        timeLabel.setFont(Font.font("anton"));
        timeLabel.setFont(Font.font(30));
        timeLabel.setTextFill(Color.web("#FFFFFF"));
        timeLabel.setStyle("-fx-font-weight: bold");
    }
    public void setTiempoPartida(long time){ TIEMPO_PARTIDA = time;}
    public void setPerfil(Perfil perfil){ this.perfil = perfil; }
    public void setIntentosPartida(int i){ intentos = i; }
    public void setNivelPartida (boolean isLevel){ this.isLevel = isLevel; }
    public void setLevelPartida (int n){ lvl = n; }
    public void setRanking(Ranking ranking){this.ranking = ranking;}
    public void setHistorial(Historial historial){this.historial = historial;}

    /**
     * Cambia el valor de la puntuación
     * @param intentos
     */
    public void setIntentos(int intentos) { intentosLabel.setText("INTENTOS RESTANTES " +  intentos ); }
}