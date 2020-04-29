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
import java.net.URL;
import java.util.ResourceBundle;
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
    private ImageView barajaMasUtilizada;
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
    
    private GestorArchivos gestorArchivos = new GestorArchivos();
    
    public void setElements(Perfil perfil){
        nombrePerfil.setText(perfil.getNombre());
        imagenPerfil.setImage(perfil.getImagen());
        banderaIdioma.setImage(perfil.getIdioma().getImagenBandera());
        setIdiomas();
        tableroPorDefecto.setImage(perfil.getTableroPorDefecto().getImagen());
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
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setPrefSize(1024, 768);
        borderPane.setBackground(new Background(new BackgroundImage(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
        perfil = new Perfil();
        perfil.cargarPerfil();
        setElements(perfil);
    }    

    @FXML
    private void imagenPerfilOnClick(MouseEvent event) {       
        File archivoImagen = fileChooser();       
        gestorArchivos.copiarArchivo(archivoImagen.getPath(), gestorArchivos.getRutaImagenesSistema());
        perfil.setImage(new Image(archivoImagen.getPath()));
        imagenPerfil.setImage(perfil.getImagen());
    }
    
}
