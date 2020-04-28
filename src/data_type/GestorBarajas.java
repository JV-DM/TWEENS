/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
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
    private CaraPosterior caraPosterior = new CaraPosterior(new Image("imagenes/ImagenesCaraPosterior/BacCard.png"),"CaraPosterior por defecto");
    private final static String RUTA_BARAJAS = System.getProperty("user.dir") + "/src/imagenes/Barajas/";
    private final static String RUTA_IMAGENES = "imagenes/Barajas/";

    public GestorBarajas(){
        barajas = new ArrayList<>();
        barajaPorDefecto = new Baraja();
        cargarBarajas();
    }

    /**
     *
     * @return Lista de barajas
     */
    public List<Baraja> getBarajas(){ return barajas;}

    /**
     *
     * @return Devuelve la barajaPorDefecto
     */
    public Baraja getBarajaPorDefecto(){ return barajaPorDefecto;}

    /**
     * Establece una lista de barajas
     * @param nuevaBarajas
     */
    public void setBarajas(List<Baraja> nuevaBarajas){this.barajas = nuevaBarajas;}

    /**
     * Establece la baraja por defecto
     * @param nuevaBarajaPorDefecto
     */
    public void setBarajaPorDefecto(Baraja nuevaBarajaPorDefecto){this.barajaPorDefecto = nuevaBarajaPorDefecto;}

    /**
     * Añade barajas a la lista de barajas
     * @param baraja
     * @return
     */
    public boolean añadirBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(!existeLaBaraja(baraja)) {
            barajas.add(baraja);
            return true;
        }
        return false;
    }

    /**
     * Elimina una baraja de la lista de barajas
     * @param baraja
     * @return
     */
    public boolean eliminarBaraja(Baraja baraja){
        if(baraja == null) return false;
        else if(existeLaBaraja(baraja)){
            barajas.remove(baraja);
            return true;
        }
        return false;
    }
    
    //El método será sustituido en el sprint 2
    
    public void cargarBarajas(){
        Baraja baraja = new Baraja();
        File carpeta = new File(RUTA_BARAJAS);
        String[] listaDeBarajas = carpeta.list();      
        for (String listaDeBaraja : listaDeBarajas) {
            baraja = new Baraja();
            File carpetaBaraja = new File(RUTA_BARAJAS + listaDeBaraja + "/");
            String[] listaCartasBaraja = carpetaBaraja.list();
            for(int j = 0; j < listaCartasBaraja.length; j++)
                baraja.añadirCarta(new Carta(new Image(RUTA_IMAGENES
                        + listaDeBaraja + "/"
                        + listaCartasBaraja[j]), listaCartasBaraja[j], j));
            baraja.setNombre(listaDeBaraja);
            baraja.setCaraPosterior(caraPosterior);
            añadirBaraja(baraja);  
        }
             
    }
    
    /**
     * Carga la baraja por defecto
     */
    public void cargarBarajaPorDefecto(){
       barajaPorDefecto = barajas.get(0);
    }

    /**
     * Comprueba si existe una baraja
     * @param barajaNueva
     * @return
     */
    public boolean existeLaBaraja(Baraja barajaNueva){
        for(Baraja barajaExistente : barajas) {
            if(barajaExistente.EqualsTo(barajaNueva)) return true;
        }
        return false;       
    }
}
