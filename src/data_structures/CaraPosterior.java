/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author Javier
 */
public class CaraPosterior {
    private Imagen imagen;
    private String nombre;

    public CaraPosterior(Imagen imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Imagen GetImagen() {return this.imagen;}
    public String GetNombre() {return this.nombre;}

    public void SetImagen(Imagen nuevaImagen) {this.imagen = nuevaImagen;}
    public void SetNombre(String nuevoNombre) {this.nombre = nuevoNombre;}
}
