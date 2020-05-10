package data_type;

public class ModoJuegoNormal extends EstrategiaModoJuego {
    public ModoJuegoNormal(){};
    public ModoJuegoNormal(Partida partida){
        this.partida = partida;
    }
    @Override
    public void pickCard(Carta card){

        if(!partida.getBaraja().getCartas().contains(card) || card.isFound())
            return;

        //si la carta ya está seleccionada no la coje
        if(partida.getSelectedCards().stream().anyMatch(x -> x.getUuid() == card.getUuid())){
            return;
        }

        partida.getSelectedCards().add(card);

        if(!checkCardsCombination() && partida.getSelectedCards().size() == 2){
            partida.increaseErrors();
            partida.clearSelection();
            partida.soundManager.playErrorSound();
            if (partida.getPuntuacion() >= 3) {
                partida.setPuntuacion(partida.getPuntuacion() - 3);
            } else if (partida.getErrorCounter() > partida.getIntentos()){
                partida.setPuntuacion(0);
                partida.finish();
                partida.stopTimer();
            }


        }
        if(checkCardsCombination() && partida.getSelectedCards().size() == 2){
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(partida.getPuntuacion() + partida.getIntentos());
            partida.soundManager.playCorrectSound();
            if(partida.isGameCompleted()) {
                partida.finish();
                partida.stopTimer();
            }
        }
        if (partida.getController() != null){
            partida.getController().setPuntuacion(partida.getPuntuacion());
            if(partida.getErrorCounter() <= partida.getIntentos())
            partida.getController().setIntentos(partida.getIntentos() - partida.getErrorCounter());
        }
    }


}