/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

import javax.xml.parsers.ParserConfigurationException;

import javafx.stage.Stage;
import org.xml.sax.SAXException;


public class NivelesViewController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button bt_nivel1;

    @FXML
    private Button bt_nivel2;

    @FXML
    private Button bt_nivel3;

    private Perfil perfil;
    private GestorBarajas gestorBarajas;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setPrefSize(829, 543);
        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true))));


    }

    /*NivelesViewController(Perfil perfil, GestorBarajas gestorBarajas) {
        this.perfil = perfil;
        this.gestorBarajas = gestorBarajas;
    }*/


    public void setPerfil(Perfil p) {this.perfil = p; }

    @FXML
    private void clickNivele1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        controller.setTiempoPartida(40000);
        GestorBarajas gestor = this.setUp(new ModoJuegoNormal(),controller);
        controller.iniciarPartida(gestor.getBarajaPorDefecto());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void clickNivele2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        GestorBarajas gestor = this.setUp(new ModoJuegoNormal(),controller);
        controller.iniciarPartida(gestor.getBarajaPorDefecto());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickNivele3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        controller.setIntentosPartida(5);
        GestorBarajas gestor = this.setUp(new ModoJuegoNormal(),controller);
        controller.iniciarPartida(gestor.getBarajaPorDefecto());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private GestorBarajas setUp(EstrategiaModoJuego estrategia, mainViewController controller){
        GestorBarajas gestor = new GestorBarajas();
        controller.modoJuego = estrategia;
        controller.modoJuego.setPartida(controller.getPartida());
        controller.gestor = gestor;
        controller.gestor.cargarBarajas();
        controller.gestor.cargarBarajaPorDefecto();
        controller.setPerfil(this.perfil);
        return gestor;
    }


}