/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

import java.util.List;

/**
 *
 * @author Javier
 */
public class GestorBarajas {
    private List<Baraja> barajas;
    private Baraja barajaPorDefecto;
    public GestorBarajas(){
        CargarBarajaPorDefecto();
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
        CaraPosterior caraPosterior = new CaraPosterior(new Imagen("src\\imagenes\\ImagenesCaraPosterior\\BacCard"),"CaraPosterior por defecto");
        Baraja baraja = new Baraja(null,caraPosterior,"Baraja de animales",0);
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Elefante", 1));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\aguila_carta"),"Aguila", 2));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\caballodemar_carta"),"Caballo de mar", 3));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Cocodrilo",4));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Jirafa",5));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Leon",6));
        baraja.AñadirCarta( new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Serpiente",7));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Tiburon",8));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Tortuga",9));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Zebra",10));
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
