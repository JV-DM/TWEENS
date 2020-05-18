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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class NivelesViewController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView imageNivel1;

    @FXML
    private Button bt_nivel1;

    @FXML
    private Label labelNivel1;

    @FXML
    private ImageView imageNivel2;

    @FXML
    private Button bt_nivel2;

    @FXML
    private Label labelNivel2;

    @FXML
    private ImageView imageNivel3;

    @FXML
    private Button bt_nivel3;

    @FXML
    private Label labelNivel3;

    private Perfil perfil;
    private GestorBarajas gestorBarajas;
    private GestorDesafios gestorDesafios;
    Baraja baraja;


    /**
     * Initializes the controller class.
     */

    /*NivelesViewController(Perfil perfil, GestorBarajas gestorBarajas) {
        this.perfil = perfil;
        this.gestorBarajas = gestorBarajas;
    }*/

    public void setPerfil(Perfil p) {this.perfil = p; }
    public void setGestorBarajas(GestorBarajas gestorBarajas) {this.gestorBarajas = gestorBarajas;}
    public void setBaraja(Baraja baraja){this.baraja = baraja;}
    public void setGestorDesafios(GestorDesafios gestorDesafios){this.gestorDesafios = gestorDesafios;}

    @FXML
    private void clickNivele1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        controller.setTiempoPartida(40000);
        controller.setNivelPartida(true);
        controller.setLevelPartida(1);
        this.setUp(new SeleccionNormal(),controller);
        controller.iniciarPartida(baraja);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Nivel 1");
        stage.show();
    }


    @FXML
    private void clickNivele2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        controller.setNivelPartida(true);
        controller.setLevelPartida(2);
        this.setUp(new SeleccionNormal(),controller);
        controller.iniciarPartida(baraja);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Nivel 2");
        stage.show();
    }

    @FXML
    private void clickNivele3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        controller.setPerfil(this.perfil);
        controller.setIntentosPartida(5);
        controller.setNivelPartida(true);
        controller.setLevelPartida(3);
        this.setUp(new SeleccionNormal(),controller);
        controller.iniciarPartida(baraja);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Nivel 3");
        stage.show();
    }



    private void setUp(EstrategiaSeleccion estrategia, mainViewController controller){
        GestorBarajas gestor = new GestorBarajas();
        controller.modoJuego = estrategia;
        controller.modoJuego.setPartida(controller.getPartida());
        controller.gestor = gestor;
        controller.gestor.cargarBarajas();
        controller.gestor.cargarBarajaPorDefecto();
        baraja = gestor.getBarajaPorDefecto();
        controller.setPerfil(this.perfil);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setPrefSize(829, 543);
        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true, true, false, true))));
        labelNivel1.setText("Tiempo reducido a 40 segundos");
        labelNivel2.setText("Puntuación mínima de 60");
        labelNivel3.setText("5 intentos antes de perder");

        labelNivel1.setFont(Font.font("anton"));
        labelNivel1.setFont(Font.font(10));
        labelNivel1.setTextFill(Color.web("#FFFFFF"));
        labelNivel1.setStyle("-fx-font-weight: bold");

        labelNivel2.setFont(Font.font("anton"));
        labelNivel2.setFont(Font.font(10));
        labelNivel2.setTextFill(Color.web("#FFFFFF"));
        labelNivel2.setStyle("-fx-font-weight: bold");

        labelNivel3.setFont(Font.font("anton"));
        labelNivel3.setFont(Font.font(10));
        labelNivel3.setTextFill(Color.web("#FFFFFF"));
        labelNivel3.setStyle("-fx-font-weight: bold");

        imageNivel1.setImage(new Image("imagenes/ImagenesNivel/nivel1.png"));
        imageNivel2.setImage(new Image("imagenes/ImagenesNivel/candado.png"));
        imageNivel3.setImage(new Image("imagenes/ImagenesNivel/candado.png"));
        Platform.runLater(() ->{
            if(perfil.getNivelActual() == 1){
                bt_nivel2.setDisable(true);
                bt_nivel3.setDisable(true);
                labelNivel3.setVisible(false);
                labelNivel2.setVisible(false);
            }else if(perfil.getNivelActual() == 2){
                bt_nivel3.setDisable(true);
                labelNivel3.setVisible(false);
                imageNivel2.setImage(new Image("imagenes/ImagenesNivel/nivel2.png"));
            }else if(perfil.getNivelActual() >= 3){
                imageNivel3.setImage(new Image("imagenes/ImagenesNivel/nivel3.png"));
                imageNivel2.setImage(new Image("imagenes/ImagenesNivel/nivel2.png"));
            }
        });

    }

    @FXML
    private void atrasOnClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        
    }
}