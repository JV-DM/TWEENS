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
public class DesafioPorErrores extends Desafio{

    private int erroresParaDesafio;
    
    public DesafioPorErrores(int id, String nombre, String descripcion,String imagen, boolean completado, int erroresParaDesafio){
        super(id,nombre,descripcion,imagen,completado);
        this.erroresParaDesafio = erroresParaDesafio;
    }
    
    
    /**
     * Devuelve los errores para conseguir el desafio
     * @return 
     */
    public int getErroresParaDesafio(){return erroresParaDesafio;}
    
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
    public boolean desafioConseguido(int erroresPartida) {
        return erroresPartida == erroresParaDesafio;
    }
    
}
