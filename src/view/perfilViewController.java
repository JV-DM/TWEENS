/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.GestorBarajas;
import data_type.Historial;
import data_type.Idioma;
import data_type.IdiomaProperty;
import data_type.Perfil;
import data_type.Ranking;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class perfilViewController implements Initializable {

    @FXML
    private ImageView imagenPerfil;
    @FXML
    private TextField nombrePerfil;
    @FXML
    private ImageView banderaIdioma;
    @FXML
    private ChoiceBox<String> selectorIdioma;
    @FXML
    private ImageView tableroPorDefecto;
    @FXML
    private Label numeroVictorias;
    @FXML
    private Label numeroDerrotas;
    @FXML
    private Label numeroMejorPuntuacion;
    @FXML
    private Label numeroPuntuacionTotal;
    @FXML
    private BorderPane borderPane;

    private Perfil perfil;
    private String rutaImagenPerfil;
    private String rutaImagenTablero;

    @FXML
    private ImageView guardarPerfil;
    private boolean imagenCambiadaPerfil = false;
    private boolean imagenCambiadaTablero = false;
    @FXML
    private Label numeroBarajas;
    private GestorBarajas gestorBarajas;
    private Ranking ranking;
    private IdiomaProperty idioma;
    @FXML
    private Label nombreDelPerfilLabel;
    @FXML
    private Label tableroPorDefectoLabel;
    @FXML
    private Label numeroDeBarajasLabel;
    @FXML
    private Label estadisticasLabel;
    @FXML
    private Label victoriaLabel;
    @FXML
    private Label derrotasLabel;
    @FXML
    private Label mejorPuntuacionLabel;
    @FXML
    private Label puntuacionTotalLabel;
    @FXML
    private Button verRanking;
    @FXML
    private Button buttonHistorial;
    
    private Historial historial;
    
    public void setElements(Perfil perfil){
        setIdioma();
        nombrePerfil.setText(perfil.getNombre());
        imagenPerfil.setImage(new Image(perfil.getRutaImagen()));
        banderaIdioma.setImage(perfil.getIdioma().getImagenBandera());
        numeroBarajas.setText(String.valueOf(gestorBarajas.getBarajas().size()));
        setIdiomas();
        tableroPorDefecto.setImage(new Image(perfil.getRutaTableroPorDefecto()));
        numeroVictorias.setText(String.valueOf(perfil.getVictorias()));
        numeroDerrotas.setText(String.valueOf(perfil.getDerrotas()));
        numeroMejorPuntuacion.setText(String.valueOf(perfil.getPuntuacionMaxima()));
        numeroPuntuacionTotal.setText(String.valueOf(perfil.getPuntuacionTotal()));
        selectorIdioma.getSelectionModel().selectedItemProperty().addListener(cambioIdioma);
        
    }
    
    public void setIdioma(){
        idioma = new IdiomaProperty(perfil.getIdioma());
        nombreDelPerfilLabel.setText(idioma.getProp().getProperty("Nombre_del_perfil"));
        tableroPorDefectoLabel.setText(idioma.getProp().getProperty("Tablero_Por_Defecto"));   
        numeroDeBarajasLabel.setText(idioma.getProp().getProperty("Numero_de_barajas"));
        estadisticasLabel.setText(idioma.getProp().getProperty("Estadisticas"));
        victoriaLabel.setText(idioma.getProp().getProperty("Victorias"));
        derrotasLabel.setText(idioma.getProp().getProperty("Derrotas"));
        mejorPuntuacionLabel.setText(idioma.getProp().getProperty("Mejor_Puntuacion"));
        puntuacionTotalLabel.setText(idioma.getProp().getProperty("Puntuacion_total"));
        verRanking.setText(idioma.getProp().getProperty("Ver_Ranking"));
        buttonHistorial.setText(idioma.getProp().getProperty("Historial"));

    }
    
    public void setIdiomas(){
        ObservableList<String> listaDeIdiomas = FXCollections.observableArrayList();
        for(Idioma idioma : Idioma.values())
            listaDeIdiomas.add(idioma.name());
        selectorIdioma.setItems(listaDeIdiomas);
        selectorIdioma.setValue(perfil.getIdioma().name());
    }
    
    public File fileChooser(BorderPane borderPane) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
         File archivoDeImagen = fileChooser.showOpenDialog((Stage) borderPane.getScene().getWindow());
         return archivoDeImagen;
    }
     
    public perfilViewController(Perfil perfil,GestorBarajas gestorBarajas,Ranking ranking, Historial historial){
        this.perfil = perfil;
        this.gestorBarajas = gestorBarajas;
        this.ranking = ranking;
        this.historial = historial;
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //imagenPerfil.setDisable(true);
        borderPane.setPrefSize(500, 700);
        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
        setElements(perfil);
        nombrePerfil.textProperty().addListener((observable, oldValue, newValue) -> {
            guardarPerfil.setVisible(true);
        });
    }    

    @FXML
    private void imagenPerfilOnClick(MouseEvent event) throws IOException {       
        File archivoImagen = fileChooser(borderPane);       
        if(archivoImagen != null && archivoImagen.exists()){
            rutaImagenPerfil = "File:///" + archivoImagen.getAbsolutePath();
            System.out.println(archivoImagen.exists());
            imagenPerfil.setImage(new Image(rutaImagenPerfil));
            imagenCambiadaPerfil = true;
            guardarPerfil.setVisible(true);
        }
    }

    public void mensajeDeError(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("No puedes dejar vacio el campo del nombre");
        alert.showAndWait();
    }
    
    @FXML
    private void guadarPerfilOnClick(MouseEvent event) throws ParserConfigurationException, TransformerException {
        if(perfil.nombrePerfilCorrecto(nombrePerfil.getText())) 
            perfil.setName(nombrePerfil.getText());
        else 
            mensajeDeError();
        if(imagenCambiadaPerfil) {
            perfil.setRutaImagen(rutaImagenPerfil);
            imagenCambiadaPerfil = false;
        }
        if(imagenCambiadaTablero){
            perfil.setRutaTableroPorDefecto(rutaImagenTablero);
            imagenCambiadaTablero = false;
        }
        perfil.guardarPerfil();
        setIdioma();
        guardarPerfil.setVisible(false);
    }

    @FXML
    private void imagenTableroOnClick(MouseEvent event) {
         File archivoImagen = fileChooser(borderPane);       
        if(archivoImagen != null && archivoImagen.exists()){
            rutaImagenTablero = "File:///" + archivoImagen.getAbsolutePath();
            tableroPorDefecto.setImage(new Image(rutaImagenTablero));
            imagenCambiadaTablero = true;
            guardarPerfil.setVisible(true);
        }
    }

    @FXML
    private void rankingOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RankingsView.fxml"));
        RankingsViewController controller = new RankingsViewController(ranking,perfil);
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
            
    ChangeListener<String> cambioIdioma = new ChangeListener<String>() {
 
            @Override
            public void changed(ObservableValue<? extends String> observable, //
                    String oldValue, String newValue) {
                if (newValue != null) {
                    if(newValue.equals("Español")) perfil.setLanguage(Idioma.Español);
                    else if(newValue.equals("Frances")) perfil.setLanguage(Idioma.Frances);
                    else if(newValue.equals("Ingles")) perfil.setLanguage(Idioma.Ingles);
                    else if(newValue.equals("Valenciano")) perfil.setLanguage(Idioma.Valenciano);
                    banderaIdioma.setImage(perfil.getIdioma().getImagenBandera());
                    if(!oldValue.equals(newValue)) guardarPerfil.setVisible(true);
                }
            }
        };

    @FXML
    private void historialOnAction(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HistorialView.fxml"));
        HistorialViewController controller = new HistorialViewController(historial,perfil);
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
        
            
    

