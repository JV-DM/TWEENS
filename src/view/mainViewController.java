package view;

import data_type.Baraja;
import data_type.Carta;
import data_type.EstrategiaModoJuego;
import data_type.GestorBarajas;
import data_type.ModoTrios;
import data_type.Partida;
import data_type.Perfil;
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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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

    private final static long TIEMPO_PARTIDA = ONE_MINUTE;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label timeLabel;

    @FXML
    private Label puntuationLabel;

    GridPane playGridPane;

    private Partida partida;
    
    GestorBarajas gestor;
    
    private boolean partidaAcabada;

    private Map<Carta, ImageView> cardImageViewMap; 

    private long time;
    EstrategiaModoJuego modoJuego;   
    
    private Perfil perfil;   
    private Baraja baraja;
    
    public mainViewController(Baraja baraja,GestorBarajas gestorBarajas, Perfil perfil){
        this.baraja = baraja;
        this.gestor = gestorBarajas;
        this.perfil = perfil;
        
    }

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
            res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            res = String.format("%dd%02d:%02d:%02d", days, hours, minutes, seconds);
        }
        return res;
    }

    public void pantallaFinPartida(boolean victoria){    
        partidaAcabada = true;       
        Label finPartida = new Label();
        Label estadisticasPartida = new Label();
        Label repetirPartida = new Label();
        
        String textoFinPartida = "  DERROTA";
        String textoRepetirPartida = "   Haz clic para repetir partida";
        
        if(victoria) 
            textoFinPartida = "¡VICTORIA!";
        
        finPartida.setText(""
                +"\n\n                 "
                + textoFinPartida);
        estadisticasPartida.setText(""
                + "     PUNTUACIÓN \n               "
                + partida.getPuntuacion()+ " \n"
                + "TIEMPO DE PARTIDA \n          "
                + formatTime(TIEMPO_PARTIDA - time));
        
        repetirPartida.setText(textoRepetirPartida);

        repetirPartida.setTextFill(Paint.valueOf("white"));
        repetirPartida.setFont(Font.font(20));
        
        estadisticasPartida.setTextFill(Paint.valueOf("white"));
        estadisticasPartida.setFont(Font.font(30));
        
        finPartida.setTextFill(Paint.valueOf("white"));
        finPartida.setFont(Font.font(70));
        
        mainBorderPane.setTop(finPartida);
        mainBorderPane.setCenter(estadisticasPartida);       
        mainBorderPane.setBottom(repetirPartida);
    }

    public Partida getPartida() {
        return partida;
    }

    public void iniciarPartida(Baraja baraja){
        partidaAcabada = false;
        partida = new Partida(baraja,new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        if(this.modoJuego == null) modoJuego = new ModoTrios();
        modoJuego.setPartida(partida);

        partida.setController(this);
        setPuntuacion(0);
        setTime(TIEMPO_PARTIDA);
        reiniciarTablero();     
        updateTimer();
        gridCreation(partida.getBaraja().getCartas(), mainBorderPane.heightProperty(), mainBorderPane.widthProperty());
        partida.startGame();
    }
    
    EventHandler<MouseEvent> reinicarPartida = (MouseEvent event) -> {
        if(partidaAcabada) {            
            iniciarPartida(baraja);
        }
    };
    
    /**
     * Método que actualiza las estadisticas del perfil del jugador
     */
    public void actualizarPerfil(){
        if(partida.isVictoria()) {
            perfil.setVictorias(perfil.getVictorias() + 1);
            perfil.setPuntuacionTotal(perfil.getPuntuacionTotal() + partida.getPuntuacion());
            if(perfil.esPuntuacionMaxima(partida.getPuntuacion())) 
                perfil.setPuntuacionMaxima(partida.getPuntuacion());
        }
        else 
            perfil.setDerrotas(perfil.getDerrotas() + 1);
                    
        try {
            perfil.guardarPerfil();
        } catch (ParserConfigurationException ex) {
        } catch (TransformerException ex) {}
    }

    @FXML
    private void initialize(){

        iniciarPartida(baraja);
        mainBorderPane.addEventFilter(MouseEvent.MOUSE_CLICKED, reinicarPartida);
       
        //aesthetic puntuacion
        puntuationLabel.setFont(Font.font("anton"));
        puntuationLabel.setFont(Font.font(30));
        puntuationLabel.setTextFill(Color.web("#FFFFFF"));
        puntuationLabel.setStyle("-fx-font-weight: bold");   
        
        //aesthetic tiempo
        timeLabel.setFont(Font.font("anton"));
        timeLabel.setFont(Font.font(30));
        timeLabel.setTextFill(Color.web("#FFFFFF"));
        timeLabel.setStyle("-fx-font-weight: bold");
    }
}