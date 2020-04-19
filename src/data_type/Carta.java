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
    //id para identificar los pares de cartas
    private final int id;
    //id para identificar cada carta individualmente
    private final ObjectProperty<UUID> uuid = new SimpleObjectProperty<>();
    private boolean isFound;
    public Carta(Image imagen, String nombre,int id) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.id = id;
        setUuid(UUID.randomUUID());
    }

    public Image getImagen() {return this.imagen;}
    public String getNombre() {return this.nombre;}
    public int getId() {return this.id;}
    public void foundCard(){
        isFound = true;
    }
    public boolean isFound(){ return this.isFound;}
    public boolean equalsTo(Carta carta){
        return this.id == carta.id;
    }
    public UUID getUuid() {
        return uuid.get();
    }
    public ObjectProperty<UUID> uuidProperty() {
        return uuid;
    }
    private void setUuid(UUID uuid) {
        this.uuid.set(uuid);
    }
}
