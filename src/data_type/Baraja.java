/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.util.ArrayList;
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
    public Baraja(){
        cartas = new ArrayList<>();
    }
    public Baraja(List<Carta> cartas, CaraPosterior caraPosterior, String nombre,
            int tamaño) {
        if(cartas != null) this.cartas = cartas;
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
    
    public boolean añadirCarta(Carta carta){
        if(carta == null) return false;
        if(cartas == null){
            cartas = new ArrayList<>();
            cartas.add(carta);
            cartas.add(new Carta(carta.getImagen(),carta.getNombre(), carta.getId()));
            tamaño += 2;
            return true;
        }
        else if(!ExisteLaCarta(carta)) {
            cartas.add(carta);
            cartas.add(new Carta(carta.getImagen(),carta.getNombre(), carta.getId()));
            tamaño += 2;
            return true;
        }
        return false;
    }
    
    public boolean EliminarCarta(Carta carta){
        if(carta == null) return false;
        else if(!ExisteLaCarta(carta)){
            cartas.remove(carta);
            cartas.remove(carta);
            tamaño-= 2;
            return true;
        }
        return false;
    }
    
    public boolean ExisteLaCarta(Carta cartaNueva){
        if(cartas == null) return false;
        return cartas.contains(cartaNueva);
    }
    
    public boolean EqualsTo(Baraja baraja){
        return this.nombre.equals(baraja.nombre);
    }
}
