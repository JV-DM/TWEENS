/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twins;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class GestorBarajas {
    private List<Baraja> barajas;
    private Baraja barajaPorDefecto;
    
    public GestorBarajas() throws IOException{
        barajas = new ArrayList();
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
    
    public boolean ExisteLaBaraja(Baraja barajaNueva){
        for(Baraja barajaExistente : barajas) {
            if(barajaExistente.EqualsTo(barajaNueva)) return true;
        }
        return false;       
    }
    
    //El método será sustituido en el sprint 2
    public void CargarBarajaPorDefecto() throws IOException{
        CaraPosterior caraPosterior = new CaraPosterior(new Imagen(new File("src\\imagenes\\ImagenesCaraPosterior\\BacCard.png")),"CaraPosterior por defecto");
        Baraja baraja = new Baraja(null,caraPosterior,"Baraja de animales",0);
        
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\elefante_carta.jpg")),"Elefante"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\aguila_carta.jpg")),"Aguila"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\caballodemar_carta.jpg")),"Caballo de mar"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\cocodrilo_carta.jpg")),"Cocodrilo"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\jirafa_carta.jpg")),"Jirafa"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\leon_carta.jpg")),"Leon"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\serpiente_carta.jpg")),"Serpiente"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\tiburon_carta.jpg")),"Tiburon"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\tortuga_carta.jpg")),"Tortuga"));
        baraja.AñadirCarta(new Carta(new Imagen(new File("src\\imagenes\\ImagenesCartas\\zebra_carta.jpg")),"Zebra"));
        AñadirBaraja(baraja);
        barajaPorDefecto = baraja;      
    }
    
}
