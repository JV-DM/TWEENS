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
public class CaraPosterior {
    private Image imagen;
    private String nombre;

    public CaraPosterior(Image imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    /**
     * @return Imagen
     */
    public Image getImagen() {return this.imagen;}

    /**
     * @return nombre de la cara posterior
     */
    public String GetNomgetNombrere() {return this.nombre;}

    /**
     * Cambia la imagen de la cara posterior
     * @param nuevaImagen
     */
    public void setImagen(Image nuevaImagen) {this.imagen = nuevaImagen;}

    /**
     * Cambia el nombre de la cara posterior
     * @param nuevoNombre
     */
    public void setNombre(String nuevoNombre) {this.nombre = nuevoNombre;}
}
