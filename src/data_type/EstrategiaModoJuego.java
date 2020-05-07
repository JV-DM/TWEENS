package data_type;

public abstract class EstrategiaModoJuego {
    Partida partida;
    public EstrategiaModoJuego(Partida partida){}

    public EstrategiaModoJuego() {}

    public void pickCard(Carta card){}

    protected boolean checkCardsCombination() {
        if(partida.getSelectedCards().isEmpty())
            return false;

        int firstId = partida.getSelectedCards().get(0).getId();

        return partida.getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }

    public void setPartida(Partida p){
        this.partida = p;
    }
}
