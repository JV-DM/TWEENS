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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class MenuViewController implements Initializable {

    @FXML
    private BorderPane menuBorderPane;
    
    private Perfil perfil;
    GestorBarajas gestorBarajas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestorBarajas = new GestorBarajas();
        gestorBarajas.cargarBarajas();
        gestorBarajas.cargarBarajaPorDefecto();
        perfil = new Perfil();
        try {
            perfil.cargarPerfil();
        } catch (ParserConfigurationException ex) {} catch (SAXException ex) {} catch (IOException ex) {}
        perfil.setBarajaPorDefecto(gestorBarajas.getBarajaPorDefecto());
        Menu m = new Menu(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        menuBorderPane.setPrefSize(1024, 768);
        menuBorderPane.setBackground(new Background(new BackgroundImage(m.getBackground(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
        
    }    
    
    @FXML
    private void clickPartidaEstandar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        this.setUp(new SeleccionNormal(),controller);

        controller.iniciarPartida(gestorBarajas.getBarajaPorDefecto());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clickPerfil(MouseEvent event) throws IOException {         
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PerfilView.fxml"));       
        perfilViewController controller = new perfilViewController(perfil, gestorBarajas);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Perfil");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gestorDeBarajasOnClick(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGestorBarajasView.fxml"));       
        MenuGestorBarajasViewController controller = new MenuGestorBarajasViewController(gestorBarajas,perfil);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gestor de barajas");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void clickModoTrios(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        this.setUp(new SeleccionTrios(),controller);
        controller.setTiempoPartida(90000);
        controller.iniciarPartida(gestorBarajas.barajaATrios(gestorBarajas.getBarajaPorDefecto()));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void clickNiveles(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("NivelesView.fxml")));
        NivelesViewController controller = new NivelesViewController(perfil,gestorBarajas);
        controller.setPerfil(this.perfil);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Elegir Nivel");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setResizable(false);
        stage.show();
    }
    private void setUp(EstrategiaSeleccion estrategia, mainViewController controller){
        GestorBarajas gestor = new GestorBarajas();
        controller.modoJuego = estrategia;
        controller.modoJuego.setPartida(controller.getPartida());
        controller.gestor = gestor;
        controller.gestor.cargarBarajas();
        controller.gestor.cargarBarajaPorDefecto();
        controller.setPerfil(this.perfil);
    }

}
