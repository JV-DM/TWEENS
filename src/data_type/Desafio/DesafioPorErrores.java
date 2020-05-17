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
public class DesafioPorErrores implements Desafio{

    private String nombre;
    private String descripcion;
    private int erroresParaDesafio;
    
    public DesafioPorErrores(String nombre, String descripcion, int erroresParaDesafio){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.erroresParaDesafio = erroresParaDesafio;
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
     * Devuelve los errores para conseguir el desafio
     * @return 
     */
    public int getErroresParaDesafio(){return erroresParaDesafio;}
    
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
     * Establece los errores para conseguir el desafio
     * @param nuevoErroresParaDesafio 
     */
    public void setErroresParaDesafio(int nuevoErroresParaDesafio){
        this.erroresParaDesafio = nuevoErroresParaDesafio;
    }
    
    /**
     * Comprueba si el desafio se ha conseguido
     * @param erroresPartida
     * @return 
     */
    @Override
    public boolean desafioConseguido(int erroresPartida) {
        return erroresPartida == erroresParaDesafio;
    }
    
}
