/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import javafx.scene.image.Image;

/**
 *
 * @author Javier
 */
public class Perfil {
    private String name;
    private Image image;
    private Idioma language;
    
    public Perfil(Image image, Idioma language, String name){
        this.image = image;
        this.language = language;        
        this.name = name;
    }
    
    /**
     * Devuelve el nombre del perfil
     * @return 
     */
    public String getName(){ return name; }
    
    /**
     * Devuelve la imagen del perfil
     * @return 
     */
    public Image getImage(){ return image; }
    
    /**
     * Devuelve el idioma preferencia del perfil
     * @return 
     */
    public Idioma getLanguage(){ return language; }
    
    /**
     * Cambia el nombre del perfil
     * @param newName 
     */
    public void setName(String newName){ name = newName; }
    
    /**
     * Cambia la imagen del perfil
     * @param newImage 
     */
    public void setImage(Image newImage){ image = newImage; }
    
    /**
     * Cambia el idioma del jugador
     * @param newLanguage 
     */
    public void setLanguage(Idioma newLanguage){ language = newLanguage; }
}
