/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class Historial {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Date fecha1;
    private Date fecha2;
    private Date fecha3;
    private Date fecha4;
    private Date fecha5;
    
    private final String RUTA_XML = "src\\xml\\";
    private final String NOMBRE_XML = "historial.xml";
    
    public Historial(){        
        
    }
    
    public Date getFecha1(){ return fecha1; }
    public Date getFecha2(){ return fecha2; }
    public Date getFecha3(){ return fecha3; }
    public Date getFecha4(){ return fecha4; }
    public Date getFecha5(){ return fecha5; }
    
    public void setFecha1(Date nuevaFecha1){ fecha1 = nuevaFecha1; }
    public void setFecha2(Date nuevaFecha2){ fecha2 = nuevaFecha2; }
    public void setFecha3(Date nuevaFecha3){ fecha3 = nuevaFecha3; }
    public void setFecha4(Date nuevaFecha4){ fecha4 = nuevaFecha4; }
    public void setFecha5(Date nuevaFecha5){ fecha5 = nuevaFecha5; }
    
    public void cargarHistorial() throws ParserConfigurationException, SAXException, IOException, ParseException{       
        File ficheroXML = new File(this.RUTA_XML + this.NOMBRE_XML);       
        List<String> listaDeElementos = new ArrayList();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = builder.parse(ficheroXML);
        NodeList historial = documentoXML.getElementsByTagName("historial");
        // Recorro las etiquetas
            // Cojo el nodo actual
            Node nodo = historial.item(0);
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

    private void extraerDatos(List<String> listaDeElementos) throws ParseException {
        
        if(!listaDeElementos.get(0).equals("null")){
            this.fecha1 = formato.parse(listaDeElementos.get(0));
        }
        
        if(!listaDeElementos.get(1).equals("null")){        
            this.fecha2 = formato.parse(listaDeElementos.get(1));
        }
        
        if(!listaDeElementos.get(2).equals("null")){
            this.fecha3 = formato.parse(listaDeElementos.get(2));
        }
        
        if(!listaDeElementos.get(3).equals("null")){
            this.fecha4 = formato.parse(listaDeElementos.get(3));
        }
        
        if(!listaDeElementos.get(4).equals("null")){
            this.fecha5 = formato.parse(listaDeElementos.get(4));
        }


    }
    
    public void guardarHistorial() throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        org.w3c.dom.Document documentoXML = db.newDocument();
        
        Element rankings = documentoXML.createElement("historial");
        documentoXML.appendChild(rankings);
        
        Element partida1 = documentoXML.createElement("partida1");
        partida1.appendChild(documentoXML.createTextNode(String.valueOf(this.fecha1)));
        rankings.appendChild(partida1);
        

        Element partida2 = documentoXML.createElement("partida2");
        partida2.appendChild(documentoXML.createTextNode(String.valueOf(this.fecha2)));
        rankings.appendChild(partida2);
        
        
        Element partida3 = documentoXML.createElement("partida3");
        partida3.appendChild(documentoXML.createTextNode(String.valueOf(this.fecha3)));
        rankings.appendChild(partida3);    
        
        Element partida4 = documentoXML.createElement("partida4");
        partida4.appendChild(documentoXML.createTextNode(String.valueOf(this.fecha4)));
        rankings.appendChild(partida4);    
        
        Element partida5 = documentoXML.createElement("partida5");
        partida5.appendChild(documentoXML.createTextNode(String.valueOf(this.fecha5)));
        rankings.appendChild(partida5);    
        
           
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(documentoXML);
        StreamResult result = new StreamResult(new File(this.RUTA_XML + this.NOMBRE_XML));
        
        transformer.transform(source, result);
        
        
    }
    
    public void actualizarFecha(Date fecha){
        fecha5 = fecha4;
        fecha4 = fecha3;
        fecha3 = fecha2;
        fecha2 = fecha1;
        fecha1 = fecha;        
        }
    
    }
    

