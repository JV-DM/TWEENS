/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Historial;
import data_type.IdiomaProperty;
import data_type.Perfil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
public class HistorialViewController implements Initializable {

    @FXML
    private Label perfil1;
    @FXML
    private Label perfil2;
    @FXML
    private Label perfil3;
    @FXML
    private Label perfil4;
    @FXML
    private Label perfil5;
    @FXML
    private Label fecha1;
    @FXML
    private Label fecha2;
    @FXML
    private Label fecha3;
    @FXML
    private Label fecha4;
    @FXML
    private Label fecha5;
    @FXML
    private BorderPane borderPane;

    private Historial historial;
    @FXML
    private Label partidaLabel;
    @FXML
    private Label fechaLabel;
    private Perfil perfil;
    private IdiomaProperty idioma;
    @FXML
    private Label historialLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setElements();
        if(historial.getFecha1() != null){
            fecha1.setText(String.valueOf(historial.getFecha1()));
        }

        if(historial.getFecha2() != null){
            fecha2.setText(String.valueOf(historial.getFecha2()));
        }

        if(historial.getFecha3() != null){
            fecha3.setText(String.valueOf(historial.getFecha3()));
        }

        if(historial.getFecha4() != null){
            fecha4.setText(String.valueOf(historial.getFecha4()));
        }

        if(historial.getFecha5() != null){
            fecha5.setText(String.valueOf(historial.getFecha5()));
        }

        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }

    public void setElements(){
        idioma = new IdiomaProperty(perfil.getIdioma());
        partidaLabel.setText(idioma.getProp().getProperty("Partida"));
        fechaLabel.setText(idioma.getProp().getProperty("Fecha"));
        historialLabel.setText(idioma.getProp().getProperty("Historial"));
    }
    
    public HistorialViewController(Historial historial,Perfil perfil){
        this.historial = historial;
        this.perfil = perfil;
    }

}