package data_type;

import data_type.Puntuacion.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionModoCarta extends EstrategiaSeleccion {

    @Override
    public void pickCard(Carta card, Partida partida) {
        if (!partida.getBaraja().getCartas().contains(card) || card.isFound())
            return;

        //si la carta ya está seleccionada no la coje
        if (partida.getSelectedCards().stream().anyMatch(x -> x.getUuid() == card.getUuid())) {
            return;
        }


        partida.getSelectedCards().add(card);

        if(partida.getSelectedCards().size() == 1 && partida.getSelectedCards().get(0).getId() != partida.cartaABuscar(partida.getBaraja()).getId()){
            partida.clearSelection();
            partida.soundManager.playErrorSound();
            partida.resetParejasSeguidas();
            partida.increaseErrors();
            if (partida.getPuntuacion().getPuntos() >= 3)
                partida.setPuntuacion(new DecoradorParejaIncorrecta(partida.getPuntuacion()));
            else if (partida.getErrorCounter() > partida.getIntentos()) {
                partida.setPuntuacion(new Puntuacion());
                partida.finish();
                try {
                    partida.stopTimer();
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (!checkCardsCombination(partida) && partida.getSelectedCards().size() == 2) {
            partida.increaseErrors();
            partida.clearSelection();
            partida.soundManager.playErrorSound();
            partida.resetParejasSeguidas();
            if(partida.esPrimera)
                partida.esPrimera = false;
            if (partida.getPuntuacion().getPuntos() >= 3)
                partida.setPuntuacion(new DecoradorParejaIncorrecta(partida.getPuntuacion()));

            else if (partida.getErrorCounter() > partida.getIntentos()) {
                partida.setPuntuacion(new Puntuacion());
                partida.finish();
                try {
                    partida.stopTimer();
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (checkCardsCombination(partida) && partida.getSelectedCards().size() == 2) {
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(new DecoradorParejaCorrecta(partida.getPuntuacion()));
            partida.soundManager.playCorrectSound();
            partida.parejaSeguida();

            if (partida.getParejasSeguidas() == 5){
                partida.setPuntuacion(new DecoradorLogroParejasSeguidas(partida.getPuntuacion()));
                partida.resetParejasSeguidas();
            }
            else if(partida.esPrimera){
                partida.setPuntuacion(new DecoradorLogroPrimeraCorrecta(partida.getPuntuacion()));
                partida.esPrimera = false;
            }
            if (partida.isGameCompleted()) {
                partida.finish();
                try {
                    partida.stopTimer();
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(SeleccionNormal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (partida.getController() != null)
            partida.getController().setPuntuacion(partida.getPuntuacion().getPuntos());
        if (partida.getErrorCounter() <= partida.getIntentos())
            partida.getController().setIntentos(partida.getIntentos() - partida.getErrorCounter());
    }
}