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
    private String nombre;
    private Image imagen;
    private Idioma idioma;
    private Baraja barajaMasUtilizada;
    private Tablero tableroPorDefecto;
    private int puntuacionTotal;
    private int puntuacionMaxima;
    private int victorias;
    private int derrotas;
    
    public Perfil(){
        idioma = Idioma.Español;
        nombre = "Jugador";
        imagen = new Image("imagenes/ImagenesSistema/avatar.png");
        tableroPorDefecto = new Tablero();
        puntuacionTotal = 0;
        puntuacionMaxima = 0;
        victorias = 0;
        derrotas = 0;
    }
    
    public Perfil(Image image, Idioma language, String name, 
            Baraja barajaMasUtilizada, Tablero tableroPorDefecto,
            int puntuacionTotal, int puntuacionMaxima,
            int victorias, int derrotas){
        this.imagen = image;
        this.idioma = language;        
        this.nombre = name;
        this.barajaMasUtilizada = barajaMasUtilizada;
        this.tableroPorDefecto = tableroPorDefecto;
        this.puntuacionTotal = puntuacionTotal;
        this.puntuacionMaxima = puntuacionMaxima;
        this.victorias = victorias;
        this.derrotas = derrotas;
    }
    
    /**
     * Devuelve el nombre del perfil
     * @return 
     */
    public String getNombre(){ return nombre; }
    
    /**
     * Devuelve la imagen del perfil
     * @return 
     */
    public Image getImagen(){ return imagen; }
    
    /**
     * Devuelve el idioma preferencia del perfil
     * @return 
     */
    public Idioma getIdioma(){ return idioma; }
    
    /**
     * Devuelve el Tablero que usa el perfil
     * @return 
     */
    public Tablero getTableroPorDefecto(){return tableroPorDefecto; }
    
    /**
     * Devuelve la baaja mas usada por el perfil
     * @return 
     */
    public Baraja getBarajaMasUstilizda(){return barajaMasUtilizada;}
    
    /**
     * Devuelve la puntuacion total obtenida por el perfil
     * @return 
     */
    public int getPuntuacionTotal(){ return puntuacionTotal; }
    
    /**
     * Devuelve la puntuacion maxima obtenida en un partida por el perfil
     * @return 
     */
    public int getPuntuacionMaxima(){ return puntuacionMaxima;}
    
    /**
     * Devuelve el numero de victorias del perfil
     * @return 
     */
    public int getVictorias(){ return victorias; }
    
    /**
     * Devuelve el numero de derrotas del perfil
     * @return 
     */
    public int getDerrotas(){ return derrotas; }
    
    /**
     * Cambia el nombre del perfil
     * @param newName 
     */
    public void setName(String newName){ nombre = newName; }
    
    /**
     * Cambia la imagen del perfil
     * @param newImage 
     */
    public void setImage(Image newImage){ imagen = newImage; }
    
    /**
     * Cambia el idioma del jugador
     * @param newLanguage 
     */
    public void setLanguage(Idioma newLanguage){ idioma = newLanguage; }
    
    /**
     * Cambia la baraja más usada
     * @param nuevaBaraja 
     */
    public void setBarajaMasUtilizada(Baraja nuevaBaraja){ this.barajaMasUtilizada = nuevaBaraja; }
    
    /**
     * Cambia el tablero por defecto 
     * @param nuevoTablero 
     */
    public void setTableroPorDefecto(Tablero nuevoTablero){ this.tableroPorDefecto = nuevoTablero; }
    
    /**
     * Cambia la puntuación total
     * @param nuevaPuntuacionTotal 
     */
    public void setPuntuacionTotal(int nuevaPuntuacionTotal){this.puntuacionTotal = nuevaPuntuacionTotal; }
    
    /**
     * Cambia la puntuación máxima
     * @param nuevaPuntuacionMaxima 
     */
    public void setPuntuacionMaxima(int nuevaPuntuacionMaxima){this.puntuacionMaxima = nuevaPuntuacionMaxima; }
    
    /**
     * Cambia las victorias
     * @param nuevaVictorias 
     */
    public void setVictorias(int nuevaVictorias){ this.victorias = nuevaVictorias; }
    
    /**
     * Cambia las derrotas
     * @param nuevaDerrotas 
     */
    public void setDerrotas(int nuevaDerrotas){ this.derrotas = nuevaDerrotas; }
    
    public void cargarPerfil(){}
    
}
