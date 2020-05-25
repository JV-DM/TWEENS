/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import data_type.*;
import data_type.Puntuacion.DecoradorLogroFinPartidaRapido;
import data_type.Puntuacion.Puntuacion;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.input.MouseEvent;

import java.text.SimpleDateFormat;
import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class mainViewController implements Initializable {

    private final static long ONE_SECOND = 1000;
    private final static long SECONDS = 60;

    private final static long ONE_MINUTE = ONE_SECOND * 60;
    private final static long MINUTES = 60;

    private final static long ONE_HOUR = ONE_MINUTE * 60;
    private final static long HOURS = 24;

    private final static long ONE_DAY = ONE_HOUR * 24;

    private long TIEMPO_PARTIDA = ONE_MINUTE;

    private final static int INTENTOS = 10;

    public ImageView imageViewCarta;
    public Label labelCategoria;

    GridPane playGridPane;
    private boolean esDinamico = false;
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
    private GestorDesafios gestorDesafios;

    private int intentos = 10;
    private boolean isLevel = false;
    private int lvl = 1;



    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Label timeLabel;
    @FXML
    private Label intentosLabel;
    @FXML
    private Label puntuationLabel;
    @FXML
    private HBox topBorderPane;
    @FXML
    private HBox bottomBorderPane;
    @FXML
    private ImageView replay;
    @FXML
    private ImageView pause;
    @FXML
    private ImageView sound;
    @FXML
    private ImageView menu;

    private AjustesPartida ajustes = new AjustesPartida();

    private boolean partidaPausada = false;
    private boolean partidaSonido = true;
    @FXML
    private ImageView salirPartida;
    private String puntuacionTexto;
    private String intentosTexto;
    private IdiomaProperty idioma;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        replay.setVisible(false);
        pause.setVisible(false);
        sound.setVisible(false);
        salirPartida.setVisible(false);
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
            //patron estrategia
            modoJuego.pickCard(carta,this.partida);
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
                    if (partida.esDinamico) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ObservableList<Node> nodos = playGridPane.getChildren();
                                ArrayList<ArrayList<Node>> filas = new ArrayList<>();

                                int columnCount = playGridPane.getColumnCount();

                                for (int i = 0; i < nodos.size(); i += columnCount) {
                                    ArrayList<Node> temp = new ArrayList<>();
                                    for (int j = i; j < columnCount + i; j++) {
                                        temp.add(nodos.get(j));
                                    }
                                    filas.add(temp);
                                }
                                for (ArrayList<Node> fila : filas) {
                                    int ultimo = fila.size() - 1;
                                    for (int i = 0; i < fila.size(); i++) {
                                        if (i == ultimo) {
                                            swap(fila.get(0), fila.get(ultimo));
                                        } else if (i != 0) swap(fila.get(i), fila.get(i + 1));

                                    }
                                }


                            }
                        });

                    }

                    if (modoJuego instanceof SeleccionModoCarta && partidaAcabada == false)
                        imageViewCarta.setImage(partida.cartaABuscar(partida.getBaraja()).getImagen());
                    else if (partidaAcabada)  mainBorderPane.getChildren().remove(imageViewCarta);;
                    playGridPane.setDisable(false);
                }
            }).start();
        };
    }

    public void swap(Node n1, Node n2) {
        Integer temp = playGridPane.getRowIndex(n1);
        playGridPane.setRowIndex(n1, playGridPane.getRowIndex(n2));
        playGridPane.setRowIndex(n2, temp);

        temp = playGridPane.getColumnIndex(n1);
        playGridPane.setColumnIndex(n1, playGridPane.getColumnIndex(n2));
        playGridPane.setColumnIndex(n2, temp);
    }

    /**
     * Cambia el valor de la puntuación
     * @param puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        puntuationLabel.setText(puntuacionTexto + " " + puntuacion);
    }

    public void setElements(){
        idioma = new IdiomaProperty(perfil.getIdioma());
        puntuacionTexto = idioma.getProp().getProperty("Puntuacion");
        intentosTexto = idioma.getProp().getProperty("Intentos");
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
        mainBorderPane.setTop(topBorderPane);
        mainBorderPane.setBottom(bottomBorderPane);
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
                    if(!partidaAcabada && !partidaPausada){
                        time -= ONE_SECOND;
                        if(time >= 0)
                            timeLabel.setText(formatTime(time));
                        if(time <= 0){
                            try {
                                partida.stopTimer();
                            } catch (ParserConfigurationException ex) {} catch (TransformerException ex) {}
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

    public void comprobarDesafio() throws ParserConfigurationException, TransformerException{
        if(gestorDesafios.getDesafioEnCurso() != null){
                gestorDesafios.comprobarDesafioEnCurso(partida.getErrorCounter(), partida.getPuntuacion().getPuntos(), (int) (TIEMPO_PARTIDA - time)/1000);
                gestorDesafios.guardarDesafios();
                mensajeDesafioConseguido();
            }
    }

    public void pantallaFinPartida(boolean victoria){
        if(TIEMPO_PARTIDA - time == 30 * ONE_SECOND )
            partida.setPuntuacion(new DecoradorLogroFinPartidaRapido(partida.getPuntuacion()));

        partidaAcabada = true;
        VBox vBox;
        Label finPartida = new Label();
        Label timeText = new Label();
        Label puntuacionLabel = new Label();
        Label repetirPartida = new Label();
        Label puntuacion = new Label();
        Label tiempo = new Label();
        String textoFinPartida = idioma.getProp().getProperty("Derrota");;
        String textoRepetirPartida = idioma.getProp().getProperty("Repetir_partida");

        if(victoria){
            textoFinPartida = idioma.getProp().getProperty("Victoria");;
        }

        finPartida.setText(textoFinPartida);
        timeText.setText(idioma.getProp().getProperty("Tiempo"));
        puntuacionLabel.setText(idioma.getProp().getProperty("Puntuacion"));
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
        mainBorderPane.getChildren().remove(imageViewCarta);
    }


    public Partida getPartida() {
        return partida;
    }

    public void iniciarPartida(Baraja baraja){
        this.baraja = baraja;
        partidaAcabada = false;
        partida = Partida.getInstance(baraja,new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        partida.setBaraja(baraja);
        partida.setBackground(new Image(perfil.getRutaTableroPorDefecto()));
        if(this.modoJuego == null) modoJuego = new SeleccionTrios();
//        modoJuego.setPartida(partida);
        playGridPane = new GridPane();
        partida.setController(this);
        partida.setPuntuacion(new Puntuacion());
        intentos = INTENTOS;
        if(isLevel && lvl == 3)
            intentos = 5;
        setPuntuacion(0);
        partida.esPrimera = true;
        partida.setErrorCounter(0);
        partida.setIntentos(intentos);
        partida.setNivel(isLevel);
        partida.setLevel(lvl);
        setIntentos(intentos);
        setTime(TIEMPO_PARTIDA);
        partida.setDinamico(esDinamico);
        reiniciarTablero();     
        updateTimer();
        gridCreation(partida.getBaraja().getCartas(), mainBorderPane.heightProperty(), mainBorderPane.widthProperty());
        partida.startGame();
        if(modoJuego instanceof SeleccionModoCarta) {
            cartaASeleccionar();
            Collections.shuffle(partida.getBaraja().getCartas());
            imageViewCarta.setImage(partida.cartaABuscar(partida.getBaraja()).getImagen());
            imageViewCarta.setFitWidth(100);
            imageViewCarta.setFitHeight(100);
        }
        if (modoJuego instanceof SeleccionCategoria) {
            categoriaASeleccionar();
            Collections.shuffle(partida.getBaraja().getCartas());
            partida.setCategoria(partida.categoriaABuscar(partida.getBaraja()));
            labelCategoria.setText(partida.getCategoria());
        }

        imageViewCarta.setDisable(true);
    }

    public void cartaASeleccionar() {
        imageViewCarta = new ImageView();
        mainBorderPane.setRight(imageViewCarta);
        Insets insets = new Insets(0,10,0,0);
        mainBorderPane.setPadding(insets);
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

    public void setTiempoPartida(long time){ TIEMPO_PARTIDA = time;}
    public void setPerfil(Perfil perfil){ this.perfil = perfil; setElements(); }
    public void setIntentosPartida(int i){ intentos = i; }
    public void setNivelPartida (boolean isLevel){ this.isLevel = isLevel; }
    public void setLevelPartida (int n){ lvl = n; }
    public void setRanking(Ranking ranking){this.ranking = ranking;}
    public void setHistorial(Historial historial){this.historial = historial;}
    public void setGestorDesafios(GestorDesafios gestorDesafios){this.gestorDesafios = gestorDesafios;}

    /**
     * Cambia el valor de la puntuación
     * @param intentos
     */
    public void setIntentos(int intentos) { intentosLabel.setText(intentosTexto + " " +  intentos ); }

    public void mensajeDesafioConseguido(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("¡¡¡Enhorabuena!!!");
        alert.setContentText("Has conseguido el desafio: " + gestorDesafios.getDesafioEnCurso().getNombre());
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }

    @FXML
    private void replayOnClick(MouseEvent event) throws ParserConfigurationException, TransformerException {
        partida.setSonido(false);
        partida.stopTimer();
        partida.setSonido(true);
        iniciarPartida(baraja);
    }

    @FXML
    private void pauseOnClick(MouseEvent event) throws InterruptedException {
        if(!partidaPausada){
            partidaPausada = true;
            playGridPane.setDisable(true);
            pause.setImage(new Image(ajustes.getPlay()));
        }
        else {
            partidaPausada = false;
            playGridPane.setDisable(false);
            pause.setImage(new Image(ajustes.getPausa()));
        }

    }

    @FXML
    private void soundOnClick(MouseEvent event) {
        if(!partidaSonido){
            partidaSonido = true;
            partida.setSonido(true);
            sound.setImage(new Image(ajustes.getSonido()));
        }
        else {
            partidaSonido = false;
            partida.setSonido(false);
            sound.setImage(new Image(ajustes.getSinSonido()));
        }
    }

    @FXML
    private void menuOnClick(MouseEvent event) {
            replay.setVisible(!replay.visibleProperty().getValue());
            pause.setVisible(!pause.visibleProperty().getValue());
            sound.setVisible(!sound.visibleProperty().getValue());
            salirPartida.setVisible(!salirPartida.visibleProperty().getValue());

    }

    @FXML
    private void salirPartidaOnClick(MouseEvent event) throws IOException, ParserConfigurationException, TransformerException {
        partida.setSonido(false);
        if(!partida.isFinished())
            partida.stopTimer();
        partida.setSonido(true);
        Parent root = FXMLLoader.load(getClass().getResource("MenuView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void setLabelCategoria(String categoria){
        labelCategoria.setText(categoria);
    }

    public void categoriaASeleccionar(){
        labelCategoria = new Label();
        mainBorderPane.setRight(labelCategoria);
        Insets insets = new Insets(0,10,0,0);
        mainBorderPane.setPadding(insets);
    }
    public void setPartidaDinamica(boolean bol) {
        this.esDinamico = bol;
    }



}

