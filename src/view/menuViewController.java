/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Ranking ranking;
    private Historial historial;
    private Baraja baraja;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private Label nombrePerfil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gestorBarajas = new GestorBarajas();
        gestorBarajas.cargarBarajas();
        perfil = new Perfil();
        ranking = new Ranking();
        historial = new Historial();
        try {
            perfil.cargarPerfil();
            ranking.cargarRanking();
            historial.cargarHistorial();
        } catch (ParserConfigurationException ex) {} catch (SAXException ex) {} catch (IOException ex) {} catch (ParseException e) {
            e.printStackTrace();
        }
        perfil.setBarajaPorDefecto(gestorBarajas.getBarajaPorDefecto());
        imagenPerfil.setImage(new Image(perfil.getRutaImagen()));
        nombrePerfil.setText(perfil.getNombre());
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
        elegirBaraja(event);
        if(baraja != null) {
            this.setUp(new SeleccionNormal(),controller);
            controller.iniciarPartida(baraja);
            Scene scene = new Scene(root,menuBorderPane.getWidth(), menuBorderPane.getHeight());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        elegirBaraja(event);
        if(baraja != null) {
            this.setUp(new SeleccionTrios(),controller);
            controller.setTiempoPartida(90000);
            controller.iniciarPartida(gestorBarajas.barajaATrios(baraja));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    public void clickNiveles(ActionEvent actionEvent) throws IOException {
        //elegirBaraja(actionEvent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NivelesView.fxml"));
        Parent root = loader.load();
        NivelesViewController controller = loader.getController();      
        controller.setPerfil(this.perfil);
        controller.setGestorBarajas(gestorBarajas);
        controller.setBaraja(gestorBarajas.getBarajaPorDefecto());
        Scene scene = new Scene(root,1024, 768);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Elegir Nivel");       
        //stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        //stage.setResizable(false);
        stage.show();
    }
    private void setUp(EstrategiaSeleccion estrategia, mainViewController controller){
        GestorBarajas gestor = new GestorBarajas();
        controller.modoJuego = estrategia;
        controller.modoJuego.setPartida(controller.getPartida());
        controller.gestor = gestor;
        controller.setPerfil(this.perfil);
        controller.setRanking(this.ranking);
        controller.setHistorial(this.historial);
    }
    @FXML
    private void clickRanking(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RankingsView.fxml"));
        RankingsViewController controller = new RankingsViewController(ranking);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Rankings");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void clickHistorial(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistorialView.fxml"));
        HistorialViewController controller = new HistorialViewController(historial);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Historial");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void barajaSeleccionada(Baraja baraja){
        this.baraja = baraja;
    }

    public void elegirBaraja(Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarBarajaView.fxml"));
        SeleccionarBarajaViewController controller = new SeleccionarBarajaViewController(gestorBarajas,this);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Historial");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }
}
