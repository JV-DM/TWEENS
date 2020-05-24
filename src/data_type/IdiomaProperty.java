/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Javier
 */
public class IdiomaProperty extends Properties {
    private static final long serialVersionUID = 1L;
    private static final String RUTA_FICHEROS = "\\src\\idiomas\\";
    Properties prop = new Properties();
    InputStream is = null;
 
    public IdiomaProperty(Idioma idioma){
        getProperties(System.getProperty("user.dir") + RUTA_FICHEROS + idioma.name() + ".properties");
    }
    
    public Properties getProp(){return prop;}

    private void getProperties(String idioma) {
        try {
            is = new FileInputStream(idioma);
	    prop.load(is);
        } catch (IOException ex) {
 
        }
   }
}
   
