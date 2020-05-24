/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.Desafio.Desafio;
import data_type.GestorDesafios;
import data_type.IdiomaProperty;
import data_type.Perfil;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class DesafioViewController implements Initializable {

    private GestorDesafios gestorDesafios;
    private GridPane gridPane = new GridPane();
    private static final int NUMERO_DESAFIOS_POR_FILA = 5;
    private static final int IMAGEN_WIDTH = 141;
    private static final int IMAGEN_HEIGHT = 74;
    private final String RUTA_BACKGROUND = "imagenes/ImagenesBackground/fondo-verde.jpg";
    @FXML
    private BorderPane borderPane;
    private Perfil perfil;
    private IdiomaProperty idioma;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createGridPaneDesafios();       
        borderPane.setPrefSize(924, 668);
        borderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));  
    }    
    
    public DesafioViewController(GestorDesafios gestorDesafios, Perfil perfil){
        this.gestorDesafios = gestorDesafios;
        this.perfil = perfil;
    }

    public void createGridPaneDesafios(){
        gridPane.getChildren().clear();
        List<Desafio> listaDeDesafios = gestorDesafios.getDesafios();
        int indice = 0;
        for(int i = 0; indice < listaDeDesafios.size(); i++){
            for(int j = 0; j < NUMERO_DESAFIOS_POR_FILA; j++){      
                if(indice < listaDeDesafios.size()) {
                    ImageView imagenDesafio = new ImageView(listaDeDesafios.get(indice).getImagen());
                    imagenDesafio.setId(String.valueOf(listaDeDesafios.get(indice).getId()));
                    imagenDesafio.setPreserveRatio(false);
                    imagenDesafio.setFitWidth(IMAGEN_WIDTH);
                    imagenDesafio.setFitHeight(IMAGEN_HEIGHT);   
                    Label desafioCompletado = new Label(desafioCompletado(listaDeDesafios.get(indice)));                   
                    VBox vbox = new VBox(imagenDesafio,desafioCompletado);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, verDesafio);
                    gridPane.add(vbox, j, i);
                    indice++;                  
                }
            }
        }
        //asthetics
        gridPane.setHgap(40);
        gridPane.setVgap(20);
        borderPane.setCenter(gridPane);
    }
    
    EventHandler<MouseEvent> verDesafio = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            VBox vbox = (VBox) event.getSource();
            ObservableList<Node> listaNodos = vbox.getChildren();
            int idDesafio = Integer.valueOf(listaNodos.get(0).getId());
            Desafio desafio = gestorDesafios.getDesafioPorId(idDesafio);
            boolean confirmado = mensajeDeConfirmacion(desafio.getDescripcion());
            if(confirmado) {
                if(desafio.getCompletado() == true) desafio.setCompletado(false);
                gestorDesafios.setDesafioEnCurso(desafio);               
                createGridPaneDesafios();               
                try {
                    gestorDesafios.guardarDesafios();
                } catch (ParserConfigurationException ex) {} catch (TransformerException ex) {}
            }
        }
    };

    public boolean mensajeDeConfirmacion(String texto){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(idioma.getProp().getProperty("Aceptar_Desafio"));
        alert.setContentText(texto);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }   
    
    public String desafioCompletado(Desafio desafio){
         idioma = new IdiomaProperty(perfil.getIdioma());
        if(desafio.getCompletado()) return idioma.getProp().getProperty("Conseguido");
        else if(gestorDesafios.getDesafioEnCurso() != null)
                if(gestorDesafios.getDesafioEnCurso().getId() == desafio.getId())
                    return idioma.getProp().getProperty("En_curso");
        return idioma.getProp().getProperty("No_Empezado");
    }
}
