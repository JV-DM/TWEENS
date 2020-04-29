/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public class Tablero {
    
    private Image imagen;
    
    public Tablero(){
        imagen = new Image("imagenes/ImagenesBackground/fondo-verde.jpg");
    }
    public Tablero(Image imagen){
        this.imagen = imagen;
    }
    
    /**
     * Devuelve la imagen del tablero
     * @return 
     */
    public Image getImagen(){ return imagen; }
       
    /**
     * Cambia la imagen del tablero
     * @param nuevaImagen 
     */
    public void setImagen(Image nuevaImagen){ this.imagen = nuevaImagen; }

    
}
