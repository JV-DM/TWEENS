package view.controllers;

import data_structures.Baraja;
import data_structures.Carta;
import data_structures.Imagen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class mainViewController {

    @FXML
    public Label puntuationLabel;
    @FXML
    public BorderPane mainBorderPane;
    @FXML
    public Label timeLabel;

    private GridPane playGridPane;
    private Baraja baraja;
    private Imagen board;
    private Map<Carta, ImageView> cardImageViewMap = new HashMap<>();
    private void gridCreation(){
        int cardNumber = 0;

        playGridPane = new GridPane();
        playGridPane.setPadding(new Insets(10));

        for(int i = 0; i < board.GetImage().getHeight(); i++){
            for(int j = 0; j < board.GetImage().getWidth(); j++){
                ImageView imageView = new ImageView(baraja.GetCaraPosterior().GetImagen().GetImage());
                //pensando como ajustar esto
                //imageView.fitWidthProperty().bind(baraja.GetCartas().get(0).GetImagen().GetImage().getHeight().divide(1*(board.getBoardConfiguration().getHeight()+board.GetImage().getWidth())));
                //imageView.fitHeightProperty().bind(heightProperty.divide((board.getBoardConfiguration().getHeight()+board.GetImage().getWidth())*0.7));

                cardImageViewMap.put(baraja.GetCartas().get(cardNumber),imageView);
                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, clickOnCardEventHandler(baraja.GetCartas().get(cardNumber), imageView));


                GridPane.setMargin(imageView, new Insets(10));
                playGridPane.add(imageView, j, i);
                cardNumber += 1;
            }
        }
        playGridPane.setAlignment(Pos.CENTER);
        mainBorderPane.setCenter(playGridPane);
        mainBorderPane.setBackground(new Background(new BackgroundImage(baraja.GetCaraPosterior().GetImagen().GetImage(),BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }
    private EventHandler clickOnCardEventHandler(Carta card, ImageView imageView) {
        return null;
    }
}
