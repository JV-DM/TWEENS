/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Javier
 */
public class GestorArchivos {
    
    private final String RUTA_BARAJAS = System.getProperty("user.dir") + "/src/imagenes/Barajas/";
    
    public GestorArchivos(){}
    
    /**
     * Devuelve la ruta donde se enuentran las barajas
     * @return 
     */
    public String getRuta_Barajas(){return RUTA_BARAJAS;}
    
    /**
     * Copia el fichero en el sitio destino
     * @param file
     * @param destino
     * @throws IOException 
     */
    public void copyFile(File file, String destino) throws IOException{
        Path from = Paths.get(file.getPath());
        System.out.println();
        Path to = Paths.get(destino);
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING
        }; 
        Files.copy(from, to, options);
    }    
    
    /**
     * Borra un fichero
     * @param file
     * @return 
     */
    public boolean deleteFile(File file){
        if(file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }
    
    /**
     * Crea un directorio
     * @param ruta 
     */
    public void createDirectory(String ruta){
        File carpeta = new File(ruta);
        carpeta.mkdir();        
    }
    
    /**
     * Ventana para elegir un archivo de tipo imagen 
     * @param borderPane
     * @return 
     */
    public File fileChooser(BorderPane borderPane) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
         File archivoDeImagen = fileChooser.showOpenDialog((Stage) borderPane.getScene().getWindow());
         return archivoDeImagen;
    }
    
    /**
     * Renombra el fichero
     * @param file
     * @param newName 
     */
    public void renameFile(File file, String newName){
        File newFile = new File(newName + fileExtension(file));
        System.out.println(newFile.getName());
        file.renameTo(newFile);
    }
    
    /**
     * Devuelve la extensión de un fichero
     * @param file
     * @return 
     */
    public String fileExtension(File file){
        String name = file.getName();
        return name.substring(name.length() - 4, name.length());
    }
}
