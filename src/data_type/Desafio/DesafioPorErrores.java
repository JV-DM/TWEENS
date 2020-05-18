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

    public int id;
    private String nombre;
    private String descripcion;
    private boolean completado;
    private int erroresParaDesafio;
    
    public DesafioPorErrores(int id, String nombre, String descripcion, boolean completado, int erroresParaDesafio){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completado = completado;
        this.erroresParaDesafio = erroresParaDesafio;
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
     * Devuelve los errores para conseguir el desafio
     * @return 
     */
    public int getErroresParaDesafio(){return erroresParaDesafio;}
    
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
