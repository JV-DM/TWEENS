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
        }
        if(checkCardsCombination() && partida.getSelectedCards().size() == 3){
            partida.getSelectedCards().stream().forEach(x -> x.foundCard());
            partida.clearSelection();
            partida.setPuntuacion(partida.getPuntuacion() + 10);

            if(partida.isGameCompleted()) {
                partida.finish();
                partida.stopTimer();
            }
        }
        if (partida.getController() != null)
            partida.getController().setPuntuacion(partida.getPuntuacion());
    }

    public boolean checkCardsCombination() {
        if(partida.getSelectedCards().isEmpty())
            return false;

        int firstId = partida.getSelectedCards().get(0).getId();

        return partida.getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }
}
