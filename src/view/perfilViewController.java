/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.GestorArchivos;
import data_type.Idioma;
import data_type.Perfil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private String rutaImagen;
    
    private GestorArchivos gestorArchivos = new GestorArchivos();
    @FXML
    private ImageView guardarPerfil;
    @FXML
    private ImageView barajaPorDefecto;
    @FXML
    private Label nombreBarajaPorDefecto;
    
    public void setElements(Perfil perfil){
        nombrePerfil.setText(perfil.getNombre());
        imagenPerfil.setImage(new Image(perfil.getRutaImagen()));
        banderaIdioma.setImage(perfil.getIdioma().getImagenBandera());
        barajaPorDefecto.setImage(perfil.getBarajaPorDefetco().getCartas().get(0).getImagen());
        nombreBarajaPorDefecto.setText(perfil.getBarajaPorDefetco().getNombre());
        barajaPorDefecto.setFitHeight(168);
        barajaPorDefecto.setFitWidth(250);
        setIdiomas();
        //tableroPorDefecto.setImage(perfil.getTableroPorDefecto().getImagen());
        numeroVictorias.setText(String.valueOf(perfil.getVictorias()));
        numeroDerrotas.setText(String.valueOf(perfil.getDerrotas()));
        numeroMejorPuntuacion.setText(String.valueOf(perfil.getPuntuacionMaxima()));
        numeroPuntuacionTotal.setText(String.valueOf(perfil.getPuntuacionTotal()));
        
    }
    
    public void setIdiomas(){
        ObservableList<String> listaDeIdiomas = FXCollections.observableArrayList();
        for(Idioma idioma : Idioma.values())
            listaDeIdiomas.add(idioma.name());
        selectorIdioma.setItems(listaDeIdiomas);
        selectorIdioma.setValue(perfil.getIdioma().name());
    }
    
    public File fileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
         File archivoDeImagen = fileChooser.showOpenDialog((Stage) borderPane.getScene().getWindow());
         return archivoDeImagen;
    }
     
    public perfilViewController(Perfil perfil){
        this.perfil = perfil;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //imagenPerfil.setDisable(true);
        borderPane.setPrefSize(829, 543);
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
        File archivoImagen = fileChooser();       
        if(archivoImagen != null){
            //gestorArchivos.copiarArchivo(archivoImagen.getPath(), gestorArchivos.getRutaImagenesSistema());
            rutaImagen = archivoImagen.getAbsolutePath();
            System.out.println(rutaImagen);
            imagenPerfil.setImage(new Image(rutaImagen));
            guardarPerfil.setVisible(true);
        }
    }

    @FXML
    private void guadarPerfilOnClick(MouseEvent event) throws ParserConfigurationException, TransformerException {
        perfil.setName(nombrePerfil.getText());
        if(rutaImagen != null)
            perfil.setRutaImagen(rutaImagen);
        perfil.guardarPerfil();
        guardarPerfil.setVisible(false);
    }
    
}
