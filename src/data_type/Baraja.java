/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Javier
 */

/**
 * Constructor de la baraja
 */
public class Baraja {
    private List<Carta> cartas;
    private String nombre;
    private CaraPosterior caraPosterior;
    private int tamaño;
    private String tematica;
    
    public Baraja(){
        cartas = new ArrayList<>();
    }
    
    public Baraja(List<Carta> cartas, CaraPosterior caraPosterior, String nombre,
            int tamaño, String tematica) {
        if(cartas != null) this.cartas = cartas;
        this.nombre = nombre;
        this.caraPosterior = caraPosterior;
        this.tamaño = tamaño;
        this.tematica = tematica;
    }

    /**
     * Devuelve la lista de cartas
     * @return lista de cartas
     */
    public List<Carta> getCartas(){return this.cartas;}

    /**
     * Devuelve el nombre de la baraja
     * @return nombre de la baraja
     */
    public String getNombre(){return this.nombre;}

    /**
     * Devuelve la cara posterior de las cartas
      * @return cara posterior de las cartas de la baraja
     */
    public CaraPosterior getCaraPosterior(){return this.caraPosterior;}

    /**
     * Devuelve el tamaño de la baraja(numero de cartas)
     * @return
     */
    public int getTamaño(){return this.tamaño;}

    /**
     * DEvuelve la tematica de la baraja
     * @return 
     */
    public String getTematica() {return this.tematica;}
    
    /**
     * Cambia la lista de cartas de la baraja
     * @param nuevaCartas nueva lista de cartas
     */
    public void setCartas(List<Carta> nuevaCartas){this.cartas = nuevaCartas;}

    /**
     * Cambia el nombre de la baraja
     * @param nuevoNombre nuevo nombre de la baraja
     */
    public void setNombre(String nuevoNombre){this.nombre = nuevoNombre;}

    /**
     * Cambia la cara posterior de la baraja
     * @param nuevaCaraPosterior
     */
    public void setCaraPosterior(CaraPosterior nuevaCaraPosterior){
        this.caraPosterior = nuevaCaraPosterior;
    }

    /**
     * Cambia el tamaño de la baraja
     * @param nuevoTamaño
     */
    public void setTamaño(int nuevoTamaño){this.tamaño = nuevoTamaño;}

    /**
     * Cambia la tematica de la baraja
     * @param nuevaTematica 
     */
    public void setTematica(String nuevaTematica){this.tematica = nuevaTematica;}
    
    /**
     * Añade una carta a la baraja
     * @param carta
     */
    public void añadirCarta(Carta carta){
        if(carta == null) throw new NullPointerException();
        if(cartas == null){
            cartas = new ArrayList<>();
            cartas.add(carta);
            cartas.add(new Carta(carta.getImagen(),carta.getNombre(), carta.getId()));
            tamaño += 2;
        }
        else if(!existeLaCarta(carta)) {
            cartas.add(carta);
            cartas.add(new Carta(carta.getImagen(),carta.getNombre(), carta.getId()));
            tamaño += 2;
        }
    }

    /**
     * Elimina una carta de la baraja
     * @param carta carta a eliminar
     */
    public boolean eliminarCarta(Carta carta){
        if(carta == null) return false;
        else if(!existeLaCarta(carta)){
            cartas.remove(carta);
            cartas.remove(carta);
            tamaño-= 2;
            return true;
        }
        return false;
    }

    /**
     * @param cartaNueva
     * @return true if the card exist
     */
    public boolean existeLaCarta(Carta cartaNueva){
        if(cartas == null) return false;
        return cartas.contains(cartaNueva);
    }

    /**
     * @param baraja
     * @return true si 2 barajas son iguales
     */
    public boolean EqualsTo(Baraja baraja){
        return this.nombre.equals(baraja.nombre);
    }
    
    /**
     * Pone el atributo isFound de todas las cartas 
     * a false
     */
    public void resetBaraja(){
        cartas.forEach((carta) -> {
            carta.resetCarta();
        });
               
    }
}
