package data_type;

public class ModoTrios extends EstrategiaModoJuego {

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
            partida.setPuntuacion( partida.getPuntuacion() - 3);
            partida.soundManager.playErrorSound();
        }
        if(checkCardsCombination() && partida.getSelectedCards().size() == 3){
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(partida.getPuntuacion() + 10);
            partida.soundManager.playCorrectSound();
            if(partida.isGameCompleted()) {
                partida.finish();
                //partida.stopTimer();
            }
        }
        if (partida.getController() != null)
            partida.getController().setPuntuacion(partida.getPuntuacion());
    }
}
