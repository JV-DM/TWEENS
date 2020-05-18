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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public DesafioViewController(GestorDesafios gestorDesafios){
        this.gestorDesafios = gestorDesafios;
    }
    /**
    public void createGridPaneBarajas(){
        gridPane.getChildren().clear();
        List<Desafio> listaDeDesafios = gestorDesafios.getDesafios();
        int indice = 0;
        for(int i = 0; indice < listaDeDesafios.size(); i++){
            for(int j = 0; j < NUMERO_DESAFIOS_POR_FILA; j++){      
                if(indice < listaDeDesafios.size()) {
                    ImageView imagenDesafio = new ImageView(imagenDesafio(listaDeDesafios.get(indice)));
                    imagenDesafio.setPreserveRatio(false);
                    imagenDesafio.setFitWidth(IMAGEN_WIDTH);
                    imagenDesafio.setFitHeight(IMAGEN_HEIGHT);   
                    Label nombreBaraja = new Label(listaDeDesafios.get(indice).getNombre());                   
                    VBox vbox = new VBox(imagenDesafio,nombreBaraja);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, verBaraja);
                    gridPane.add(vbox, j, i);
                    indice++;                  
                }
            }
        }
        //asthetics
        gridPane.setHgap(40);
        gridPane.setVgap(20);
    }
    
    public String imagenDesafio(Desafio){
        
    }
    * **/
}
