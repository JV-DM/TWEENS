/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Javier
 */
public class Perfil {
    private String nombre;
    private String rutaImagen;
    private Idioma idioma;
    private Baraja barajaPorDefecto;
    private String rutaTableroPorDefecto;
    private int puntuacionTotal;
    private int puntuacionMaxima;
    private int victorias;
    private int derrotas;
    
    private final String RUTA_XML = "src\\xml\\";
    private final String NOMBRE_XML = "perfil.xml";
    private final String RUTA_AVATAR = "imagenes/ImagenesSistema/avatar.png";
    private final String RUTA_TABLERO_POR_DEFECTO = "imagenes/ImagenesBackground/fondo-verde.jpg";
    
    public Perfil(){
        nombre = "Jugador";
        idioma = Idioma.Español;        
        rutaImagen = RUTA_AVATAR;
        rutaTableroPorDefecto = this.RUTA_TABLERO_POR_DEFECTO;
        puntuacionTotal = 0;
        puntuacionMaxima = 0;
        victorias = 0;
        derrotas = 0;
    }
    
    public Perfil(String rutaImage, Idioma language, String name, 
            Baraja barajaPorDefecto, String tableroPorDefecto,
            int puntuacionTotal, int puntuacionMaxima,
            int victorias, int derrotas){
        this.rutaImagen = rutaImagen;
        this.idioma = language;        
        this.nombre = name;
        this.barajaPorDefecto = barajaPorDefecto;
        this.rutaTableroPorDefecto = tableroPorDefecto;
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
    public String getRutaImagen(){ return rutaImagen; }
    
    /**
     * Devuelve el idioma preferencia del perfil
     * @return 
     */
    public Idioma getIdioma(){ return idioma; }
    
    /**
     * Devuelve el Tablero que usa el perfil
     * @return 
     */
    public String getRutaTableroPorDefecto(){return rutaTableroPorDefecto; }
    
    /**
     * Devuelve la baaja mas usada por el perfil
     * @return 
     */
    public Baraja getBarajaPorDefecto(){return barajaPorDefecto;}
    
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
    public void setRutaImagen(String newImage){ rutaImagen = newImage; }
    
    /**
     * Cambia el idioma del jugador
     * @param newLanguage 
     */
    public void setLanguage(Idioma newLanguage){ idioma = newLanguage; }
    
    /**
     * Cambia la baraja más usada
     * @param nuevaBaraja 
     */
    public void setBarajaPorDefecto(Baraja nuevaBaraja){ this.barajaPorDefecto = nuevaBaraja; }
    
    /**
     * Cambia el tablero por defecto 
     * @param nuevoTablero 
     */
    public void setRutaTableroPorDefecto(String nuevoTablero){ this.rutaTableroPorDefecto = nuevoTablero; }
    
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
    
    /**
     * Comprueba si un entero es mayor que la puntuaciónMaxima
     * @param puntuacion
     * @return 
     */
    public boolean esPuntuacionMaxima(int puntuacion){
        return puntuacion > puntuacionMaxima;
    }
    
    public boolean nombrePerfilCorrecto(String nombre){
        return !nombre.equals("");
    }
    
    /**
     * Carga los datos del perfil a partir del fichero xml
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public void cargarPerfil() throws ParserConfigurationException, SAXException, IOException{       
        File ficheroXML = new File(this.RUTA_XML + this.NOMBRE_XML);       
        List<String> listaDeElementos = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = builder.parse(ficheroXML);
        NodeList perfil = documentoXML.getElementsByTagName("perfil");
        // Recorro las etiquetas
            // Cojo el nodo actual
            Node nodo = perfil.item(0);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element elemento = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = elemento.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        // Muestro el contenido
                        listaDeElementos.add(hijo.getTextContent());
                    }
                }
            }
            extraerDatos(listaDeElementos);
            //validarImagenes();
        }
    
    /**
     * Asigna a cada variable su dato correspondiente
     * @param datos 
     */
    public void extraerDatos(List<String> datos){
        this.nombre = datos.get(0);
        this.rutaImagen = datos.get(1);
        this.idioma = Idioma.valueOf(datos.get(2));               
        this.rutaTableroPorDefecto = datos.get(3);
        this.puntuacionTotal = Integer.valueOf(datos.get(4));
        this.puntuacionMaxima = Integer.valueOf(datos.get(5));
        this.victorias = Integer.valueOf(datos.get(6));
        this.derrotas = Integer.valueOf(datos.get(7));
    }
    
    /**
     * Métoodo que comprueba si las imagenes del xml aún existen.
     * Si no, les asigna las predefinidas
     */
    public void validarImagenes(){
        File imagenPerfil = new File(rutaImagen);
        File imagenTablero = new File(rutaTableroPorDefecto);
        if(!imagenPerfil.exists()) this.rutaImagen = this.RUTA_AVATAR;
        if(!imagenTablero.exists()) this.rutaTableroPorDefecto = this.RUTA_TABLERO_POR_DEFECTO;
    }
    
    /**
     * Genera el docuemnto xml con los datos nuevos
     * @throws ParserConfigurationException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void guardarPerfil() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = db.newDocument();
        
        Element perfil = documentoXML.createElement("perfil");
        documentoXML.appendChild(perfil);
        
        Element elementoNombre = documentoXML.createElement("nombe");
        elementoNombre.appendChild(documentoXML.createTextNode(this.nombre));
        perfil.appendChild(elementoNombre);
        

        Element elementoImagen = documentoXML.createElement("imagen");
        elementoImagen.appendChild(documentoXML.createTextNode(this.rutaImagen));
        perfil.appendChild(elementoImagen);
        
        
        Element elementoIdioma = documentoXML.createElement("idioma");
        elementoIdioma.appendChild(documentoXML.createTextNode(this.idioma.toString()));
        perfil.appendChild(elementoIdioma);       
        
        Element elementoTablero = documentoXML.createElement("nomberTableroPorDefecto");
        elementoTablero.appendChild(documentoXML.createTextNode(this.rutaTableroPorDefecto));
        perfil.appendChild(elementoTablero);
        
        Element elementoPuntuacionTotal = documentoXML.createElement("puntuacionTotal");
        elementoPuntuacionTotal.appendChild(documentoXML.createTextNode(String.valueOf(this.puntuacionTotal)));
        perfil.appendChild(elementoPuntuacionTotal);
        
        Element elementoPuntuacionMaxima = documentoXML.createElement("puntuacionMaxima");
        elementoPuntuacionMaxima.appendChild(documentoXML.createTextNode(String.valueOf(this.puntuacionMaxima)));
        perfil.appendChild(elementoPuntuacionMaxima);
        
        Element elementoVictorias = documentoXML.createElement("victorias");
        elementoVictorias.appendChild(documentoXML.createTextNode(String.valueOf(this.victorias)));
        perfil.appendChild(elementoVictorias);
        
        Element elementoDerrotas = documentoXML.createElement("derrotas");
        elementoDerrotas.appendChild(documentoXML.createTextNode(String.valueOf(this.derrotas)));
        perfil.appendChild(elementoDerrotas);
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documentoXML);
        StreamResult result = new StreamResult(new File(this.RUTA_XML + this.NOMBRE_XML));
        
        transformer.transform(source, result);
        

    }
    
}
