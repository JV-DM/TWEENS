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

    public Image GetImagen() {return this.imagen;}
    public String GetNombre() {return this.nombre;}

    public void SetImagen(Image nuevaImagen) {this.imagen = nuevaImagen;}
    public void SetNombre(String nuevoNombre) {this.nombre = nuevoNombre;}
}
