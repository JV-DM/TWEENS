/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class GestorBarajas {
    private List<Baraja> barajas;
    private Baraja barajaPorDefecto;
    public GestorBarajas(){
        barajas = new ArrayList<>();
        barajaPorDefecto = new Baraja();
    }
    
    public List<Baraja> GetBarajas(){return barajas;}
    public Baraja GetBarajaPorDefecto(){return barajaPorDefecto;}
    
    public void SetBarajas(List<Baraja> nuevaBarajas){this.barajas = nuevaBarajas;}
    public void SetBarajaPorDefecto(Baraja nuevaBarajaPorDefecto){this.barajaPorDefecto = nuevaBarajaPorDefecto;}
    
    public boolean AñadirBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(!ExisteLaBaraja(baraja)) {
            barajas.add(baraja);
            return true;
        }
        return false;
    }
    
    public boolean EliminarBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(ExisteLaBaraja(baraja)){
            barajas.remove(baraja);
            return true;
        }
        return false;
    }
    
    //El método será sustituido en el sprint 2
    public void CargarBarajaPorDefecto(){
        CaraPosterior caraPosterior = new CaraPosterior(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"),"CaraPosterior por defecto");
        Baraja baraja = new Baraja(null,caraPosterior,"Baraja de animales",0);
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/elefante_carta.jpg"),"Elefante", 1));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/aguila_carta.jpg"),"Aguila", 2));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/caballodemar_carta.jpg"),"Caballo de mar", 3));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/cocodrilo_carta.jpg"),"Cocodrilo",4));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/jirafa_carta.jpg"),"Jirafa",5));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/leon_carta.jpg"),"Leon",6));
        baraja.añadirCarta( new Carta(new Image("imagenes/ImagenesCartas/serpiente_carta.jpg"),"Serpiente",7));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/tiburon_carta.jpg"),"Tiburon",8));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/tortuga_carta.jpg"),"Tortuga",9));
        baraja.añadirCarta(new Carta(new Image("imagenes/ImagenesCartas/zebra_carta.jpg"),"Zebra",10));

        barajas.add(baraja);
        barajaPorDefecto = baraja;
    }
    
    public boolean ExisteLaBaraja(Baraja barajaNueva){
        for(Baraja barajaExistente : barajas) {
            if(barajaExistente.EqualsTo(barajaNueva)) return true;
        }
        return false;       
    }
}
