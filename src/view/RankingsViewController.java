/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.IdiomaProperty;
import data_type.Perfil;
import data_type.Ranking;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class RankingsViewController implements Initializable {

    private Perfil perfil;
    @FXML
    private Label perfil1;
    @FXML
    private Label perfil2;
    @FXML
    private Label perfil3;
    @FXML
    private Label puntuacion1;
    @FXML
    private Label puntuacion2;
    @FXML
    private Label puntuacion3;
    @FXML
    private BorderPane borderPane;

    private Ranking ranking;
    @FXML
    private Label rankingLabel;
    @FXML
    private Label posicionLabel;
    @FXML
    private Label puntuacionLabel;
    private IdiomaProperty idioma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setElements();
        puntuacion1.setText(String.valueOf(ranking.getPrimero()));
        puntuacion2.setText(String.valueOf(ranking.getSegundo()));
        puntuacion3.setText(String.valueOf(ranking.getTercero()));
        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));

    }

    public void setElements(){
        idioma = new IdiomaProperty(perfil.getIdioma());
        rankingLabel.setText(idioma.getProp().getProperty("Ranking"));
        posicionLabel.setText(idioma.getProp().getProperty("Posicion"));
        puntuacionLabel.setText(idioma.getProp().getProperty("Puntuacion"));
    }
    
    public RankingsViewController(Ranking ranking, Perfil perfil){
        this.ranking = ranking;
        this.perfil = perfil;
    }


}