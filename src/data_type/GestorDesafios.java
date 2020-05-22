/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import data_type.Desafio.Desafio;
import data_type.Desafio.DesafioPorErrores;
import data_type.Desafio.DesafioPorTiempo;
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
 * @author Javier
 */
public class GestorDesafios {
    //Desafios 
    private final static String NOMBRE_DESAFIO_1 = "Se el más rápido";
    private final static String NOMBRE_DESAFIO_2 = "¡A por los niveles!";
    private final static String NOMBRE_DESAFIO_3 = "Inmortal";
    private final static String DESCRIPCION_DESAFIO_1 = "Consigue acabar el nivel en tan solo 40 segundos";
    private final static String DESCRIPCION_DESAFIO_2 = "¡Acaba dos niveles en menos de 60 segundos!";
    private final static String DESCRIPCION_DESAFIO_3 = "Gana una partida sin cometer ningún error";
    private final static String RUTA_IMAGENES_DESAFIO1 = "imagenes/Desafios/Desafio1.png";
    private final static String RUTA_IMAGENES_DESAFIO2 = "imagenes/Desafios/Desafio2.png";
    private final static String RUTA_IMAGENES_DESAFIO3 = "imagenes/Desafios/Desafio3.png";
    private final static int TIPO_DESAFIO_1 = 1;
    private final static int TIPO_DESAFIO_2 = 1;
    private final static int TIPO_DESAFIO_3 = 2;
    
    private final static int TIPO_DESAFIO_POR_TIEMPO = 1;
    private final static int TIPO_DESAFIO_POR_ERRORES = 2;
    private final static int TIPO_DESAFIO_POR_PUNTOS = 3;
    
    private final String RUTA_XML = "src\\xml\\";
    private final String NOMBRE_XML = "desafios.xml";
    
    private List<Desafio> desafios;
    private Desafio desafioEnCurso;
    
    public GestorDesafios(){
        desafios = new ArrayList();
        crearDesafios();
    }
    
    public void añadirDesafio(Desafio desafio){
        desafios.add(desafio);
    }
    
    public void crearDesafios() {
        Desafio desafio1 = new DesafioPorTiempo(1,NOMBRE_DESAFIO_1,DESCRIPCION_DESAFIO_1
                ,RUTA_IMAGENES_DESAFIO1,TIPO_DESAFIO_1,false,40);
        añadirDesafio(desafio1);
        Desafio desafio2 = new DesafioPorTiempo(2,NOMBRE_DESAFIO_2,DESCRIPCION_DESAFIO_2
                ,RUTA_IMAGENES_DESAFIO2,TIPO_DESAFIO_2,false,60);
        añadirDesafio(desafio2);
        Desafio desafio3 = new DesafioPorErrores(3,NOMBRE_DESAFIO_3,DESCRIPCION_DESAFIO_3
                ,RUTA_IMAGENES_DESAFIO3,TIPO_DESAFIO_3,false,0);
        añadirDesafio(desafio3);
    }
    
    public List<Desafio> getDesafios(){return desafios;}
    
    public Desafio getDesafioEnCurso(){return desafioEnCurso;}
    
    public void setDesafioEnCurso(Desafio nuevoDesafio){this.desafioEnCurso = nuevoDesafio;}
    
    public int getTipoDesafio(Desafio desafio){
        if(desafio instanceof DesafioPorErrores)
            return 1;
        else return 2;
    }
    
    public DesafioPorErrores getDesafioPorErrores(Desafio desafio){
         if(desafio instanceof DesafioPorErrores)
             return (DesafioPorErrores) desafio;
         return null;
    }
    
    public DesafioPorTiempo getDesafioPorTiempo(Desafio desafio){
         if(desafio instanceof DesafioPorTiempo)
             return (DesafioPorTiempo) desafio;
         return null;
    }
    
    public Desafio getDesafioPorId(int id){
        for(Desafio desafio : desafios)
            if(desafio.getId() == id) return desafio;
        return null;               
    }
    
    public void comprobarDesafioEnCurso(int errores, int puntuacion,int tiempo){
        switch(desafioEnCurso.getTipo()){
            case TIPO_DESAFIO_POR_TIEMPO: desafioEnCurso.setCompletado(desafioEnCurso.desafioCompletado(tiempo));
                                          break;
            case TIPO_DESAFIO_POR_ERRORES: desafioEnCurso.setCompletado(desafioEnCurso.desafioCompletado(errores));
                                          break;
            case TIPO_DESAFIO_POR_PUNTOS: desafioEnCurso.setCompletado(desafioEnCurso.desafioCompletado(errores));
                                          break;
            default: break;
        }
    }
    
    public void cargarDesafios() throws ParserConfigurationException, SAXException, IOException{
        File ficheroXML = new File(this.RUTA_XML + this.NOMBRE_XML);       
        List<String> listaDeElementos = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = builder.parse(ficheroXML);
        NodeList perfil = documentoXML.getElementsByTagName("desafios");
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
    }

    private void extraerDatos(List<String> listaDeElementos) {
        if(!listaDeElementos.get(0).equals("0")){
            desafioEnCurso = getDesafioPorId(Integer.valueOf(listaDeElementos.get(0)));
            if(desafioEnCurso.getCompletado() == true) desafioEnCurso = null;
        }
        int indice = 1;
        for(Desafio desafio: desafios){
            desafio.setCompletado(Boolean.valueOf(listaDeElementos.get(indice)));
            indice++;
        }
        
    }
    
    public void guardarDesafios() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = db.newDocument();
        
        Element perfil = documentoXML.createElement("desafios");
        documentoXML.appendChild(perfil);
        
        Element elementoNombre = documentoXML.createElement("desafioEnCurso");
        elementoNombre.appendChild(documentoXML.createTextNode(String.valueOf(this.desafioEnCurso.getId())));
        perfil.appendChild(elementoNombre);
        
        Element elementoD1= documentoXML.createElement("desafio1");
        elementoD1.appendChild(documentoXML.createTextNode(String.valueOf(desafios.get(0).getCompletado())));
        perfil.appendChild(elementoD1);
        Element elementoD2 = documentoXML.createElement("desafio2");
        elementoD2.appendChild(documentoXML.createTextNode(String.valueOf(desafios.get(1).getCompletado())));
        perfil.appendChild(elementoD2);
        Element elementoD3 = documentoXML.createElement("desafio3");
        elementoD3.appendChild(documentoXML.createTextNode(String.valueOf(desafios.get(2).getCompletado())));
        perfil.appendChild(elementoD3);
        
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documentoXML);
        StreamResult result = new StreamResult(new File(this.RUTA_XML + this.NOMBRE_XML));
        
        transformer.transform(source, result);
        
    
    }
}
