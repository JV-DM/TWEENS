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
    private final int id;
    private boolean isFound;

    public Carta(Imagen imagen, String nombre,int id) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.id = id;
    }

    public Imagen getImagen() {return this.imagen;}
    public String getNombre() {return this.nombre;}
    public int getId() {return this.id;}
    public void foundCard(){
        isFound = true;
    }
    public boolean isFound(){ return this.isFound;}
    public boolean EqualTo(Carta carta){
        return this.nombre.equals(carta.nombre);
    }
}
