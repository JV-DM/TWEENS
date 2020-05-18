package data_type.Puntuacion;

public class DecoradorLogroFinPartidaRapido extends DecoradorConcreto{
    public DecoradorLogroFinPartidaRapido(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }
    @Override
    public int getPuntos() {
        return super.getPuntos() + 20;
    }
}
