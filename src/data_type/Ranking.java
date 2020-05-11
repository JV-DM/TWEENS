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
 * @author youssef
 */
public class Ranking {
    private int primero;
    private int segundo;
    private int tercero;
    private Perfil perfil;
    
    private final String RUTA_XML = "src\\xml\\";
    private final String NOMBRE_XML = "rankings.xml";
    
    
    public Ranking(){        
        
    }
    
    public int getPrimero(){ return primero; }
    public int getSegundo(){ return segundo; }
    public int getTercero(){ return tercero; }
    
    public void setPrimero(int newPrimero){ primero = newPrimero; }
    public void setSegundo(int newSegundo){ segundo = newSegundo; }
    public void setTercero(int newTercero){ tercero = newTercero; }
    
    public void cargarRanking() throws ParserConfigurationException, SAXException, IOException{       
        File ficheroXML = new File(this.RUTA_XML + this.NOMBRE_XML);       
        List<String> listaDeElementos = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = builder.parse(ficheroXML);
        NodeList rankings = documentoXML.getElementsByTagName("rankings");
        // Recorro las etiquetas
            // Cojo el nodo actual
            Node nodo = rankings.item(0);
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
        }

    private void extraerDatos(List<String> listaDeElementos) {
        this.primero = Integer.valueOf(listaDeElementos.get(0));
        this.segundo = Integer.valueOf(listaDeElementos.get(1));
        this.tercero = Integer.valueOf(listaDeElementos.get(2));
    }
    
    public void guardarRanking() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = db.newDocument();
        
        Element rankings = documentoXML.createElement("rankings");
        documentoXML.appendChild(rankings);
        
        Element elementoNombre = documentoXML.createElement("primero");
        elementoNombre.appendChild(documentoXML.createTextNode(String.valueOf(this.primero)));
        rankings.appendChild(elementoNombre);
        

        Element elementoImagen = documentoXML.createElement("segundo");
        elementoImagen.appendChild(documentoXML.createTextNode(String.valueOf(this.segundo)));
        rankings.appendChild(elementoImagen);
        
        
        Element elementoIdioma = documentoXML.createElement("tercero");
        elementoIdioma.appendChild(documentoXML.createTextNode(String.valueOf(this.tercero)));
        rankings.appendChild(elementoIdioma);       
        
           
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documentoXML);
        StreamResult result = new StreamResult(new File(this.RUTA_XML + this.NOMBRE_XML));
        
        transformer.transform(source, result);
        
    }
    
    public void actualizarRanking(int puntuacion){
        if(puntuacion > tercero){
            if(puntuacion > segundo){
               if(puntuacion > primero){
                   tercero = segundo;
                   segundo = primero;
                   primero = puntuacion;                   
               }else{
                   tercero = segundo;
                   segundo = puntuacion;}
            }else{tercero = puntuacion;}
        
        }
    
    }
    
    
}
