package data_type;

public abstract class EstrategiaSeleccion {
    public EstrategiaSeleccion() {}

    public void pickCard(Carta card, Partida partida){}

    protected boolean checkCardsCombination(Partida partida) {
        if(partida.getSonido())
            partida.soundManager.playCartaSound();
        if(partida.getSelectedCards().isEmpty())
            return false;

        int firstId = partida.getSelectedCards().get(0).getId();

        return partida.getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }
}
