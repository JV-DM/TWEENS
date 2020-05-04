/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.Carta;
import data_type.GestorBarajas;
import data_type.Perfil;
import java.awt.Font;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class MenuGestorBarajasViewController implements Initializable {

    @FXML
    private ImageView imagenBarajaPorDefecto;
    @FXML
    private Label nombreBarajaPorDefecto;
    private final String RUTA_BACKGROUND = "imagenes/ImagenesBackground/fondo-verde.jpg";
    private Perfil perfil;
    private GestorBarajas gestorBarajas;
    @FXML
    private BorderPane borderPane;
    private final int TAMAÑO_CELDAS_GRID_PANE_BARAJAS = 4;
    private final int TAMAÑO_CELDAS_GRID_PANE_CARTAS = 5;
    
    private final int MENSAJE_CARTAS_INSUFICIENTES = 1;
    private final int NOMBRE_BARAJA_NO_VALIDO = 2;
    private final int NOMBRE_CARTA_REPETIDO = 3;
    
    private final int TAMAÑO_DE_BARAJA_MINIMO = 4;
    
    private final double CARTA_WIDTH = 126;
    private final double CARTA_HEIGHT = 106;
    private GridPane gridPaneBarajas = new GridPane();
    private GridPane gridPaneCartas = new GridPane();
    @FXML
    private Pane paneDerecho;
    @FXML
    private Label textoCabecera;
    @FXML
    private Button atrasButton;
    private Baraja baraja;   

    MenuGestorBarajasViewController(GestorBarajas gestorBarajas, Perfil perfil) {
        this.perfil = perfil;
        this.gestorBarajas = gestorBarajas;
    }
    
    /**
     * Establece los elementos principales de la pantalla al iniciar
     */
    public void setElements(){
        imagenBarajaPorDefecto.setImage(perfil.getBarajaPorDefecto().getCartas().get(0).getImagen());
        nombreBarajaPorDefecto.setText(perfil.getBarajaPorDefecto().getNombre());
        createGridPaneBarajas();
        borderPane.setCenter(gridPaneBarajas);
    }
    
    /**
     * Crear el GridPane para visualizar las barajas
     */
    public void createGridPaneBarajas(){
        List<Baraja> listaDeBarajas = gestorBarajas.getBarajas();
        int indice = 0;
        for(int i = 0; indice < listaDeBarajas.size(); i++){
            for(int j = 0; j < TAMAÑO_CELDAS_GRID_PANE_BARAJAS; j++){      
                if(indice < listaDeBarajas.size()) {
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
    }
    
    /**
     * Crea el GridPane para visualizar las cartas de una baraja
     * @param baraja 
     */
    public void createGridPaneCartas(Baraja baraja){
        gridPaneCartas.getChildren().clear();
        List<Carta> listaDeCartas = baraja.getCartas();        
        int indice = 0;
        for(int i = 0; indice < listaDeCartas.size(); i++){
            for(int j = 0; j < TAMAÑO_CELDAS_GRID_PANE_CARTAS; j++){      
                if(indice < listaDeCartas.size()) {
                    ImageView imagenBaraja = new ImageView(listaDeCartas.get(indice).getImagen());
                    imagenBaraja.setPreserveRatio(false);
                    imagenBaraja.setFitWidth(CARTA_WIDTH);
                    imagenBaraja.setFitHeight(CARTA_HEIGHT);   
                    Label nombreBaraja = new Label(listaDeCartas.get(indice).getNombre());                   
                    VBox vbox = new VBox(imagenBaraja,nombreBaraja);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.addEventHandler(MouseEvent.MOUSE_CLICKED, borrarCarta);
                    gridPaneCartas.add(vbox, j, i);
                    indice += 2;
                }
            }
        }
        //asthetics
        gridPaneCartas.setHgap(40);
        gridPaneCartas.setVgap(20);
    }
    
    /**
     * Evento para ver las cartas de una baraja
     */
    EventHandler<MouseEvent> verBaraja = (MouseEvent event) -> {
        VBox vbox = (VBox) event.getSource();
        ObservableList<Node> listaNodos = vbox.getChildren();
        Label label = (Label) listaNodos.get(1);
        Baraja baraja = gestorBarajas.buscarBaraja(label.getText());
        createGridPaneCartas(baraja);
        this.baraja = baraja;
        borderPane.setCenter(gridPaneCartas);
        borderPane.setRight(null);
        atrasButton.setVisible(true);
    };
    
    /**
     * Evento para borrar una carta
     */
    EventHandler<MouseEvent> borrarCarta = (MouseEvent event) -> {
        VBox vbox = (VBox) event.getSource();
        ObservableList<Node> listaNodos = vbox.getChildren();
        Label label = (Label) listaNodos.get(1);  
        if(baraja.getTamaño() != TAMAÑO_DE_BARAJA_MINIMO * 2){
            if(mensajeDeConfirmacion())
                baraja.eliminarCarta(baraja.buscarCarta(label.getText()));
        }
        else mensajeError(this.MENSAJE_CARTAS_INSUFICIENTES) ;
        createGridPaneCartas(baraja);
    };
    
    /**
     * Mensaje para confrimar la eliminación de una carta
     * @return 
     */
    public boolean mensajeDeConfirmacion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("¿Eliminar la carta?");
        alert.setContentText("¿Estás seguro de que quieres eliminar la carta?");
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
    
    /**
     * Mensajes de error 
     * @param tipo 
     */
    public void mensajeError(int tipo){
        String mensaje = "";
        switch (tipo){
            case  MENSAJE_CARTAS_INSUFICIENTES: 
                mensaje = "No puede haber menos de 4 cartas";
                break;
            case NOMBRE_BARAJA_NO_VALIDO:
                mensaje = "El nombre de la baraja no es valido";
                break;
            case NOMBRE_CARTA_REPETIDO:
                mensaje = "El nombre de la carta ya existe";
                break;
            default : 
                mensaje = "El nombre de la carta no se puede dejar vacio";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setElements();
        borderPane.setPrefSize(924, 668);
        borderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));    
    }    

    @FXML
    private void atrasOnClick(ActionEvent event) {
        borderPane.setCenter(gridPaneBarajas);
        borderPane.setRight(paneDerecho);
        atrasButton.setVisible(false);
        baraja = null;
    }
    
}
