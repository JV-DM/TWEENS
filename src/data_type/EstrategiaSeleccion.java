package data_type;

public abstract class EstrategiaSeleccion {
    Partida partida;
    public EstrategiaSeleccion(Partida partida){}

    public EstrategiaSeleccion() {}

    public void pickCard(Carta card){}

    protected boolean checkCardsCombination() {
        partida.soundManager.playCartaSound();
        if(partida.getSelectedCards().isEmpty())
            return false;

        int firstId = partida.getSelectedCards().get(0).getId();

        return partida.getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }

    public void setPartida(Partida p){
        this.partida = p;
    }
}
