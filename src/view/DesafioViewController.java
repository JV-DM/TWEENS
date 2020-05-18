/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.Desafio.Desafio;
import data_type.GestorDesafios;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createGridPaneDesafios();
        borderPane.setCenter(gridPane);
        borderPane.setPrefSize(924, 668);
        borderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));  
    }    
    
    public DesafioViewController(GestorDesafios gestorDesafios){
        this.gestorDesafios = gestorDesafios;
    }

    public void createGridPaneDesafios(){
        gridPane.getChildren().clear();
        List<Desafio> listaDeDesafios = gestorDesafios.getDesafios();
        int indice = 0;
        for(int i = 0; indice < listaDeDesafios.size(); i++){
            for(int j = 0; j < NUMERO_DESAFIOS_POR_FILA; j++){      
                if(indice < listaDeDesafios.size()) {
                    ImageView imagenDesafio = new ImageView(listaDeDesafios.get(indice).getImagen());
                    imagenDesafio.setPreserveRatio(false);
                    imagenDesafio.setFitWidth(IMAGEN_WIDTH);
                    imagenDesafio.setFitHeight(IMAGEN_HEIGHT);   
                    Label desafioCompletado = new Label(desafioCompletado(listaDeDesafios.get(indice)));                   
                    VBox vbox = new VBox(imagenDesafio,desafioCompletado);
                    vbox.setAlignment(Pos.CENTER);
                    //vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, verBaraja);
                    gridPane.add(vbox, j, i);
                    indice++;                  
                }
            }
        }
        //asthetics
        gridPane.setHgap(40);
        gridPane.setVgap(20);
    }

    public String desafioCompletado(Desafio desafio){
        if(desafio.getCompletado()) return "Conseguido";
        else if(gestorDesafios.getDesafioEnCurso() != null)
                if(gestorDesafios.getDesafioEnCurso().getId() == desafio.getId())
                    return "En curso";
        return "No empezado";
    }
}
