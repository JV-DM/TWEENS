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
public class Carta {
    private final Imagen imagen;
    private final String nombre;
    
    public Carta(Imagen imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public Imagen GetImagen() {return this.imagen;}
    public String GetNombre() {return this.nombre;}

    public boolean EqualTo(Carta carta){
        return this.nombre.equals(carta.nombre);
    }
}
