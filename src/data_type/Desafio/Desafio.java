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
public abstract class Desafio implements IDesafio{
    public int id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private boolean completado;
    
    public Desafio(int id, String nombre, String descripcion,String imagen, boolean completado){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
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
     * Develve la ruta de la imagen
     * @return 
     */
    public String getImagen(){return imagen;}
    
    /**
     * Devuelve si el desafio ha sido completado
     * @return 
     */
    public boolean getCompletado(){return completado;}
    
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
    
    public void setImagen(String nuevaImagen){this.imagen = nuevaImagen;}
    /**
     * Establece si el desafio ha sido completado
     * @param nuevoCompletado 
     */
    public void setCompletado(boolean nuevoCompletado){this.completado = nuevoCompletado;}
    
    
}
