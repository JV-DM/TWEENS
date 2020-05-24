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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.VBox;
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
    private GestorBarajas gestorBarajas;
    private GestorDesafios gestorDesafios;
    private Ranking ranking;
    
    private Baraja baraja;
    @FXML
    private ImageView imagenPerfil;
    @FXML
    private Label nombrePerfil;
    @FXML
    private Button modosDeJuego;
    @FXML
    private VBox modosDeJuegoVBox;
    private boolean verModosDeJuego = false;
    @FXML
    private VBox otrosButtons;
    @FXML
    private VBox modoVBox;
    
    private IdiomaProperty idioma;
    @FXML
    private Button partidaEstandar;
    @FXML
    private Button modoTrios;
    @FXML
    private Button niveles;
    @FXML
    private Button gestorDeBarajas;
    @FXML
    private Button desafios;
    @FXML
    private Button historialButton;
    @FXML
    private Button btnModoCategoria;
    private Historial historial;
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
        gestorDesafios = new GestorDesafios();
        try {
            perfil.cargarPerfil();
            ranking.cargarRanking();
            historial.cargarHistorial();
            gestorDesafios.cargarDesafios();
        } catch (ParserConfigurationException ex) {} catch (SAXException ex) {} catch (IOException ex) {} catch (ParseException e) {
            e.printStackTrace();
        }
        setElements();
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
        modoVBox.getChildren().add(otrosButtons);
        modosDeJuegoVBox.setVisible(false);
        
        
    }    
    
    public void setElements(){
        idioma = new IdiomaProperty(perfil.getIdioma());
        modosDeJuego.setText(idioma.getProp().getProperty("Modos_De_Juego"));
        partidaEstandar.setText(idioma.getProp().getProperty("Partida_Estandar"));
        modoTrios.setText(idioma.getProp().getProperty("Modo_trios"));
        niveles.setText(idioma.getProp().getProperty("Niveles"));
        gestorDeBarajas.setText(idioma.getProp().getProperty("Gestor_De_Barajas"));
        desafios.setText(idioma.getProp().getProperty("Desafios"));
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
        perfilViewController controller = new perfilViewController(perfil, gestorBarajas,ranking,historial);
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
        setElements();
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
        stage.showAndWait();
        //gestorBarajas.cargarBarajas();
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
        controller.setGestorDesafios(gestorDesafios);
        Scene scene = new Scene(root,1024, 768);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Elegir Nivel");       
        //stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void clickCategoria(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        //elegirBaraja(actionEvent);
        this.setUp(new SeleccionCategoria(),controller);
        controller.setTiempoPartida(90000);
        controller.iniciarPartida(gestorBarajas.barajaCategorias());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private void setUp(EstrategiaSeleccion estrategia, mainViewController controller){
        GestorBarajas gestor = new GestorBarajas();
        controller.modoJuego = estrategia;
        controller.gestor = gestor;
        controller.setPerfil(this.perfil);
        controller.setRanking(this.ranking);
        controller.setHistorial(this.historial);
        controller.setGestorDesafios(this.gestorDesafios);
    }

    @FXML
    private void clickModoCartas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MainView.fxml")));
        Parent root = loader.load();
        mainViewController controller = loader.getController();
        elegirBaraja(event);
        if(baraja != null) {
            this.setUp(new SeleccionModoCarta(),controller);
            controller.setTiempoPartida(900000);
            controller.iniciarPartida(baraja);
            Scene scene = new Scene(root,menuBorderPane.getWidth(), menuBorderPane.getHeight());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
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

    @FXML
    private void modosDeJuegoOnClick(ActionEvent event) {
        if(verModosDeJuego){
            verModosDeJuego = false;
            modosDeJuego.getStyleClass().remove("button-pressed");
            modoVBox.getChildren().clear();
            modoVBox.getChildren().add(otrosButtons);    
            modosDeJuegoVBox.setVisible(false);
            otrosButtons.setVisible(true);
        }
        else {
            verModosDeJuego = true;
            modosDeJuego.getStyleClass().add("button-pressed");
            modoVBox.getChildren().clear();
            modoVBox.getChildren().add(modosDeJuegoVBox);    
            modosDeJuegoVBox.setVisible(true);
            otrosButtons.setVisible(false);
        }
    }

    @FXML
    private void desafiosOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DesafioView.fxml"));
        DesafioViewController controller = new DesafioViewController(gestorDesafios,perfil);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Desafios");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();
    }
    
    public void setGestorDesafios(GestorDesafios gestorDesafios){
        this.gestorDesafios = gestorDesafios;
    }
}
