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
public class GestorBarajas {
    private List<Baraja> barajas;
    private Baraja barajaPorDefecto;
    public GestorBarajas(){
        cargarBarajaPorDefecto();
    }
    
    public List<Baraja> getBarajas(){return barajas;}
    public Baraja getBarajaPorDefecto(){return barajaPorDefecto;}
    
    public void setBarajas(List<Baraja> nuevaBarajas){this.barajas = nuevaBarajas;}
    public void setBarajaPorDefecto(Baraja nuevaBarajaPorDefecto){this.barajaPorDefecto = nuevaBarajaPorDefecto;}
    
    public boolean añadirBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(!ExisteLaBaraja(baraja)) {
            barajas.add(baraja);
            return true;
        }
        return false;
    }
    
    public boolean eliminarBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(ExisteLaBaraja(baraja)){
            barajas.remove(baraja);
            return true;
        }
        return false;
    }
    
    //El método será sustituido en el sprint 2
    public void cargarBarajaPorDefecto(){
        CaraPosterior caraPosterior = new CaraPosterior(new Imagen("src\\imagenes\\ImagenesCaraPosterior\\BacCard"),"CaraPosterior por defecto");
        Baraja baraja = new Baraja(null,caraPosterior,"Baraja de animales",0);
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Elefante"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\aguila_carta"),"Aguila"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\caballodemar_carta"),"Caballo de mar"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Cocodrilo"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Jirafa"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Leon"));
        baraja.AñadirCarta( new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Serpiente"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Tiburon"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Tortuga"));
        baraja.AñadirCarta(new Carta(new Imagen("src\\imagenes\\ImagenesCartas\\elefante_carta"),"Zebra"));
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
