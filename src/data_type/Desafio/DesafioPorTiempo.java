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

    private int id;
    private String nombre;
    private String descripcion;
    private boolean completado;
    private int tiempoParaDesafio;
    
    public DesafioPorTiempo(int id, String nombre, String descripcion,boolean completado, int tiempoParaDesafio){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempoParaDesafio = tiempoParaDesafio;
        this.completado = completado;
    }
    
    /**
     * Devuelve la id del desafio
     * @return 
     */
    public int getId(){return id;}
    
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
     * Devuelve si el desafio ha sido completado
     * @return 
     */
    public boolean getCompletado(){return completado;}
    
    /**
     * Devuelve el tiempo para conseguir el desafio
     * @return 
     */
    public int getTiempoParaDesafio(){return tiempoParaDesafio;}
    
    /**
     * Establece la id del desafio
     * @param nuevaId 
     */
    public void setId(int nuevaId){this.id = nuevaId;}
    
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
     * Establece si el desafio ha sido completado
     * @param nuevoCompletado 
     */
    public void setCompletado(boolean nuevoCompletado){this.completado = nuevoCompletado;}
    
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
