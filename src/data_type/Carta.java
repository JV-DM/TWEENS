/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.util.UUID;

/**
 *
 * @author Javier
 */
public class Carta {
    private final Image imagen;
    private final String nombre;
    private boolean isFound;

    //id para identificar los pares de cartas
    private final int id;
    //id para identificar cada carta individualmente
    private final ObjectProperty<UUID> uuid = new SimpleObjectProperty<>();

    public Carta(Image imagen, String nombre,int id) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.id = id;
        setUuid(UUID.randomUUID());
    }

    /**
     * @return Imagen de la carta
     */
    public Image getImagen() {return this.imagen;}

    /**
     * @return Nombre de la carta
     */
    public String getNombre() {return this.nombre;}

    /**
     * El id de la carta se comparte entre las parejas de cartas
     * @return Id de la carta
     */
    public int getId() {return this.id;}

    /**
     * Pone la carta como encontrada
     */
    public void foundCard(){
        isFound = true;
    }

    /**
     * @return True si la carta ya ha sido encontrada
     */
    public boolean isFound(){ return this.isFound;}

    /**
     * Comprueba que dos cartas son pareja
     * @param carta
     * @return true si las dos cartas comparten id
     */
    public boolean equalsTo(Carta carta){
        return this.id == carta.id;
    }

    /**
     * @return Id única de la carta
     */
    public UUID getUuid() {
        return uuid.get();
    }

    /**
     * @return id único de la carta
     */
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }

    /**
     * Estaablece el id único de la carta
     * @param uuid
     */
    private void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }
    
    /**
     * 
     */
    public void resetCarta(){
        this.isFound = false;
    }
}
