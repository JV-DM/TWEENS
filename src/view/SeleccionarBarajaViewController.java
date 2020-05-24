/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.GestorBarajas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class SeleccionarBarajaViewController implements Initializable {

    private final double CARTA_WIDTH = 126;
    private final double CARTA_HEIGHT = 106;
    private final int TAMAÑO_CELDAS_GRID_PANE_BARAJAS = 4;
    private final String RUTA_BACKGROUND = "imagenes/ImagenesBackground/fondo-verde.jpg";

    @FXML
    private BorderPane mainBorderPane;
    private GestorBarajas gestorBarajas;

    private GridPane gridPaneBarajas = new GridPane();
    private MenuViewController controller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createGridPaneBarajas();
        mainBorderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
    }

    public SeleccionarBarajaViewController(GestorBarajas gestorBarajas,MenuViewController controller){
        this.gestorBarajas = gestorBarajas;
        this.controller = controller;
    }

    public void createGridPaneBarajas(){
        List<Baraja> listaDeBarajas = gestorBarajas.getBarajas();
        int indice = 0;
        for(int i = 0; indice < listaDeBarajas.size(); i++){
            for(int j = 0; j < TAMAÑO_CELDAS_GRID_PANE_BARAJAS; j++){
                if(indice < listaDeBarajas.size() && listaDeBarajas.get(indice).getCartas().size() > 0) {
                    ImageView imagenBaraja = new ImageView(listaDeBarajas.get(indice).getCartas().get(0).getImagen());
                    imagenBaraja.setPreserveRatio(false);
                    imagenBaraja.setFitWidth(CARTA_WIDTH);
                    imagenBaraja.setFitHeight(CARTA_HEIGHT);
                    Label nombreBaraja = new Label(listaDeBarajas.get(indice).getNombre());
                    VBox vbox = new VBox(imagenBaraja,nombreBaraja);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, verBaraja);
                    gridPaneBarajas.add(vbox, j, i);
                    indice++;
                }
            }
        }
        //asthetics
        gridPaneBarajas.setHgap(40);
        gridPaneBarajas.setVgap(20);
        mainBorderPane.setCenter(gridPaneBarajas);
    }

    EventHandler<MouseEvent> verBaraja = (MouseEvent event) -> {
        VBox vbox = (VBox) event.getSource();
        ObservableList<Node> listaNodos = vbox.getChildren();
        Label label = (Label) listaNodos.get(1);
        Baraja baraja = gestorBarajas.buscarBaraja(label.getText());
        controller.barajaSeleccionada(baraja);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    };

}