/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twins;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 *
 * @author Javier
 */
public class Imagen {
    private Image imagen;
    private String rutaImagen;
    
    public Imagen(File imagen) throws IOException{
        this.imagen = ImageIO.read(imagen);
    }
    
    public Image GetImage(){return imagen;}
    public String GetRutaImagen(){return rutaImagen;}
    
    public void SetImage(File imagen) throws IOException{
        this.imagen = ImageIO.read(imagen);
    }
}
