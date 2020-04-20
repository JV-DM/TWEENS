package view;

import data_type.Baraja;
import data_type.Carta;
import data_type.GestorBarajas;
import data_type.Partida;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
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
import javafx.stage.Stage;

import java.util.*;



public class mainViewController {

    private final static long ONE_SECOND = 1000;
    private final static long SECONDS = 60;

    private final static long ONE_MINUTE = ONE_SECOND * 60;
    private final static long MINUTES = 60;

    private final static long ONE_HOUR = ONE_MINUTE * 60;
    private final static long HOURS = 24;

    private final static long ONE_DAY = ONE_HOUR * 24;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Label timeLabel;

    @FXML
    private Label puntuationLabel;

    GridPane playGridPane;

    private Partida partida;

    Timer t;

    private Map<Carta, ImageView> cardImageViewMap = new HashMap<>();

    private void gridCreation(List<Carta> cardList, ReadOnlyDoubleProperty height, ReadOnlyDoubleProperty width){
        int cardNumber = 0;

        playGridPane = new GridPane();
        playGridPane.setPadding(new Insets(10));

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 5; j++){
                Image image = partida.getBaraja().GetCaraPosterior().GetImagen();
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
        playGridPane.setAlignment(Pos.CENTER);
        mainBorderPane.setCenter(playGridPane);
        mainBorderPane.setBackground(new Background(new BackgroundImage(
                partida.getBackground(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }

    private EventHandler clickOnCardEventHandler(Carta carta, ImageView imageView) {
        return event ->{
            partida.pickCard(carta);
            //hago un nuevo hilo para que no lagee la interfaz
            new Thread(()->
            {
                imageView.setImage(carta.getImagen());

                if(partida.getSelectedCards().isEmpty()){
                    playGridPane.setDisable(false);
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (Carta cardFromMap : cardImageViewMap.keySet())
                        if(!cardFromMap.isFound())
                            cardImageViewMap.get(cardFromMap).setImage(partida.getBaraja().GetCaraPosterior().GetImagen());
                    playGridPane.setDisable(false);
                }
            }).start();
        };
    }

    public void setPuntuacion(int puntuacion) {
        puntuationLabel.setText("PUNTUACION = " + puntuacion);
    }

    private void updateTimer(){
        partida.getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    long seconds = partida.getTimeLasted().toMillis();

                    timeLabel.setText(formatTime(seconds));

                    if(!partida.isRunning())
                        partida.stopTimer();
                });
            }
        },0,1000);
    }

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

    @FXML
    private void initialize(){
        //Esta linea se deberá eliminar posteriormente
        GestorBarajas gestor = new GestorBarajas();
        gestor.CargarBarajaPorDefecto();
        Partida p = new Partida(gestor.GetBarajaPorDefecto(),new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        partida = p;
        List<Carta> cardList = p.getBaraja().GetCartas();
        partida.startGame();
        partida.setController(this);
        updateTimer();
        setPuntuacion(0);
        gridCreation(cardList, mainBorderPane.heightProperty(), mainBorderPane.widthProperty());
    }
}
