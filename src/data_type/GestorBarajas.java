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
    private String nombreBarajaPorDefecto = "Baraja de animales";
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
    
    
    /**
     * Método que carga las barajas que hay en la carpeta de Barajas en 
     * src\imagenes\Barajas
     */
    public void cargarBarajas(){
        Baraja baraja = new Baraja();
        File directorioBarajas = new File(RUTA_BARAJAS);
        String[] listaDeBarajas = directorioBarajas.list();      
        for (String listaDeBaraja : listaDeBarajas) {
            baraja = new Baraja();
            File carpetaBaraja = new File(RUTA_BARAJAS + listaDeBaraja + "/");
            String[] listaCartasBaraja = carpetaBaraja.list();
            for(int j = 0; j < listaCartasBaraja.length; j++)
                baraja.añadirCarta(new Carta(new Image(RUTA_IMAGENES
                        + listaDeBaraja + "/"
                        + listaCartasBaraja[j]), listaCartasBaraja[j], j));
            int index = listaDeBaraja.indexOf(";");           
            baraja.setNombre(listaDeBaraja.substring(0,index));
            baraja.setTematica(listaDeBaraja.substring(index + 1));
            baraja.setCaraPosterior(caraPosterior);
            añadirBaraja(baraja);  
        }
             
    }
    
    /**
     * Carga la baraja por defecto
     */
    public void cargarBarajaPorDefecto(){
       barajaPorDefecto = buscarBaraja(nombreBarajaPorDefecto);
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
    
    public Baraja buscarBaraja(String nombreBaraja){       
        for (Baraja baraja : barajas) 
            if(baraja.getNombre().equals(nombreBaraja))
                return baraja;              
    return null;
    }
}
