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
public class DesafioPorTiempo extends Desafio {

    private int tiempoParaDesafio;
    
    public DesafioPorTiempo(int id, String nombre, String descripcion,String imagen, boolean completado, int tiempoParaDesafio){
        super(id,nombre,descripcion,imagen,completado);
        this.tiempoParaDesafio = tiempoParaDesafio;
    }
    
    /**
     * Devuelve el tiempo para conseguir el desafio
     * @return 
     */
    public int getTiempoParaDesafio(){return tiempoParaDesafio;}
    
    
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

    public boolean desafioConseguido(int tiempoPartida) {
        return tiempoPartida < tiempoParaDesafio;
    }
    
}
