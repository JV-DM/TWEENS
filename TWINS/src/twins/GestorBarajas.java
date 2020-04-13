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
        cargarBarajas();
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
    
    public void cargarBarajas(){}
    
    public boolean ExisteLaBaraja(Baraja barajaNueva){
        for(Baraja barajaExistente : barajas) {
            if(barajaExistente.EqualsTo(barajaNueva)) return true;
        }
        return false;       
    }
}
