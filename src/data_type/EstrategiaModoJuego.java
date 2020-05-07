package data_type;

public abstract class EstrategiaModoJuego {
    Partida partida;
    public EstrategiaModoJuego(Partida partida){}

    public EstrategiaModoJuego() {}

    public void pickCard(Carta card){}

    public void setPartida(Partida p){
        this.partida = p;
    }
}
