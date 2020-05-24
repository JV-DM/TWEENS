package data_type;

import javafx.scene.image.Image;

public class CartaCategoria extends Carta {
    private String categoria;
    public CartaCategoria(Image imagen, String nombre, int id,String categoria) {
        super(imagen, nombre, id);
        this.categoria = categoria;
    }
    public String getCategoria(){
        return this.categoria;
    }
}