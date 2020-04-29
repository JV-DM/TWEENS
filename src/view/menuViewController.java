package view;

import data_type.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class menuViewController {

    @FXML
    private BorderPane menuBorderPane;

    @FXML
    private Label tittle_label;

    @FXML
    private VBox menu_Vbox;

    @FXML
    private Button partidaEstandar;


    @FXML
    private Button partidaEstandar2;
    @FXML
    private Button perfil;





    private void initialize() {

        Menu m = new Menu(new Image("imagenes/ImagenesBackground/fondo-verde.jpg"));
        menuBorderPane.setPrefSize(1024, 768);
        menuBorderPane.setBackground(new Background(new BackgroundImage(m.getBackground(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, 100, true,true, false, true))));
        menu_Vbox.setAlignment(Pos.CENTER);

        //se borra cuando sean necesarios
        partidaEstandar2.setVisible(false);

        //titulo aesthitics
        tittle_label.setAlignment(Pos.TOP_CENTER);
        tittle_label.setFont(Font.font("anton"));
        tittle_label.setFont(Font.font(150));
        tittle_label.setStyle("-fx-font-weight: bold");

    }

   @FXML
    private void clickParidaEsntandar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void perfilOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PerfilView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
