/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Javier
 */
public class GestorArchivos {
    
    private final static String RUTA_IMAGENES_SISTEMA =  "imagenes/ImagenesSistema";
    
    private Perfil perfil;
    
    public GestorArchivos(){}
    
    public boolean copiarArchivo(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    public String getRutaImagenesSistema(){ return RUTA_IMAGENES_SISTEMA; }
    
    public Perfil getPerfil(){return perfil;}
    
    public void setPerfil(Perfil nuevoPerfil){ this.perfil = nuevoPerfil; }
}
