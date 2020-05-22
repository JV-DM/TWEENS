package data_type;

import data_type.Puntuacion.DecoradorParejaCorrecta;
import data_type.Puntuacion.DecoradorParejaIncorrecta;
import data_type.Puntuacion.Puntuacion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class SeleccionTrios extends EstrategiaSeleccion {

    @Override
    public void pickCard(Carta card){
        if(!partida.getBaraja().getCartas().contains(card) || card.isFound())
            return;

        //si la carta ya está seleccionada no la coje
        if(partida.getSelectedCards().stream().anyMatch(x -> x.getUuid() == card.getUuid())){
            return;
        }

        partida.getSelectedCards().add(card);

        if(!checkCardsCombination() && partida.getSelectedCards().size() == 3){
            partida.increaseErrors();
            partida.clearSelection();
            if(partida.getSonido())
                partida.soundManager.playErrorSound();
            if(partida.getPuntuacion().getPuntos() >= 3)
                partida.setPuntuacion(new DecoradorParejaIncorrecta(partida.getPuntuacion()));
            else if( partida.getErrorCounter() > partida.getIntentos()) {
                partida.setPuntuacion(new Puntuacion());
                partida.finish();
                try {
                    partida.stopTimer();
                } catch (ParserConfigurationException ex) {} catch (TransformerException ex) {}
            }
        }
        if(checkCardsCombination() && partida.getSelectedCards().size() == 3){
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(new DecoradorParejaCorrecta(partida.getPuntuacion()));
            if(partida.getSonido())
                partida.soundManager.playCorrectSound();
            if(partida.isGameCompleted()) {
                partida.finish();
                try {
                    partida.stopTimer();
                } catch (ParserConfigurationException ex) {} catch (TransformerException ex) {}
            }
        }
        if (partida.getController() != null)
            partida.getController().setPuntuacion(partida.getPuntuacion().getPuntos());
    }
}
