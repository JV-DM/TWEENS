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
    private CaraPosterior caraPosterior;
    private int tamaño;
    
    public Baraja(List<Carta> cartas, String nombre, CaraPosterior caraPosterior,
            int tamaño) {
        this.cartas = cartas;
        this.nombre = nombre;
        this.caraPosterior = caraPosterior;
        this.tamaño = tamaño;
    }
    
    public List<Carta> GetCartas(){return this.cartas;}
    public String GetNombre(){return this.nombre;}
    public CaraPosterior GetCaraPosterior(){return this.caraPosterior;}
    public int GetTamaño(){return this.tamaño;}
    
    public void SetCartas(List<Carta> nuevaCartas){this.cartas = nuevaCartas;}
    public void SetNombre(String nuevoNombre){this.nombre = nuevoNombre;}
    public void SetCaraPosterior(CaraPosterior nuevaCaraPosterior){
        this.caraPosterior = nuevaCaraPosterior;
    }
    public void SetTamaño(int nuevoTamaño){this.tamaño = nuevoTamaño;}
    
    public void AñadirCarta(Carta carta){
        cartas.add(carta); tamaño++;
    }
    public void EliminarCarta(Carta carta){
        cartas.remove(carta); tamaño--;
    }
}
