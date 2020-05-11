package data_type;

import data_type.Puntuacion.DecoradorParejaCorrecta;
import data_type.Puntuacion.DecoradorParejaIncorrecta;

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
            partida.setPuntuacion(new DecoradorParejaIncorrecta(partida.getPuntuacion()));
            partida.soundManager.playErrorSound();
        }
        if(checkCardsCombination() && partida.getSelectedCards().size() == 3){
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(new DecoradorParejaCorrecta(partida.getPuntuacion()));
            partida.soundManager.playCorrectSound();
            if(partida.isGameCompleted()) {
                partida.finish();
                partida.stopTimer();
            }
        }
        if (partida.getController() != null)
            partida.getController().setPuntuacion(partida.getPuntuacion().getPuntos());
    }
}
