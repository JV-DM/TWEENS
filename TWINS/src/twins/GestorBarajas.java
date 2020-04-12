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
    
    public void añadirBaraja(Baraja baraja){barajas.add(baraja);}
    public void eliminarBaraja(Baraja baraja){barajas.remove(baraja);}
    
    public void cargarBarajas(){}
}
