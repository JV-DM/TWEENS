/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type.Desafio;

/**
 *
 * @author Javier
 */
public class DesafioPorTiempo implements Desafio {

    private String nombre;
    private String descripcion;
    private int tiempoParaDesafio;
    
    public DesafioPorTiempo(String nombre, String descripcion, int tiempoParaDesafio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoParaDesafio = tiempoParaDesafio;
    }
    
    /**
     * Devuelve el nombre del desafio
     * @return 
     */
    public String getNombre(){return nombre;}
    
    /**
     * Devuelve la descripcion del desafio
     * @return 
     */
    public String getDescripcion(){return descripcion;}
    
    /**
     * Devuelve el tiempo para conseguir el desafio
     * @return 
     */
    public int getTiempoParaDesafio(){return tiempoParaDesafio;}
    
    /**
     * Establece el nombre del desafio
     * @param nuevoNombre 
     */
    public void setNombre(String nuevoNombre){this.nombre = nuevoNombre;}
    
    /**
     * Establece la descripcion del desafio
     * @param nuevoDescripcion 
     */
    public void setDescripcion(String nuevoDescripcion){
        this.descripcion = nuevoDescripcion;
    }
    
    /**
     * Establece el tiempo para conseguir el desafio
     * @param nuevoTiempoParaDesafio 
     */
    public void setTiempoParaDesafio(int nuevoTiempoParaDesafio){
        this.tiempoParaDesafio = nuevoTiempoParaDesafio;
    }
    
    /**
     * Comprueba si el desafio se ha conseguido
     * @param tiempoPartida
     * @return 
     */   
    @Override
    public boolean desafioConseguido(int tiempoPartida) {
        return tiempoPartida < tiempoParaDesafio;
    }
    
}
