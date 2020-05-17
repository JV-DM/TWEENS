/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import data_type.Desafio.Desafio;
import data_type.Desafio.DesafioPorErrores;
import data_type.Desafio.DesafioPorTiempo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Javier
 */
public class GestorDesafios {
    private final static String NOMBRE_DESAFIO_1 = "Se el más rápido";
    private final static String NOMBRE_DESAFIO_2 = "¡A por los niveles!";
    private final static String NOMBRE_DESAFIO_3 = "Inmortal";
    private final static String DESCRIPCION_DESAFIO_1 = "Consigue acabar el nivel en tan solo 40 segundos";
    private final static String DESCRIPCION_DESAFIO_2 = "¡Acaba dos niveles en menos de 60 segundos!";
    private final static String DESCRIPCION_DESAFIO_3 = "Gana una partida sin cometer ningún error";
    
    private List<Desafio> desafios;
    
    public GestorDesafios(){
        desafios = new ArrayList();
    }
    
    public void añadirDesafio(Desafio desafio){
        desafios.add(desafio);
    }
    
    public void cargarDesafios(){
        Desafio desafio1 = new DesafioPorTiempo(1,NOMBRE_DESAFIO_1,DESCRIPCION_DESAFIO_1,40);
        añadirDesafio(desafio1);
        Desafio desafio2 = new DesafioPorTiempo(2,NOMBRE_DESAFIO_2,DESCRIPCION_DESAFIO_2,60);
        añadirDesafio(desafio2);
        Desafio desafio3 = new DesafioPorErrores(3,NOMBRE_DESAFIO_3,DESCRIPCION_DESAFIO_3,0);
        añadirDesafio(desafio3);
    }
    
    public List<Desafio> getDesafios(){return desafios;}
    
}
