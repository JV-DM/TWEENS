/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import data_type.Baraja;
import data_type.Carta;
import data_type.GestorArchivos;
import data_type.GestorBarajas;
import data_type.Perfil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class MenuGestorBarajasViewController implements Initializable {


    private final String RUTA_BACKGROUND = "imagenes/ImagenesBackground/fondo-verde.jpg";
    private Perfil perfil;
    private GestorBarajas gestorBarajas;
    @FXML
    private BorderPane borderPane;
    private final int TAMAÑO_CELDAS_GRID_PANE_BARAJAS = 5;
    private final int TAMAÑO_CELDAS_GRID_PANE_CARTAS = 5;
    
    private final String MENSAJE_CARTAS_INSUFICIENTES = "La baraja debe tener 4 cartas como mínimo";
    private final String NOMBRE_CARTA_YA_EXISTENTE = "Ya existe una carta con ese nombre";
    
    private final int TAMAÑO_DE_BARAJA_MINIMO = 4;
    
    private final double CARTA_WIDTH = 126;
    private final double CARTA_HEIGHT = 106;
    
    private final int MODO_VER_BARAJAS = 0;
    private final int MODO_VER_CARTAS = 1;
    
    private boolean barajaCreada = false;
    private int modo;
    private GestorArchivos gestorArchivos = new GestorArchivos();
    private GridPane gridPaneBarajas = new GridPane();
    private GridPane gridPaneCartas = new GridPane();
    @FXML
    private Label textoCabecera;
    private Baraja baraja;   
    @FXML
    private ImageView añadir;
    private ImageView ver;
    @FXML
    private ImageView verCartas;
    @FXML
    private ImageView eliminarBaraja;
    @FXML
    private ImageView atras;

    MenuGestorBarajasViewController(GestorBarajas gestorBarajas, Perfil perfil) {
        this.perfil = perfil;
        this.gestorBarajas = gestorBarajas;
    }
    
    /**
     * Crear el GridPane para visualizar las barajas
     */
    public void createGridPaneBarajas(){
        gridPaneBarajas.getChildren().clear();
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
        this.baraja = baraja;
        verCartas.setVisible(true);     
        eliminarBaraja.setVisible(true);
    };
    
    public void modoVerBarajas(){
        createGridPaneBarajas();
        modo = this.MODO_VER_BARAJAS;
        borderPane.setCenter(gridPaneBarajas);
        atras.setVisible(false);
        verCartas.setVisible(false);
        eliminarBaraja.setVisible(false);
        textoCabecera.setText("Barajas");
        baraja = null;
    }
    
    public void modoVerCartas(){
        createGridPaneCartas(baraja);
        modo = this.MODO_VER_CARTAS;
        borderPane.setCenter(gridPaneCartas);
        atras.setVisible(true);
        verCartas.setVisible(false);
        eliminarBaraja.setVisible(false);
        textoCabecera.setText(baraja.getNombre());
    }
    
    /**
     * Evento para borrar una carta
     */
    EventHandler<MouseEvent> borrarCarta = (MouseEvent event) -> {
        VBox vbox = (VBox) event.getSource();
        ObservableList<Node> listaNodos = vbox.getChildren();
        Label label = (Label) listaNodos.get(1);  
        if(baraja.getTamaño() != TAMAÑO_DE_BARAJA_MINIMO * 2){
            if(mensajeDeConfirmacion("¿Estás seguro de que quieres eliminar la carta?"))
                baraja.eliminarCarta(baraja.buscarCarta(label.getText()));
        }
        else mensajeError(this.MENSAJE_CARTAS_INSUFICIENTES) ;
        createGridPaneCartas(baraja);
    };
    
    /**
     * Mensaje para confrimar la eliminación de una carta
     * @return 
     */
    public boolean mensajeDeConfirmacion(String texto){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("¿Eliminar la carta?");
        alert.setContentText(texto);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
    
    
    public void mensajeError(String texto){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setRight(null);
        modoVerBarajas();
        borderPane.setPrefSize(924, 668);
        borderPane.setBackground(new Background(new BackgroundImage(new Image(RUTA_BACKGROUND),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));    
    }       
    
    public String mensajeConCampoDeTexto(String titulo, String texto, String nombrePredefinido){
        TextInputDialog dialog = new TextInputDialog(nombrePredefinido);
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(texto);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
                return result.get();
        }
        return null;
    }
    
    public void barajaCreada(Baraja baraja){
        this.baraja = baraja;
        barajaCreada = true;
    }
    
    public void formularioCrearBaraja(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormularioCrearBaraja.fxml"));       
        FormularioCrearBarajaController controller = new FormularioCrearBarajaController(gestorBarajas,this);
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Crear baraja");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.getIcons().add(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"));
        stage.setResizable(false);
        stage.showAndWait();    
    }
    
    /**
     * Añade o una baraja o una carta en función de si hay una baraja seleccionada
     * @param event
     * @throws IOException 
     */
    @FXML
    private void añadirOnClick(MouseEvent event) throws IOException {
        if(modo == this.MODO_VER_CARTAS) {
            File archivoImagen = gestorArchivos.fileChooser(borderPane);
            String rutaDestino = gestorArchivos.getRuta_Barajas() + baraja.getNombre() 
                                 + ";" + baraja.getTematica() + "/";
            if (archivoImagen != null){                
                    String nombreCarta = mensajeConCampoDeTexto("Nombre de la carta", "Introduce el nombre de la carta", archivoImagen.getName());
                    if(baraja.buscarCarta(nombreCarta) == null){
                        gestorArchivos.copyFile(archivoImagen, rutaDestino + nombreCarta);
                        baraja.añadirCarta(new Carta(new Image("File:///" + rutaDestino + nombreCarta),nombreCarta,(baraja.getTamaño()/2) + 1));
                        createGridPaneCartas(baraja);
                        borderPane.setCenter(gridPaneCartas);
                    }
                    else mensajeError(NOMBRE_CARTA_YA_EXISTENTE);
            }       
        }
        else{
                formularioCrearBaraja(event);     
                if(barajaCreada) {
                    createGridPaneCartas(baraja);
                    modoVerCartas();
                    gestorArchivos.createDirectory(gestorArchivos.getRuta_Barajas() + baraja.getNombre() + ";" + baraja.getTematica());
                    createGridPaneCartas(baraja);
                    modoVerCartas();
                }
            
        }
    }

    @FXML
    private void verCartasOnClick(MouseEvent event) {
        modoVerCartas();
    }

    @FXML
    private void eliminarBarajaOnClick(MouseEvent event) {
        if(mensajeDeConfirmacion("¿Estás seguro de que quieres eliminar la baraja?")){
            String rutaBaraja = gestorArchivos.getRuta_Barajas() 
                    + baraja.getNombre() +";" + baraja.getTematica() + "/";
            baraja.getCartas().forEach((carta) -> {
                gestorArchivos.deleteFile(new File(rutaBaraja + carta.getNombre()));
            });
            gestorArchivos.deleteFile(new File(rutaBaraja));
            gestorBarajas.eliminarBaraja(baraja);
            modoVerBarajas();
        }
    }

    @FXML
    private void atrasOnClick(MouseEvent event) {

        if(modo == MODO_VER_CARTAS && barajaCreada){
            if(baraja.getTamaño() >= 8){
                gestorBarajas.añadirBaraja(baraja);
                modo = this.MODO_VER_BARAJAS;
                barajaCreada = false;
                modoVerBarajas();  
            }
            else mensajeError(MENSAJE_CARTAS_INSUFICIENTES);               
        }
        
        else {
            modoVerBarajas();
            modo = this.MODO_VER_BARAJAS;
        }
        verCartas.setVisible(false);
        eliminarBaraja.setVisible(false);
    }
}
    
