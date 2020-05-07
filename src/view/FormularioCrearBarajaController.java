/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.GestorArchivos;
import data_type.GestorBarajas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FormularioCrearBarajaController implements Initializable {

    @FXML
    private TextField nombreBaraja;
    @FXML
    private TextField tematicaBaraja;
    private final String RUTA_BACKGROUND = "imagenes/ImagenesBackground/fondo-verde.jpg";
    private GestorBarajas gestorBarajas;
    private MenuGestorBarajasViewController controller; 
    @FXML
    private BorderPane borderPane;
    
    public FormularioCrearBarajaController(GestorBarajas gestorBarajas, MenuGestorBarajasViewController controller){
        this.gestorBarajas = gestorBarajas; 
        this.controller = controller;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }    

    public void mensajeError(String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    
    @FXML
    private void aceptarOnClick(ActionEvent event) {
        String nombre = nombreBaraja.getText();
        String tematica = tematicaBaraja.getText();
        if(nombre.equals("")) mensajeError("El campo del nombre no puede estar vacio");
        else if(gestorBarajas.buscarBaraja(nombre) != null) mensajeError("El nombre de la baraja ya existe");
        else if(tematica.equals("")) mensajeError("El campo de la temática no puede estar vacio");
        else {
            controller.barajaCreada(new Baraja(nombre,tematica,gestorBarajas.getCaraPosterior()));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            }
        }
    }
 
