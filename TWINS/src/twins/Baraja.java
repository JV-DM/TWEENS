/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twins;

import java.util.List;

/**
 *
 * @author Javier
 */
public class Baraja {
    private List<Carta> cartas;
    private String nombre;
    
    public Baraja(List<Carta> cartas, String nombre) {
        this.cartas = cartas;
        this.nombre = nombre;
    }
    
    public List<Carta> GetCartas() {return this.cartas;}
    public String GetNombre() {return this.nombre;}
    
    public void SetCartas(List<Carta> nuevaCartas) {this.cartas = nuevaCartas;}
    public void SetNombre(String nuevoNombre) {this.nombre = nuevoNombre;}
    
    public void AñadirCarta(Carta carta){cartas.add(carta);}
    public void EliminarCarta(Carta carta){cartas.remove(carta);}
}
