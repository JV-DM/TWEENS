/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twins;

import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public class Imagen {
    private Image imagen;
    private String rutaImagen;
    
    public Imagen(String rutaImagen){
        this.rutaImagen = rutaImagen;
        this.imagen = new Image(rutaImagen);
    }
    
    public Image GetImage(){return imagen;}
    public String GetRutaImagen(){return rutaImagen;}
    
    public void SetImage(String nuevaRuta){
        this.rutaImagen = nuevaRuta;
        this.imagen = new Image(rutaImagen);
    }
}
