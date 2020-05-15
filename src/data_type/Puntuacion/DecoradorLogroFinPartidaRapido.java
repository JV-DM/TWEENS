package data_type.Puntuacion;

public class DecoradorLogroFinPartidaRapido extends DecoradorConcreto{
    public DecoradorLogroFinPartidaRapido(Decorador decorador){
        this.decorador = decorador;
    }
    @Override
    public int getPuntos() {
        return super.getPuntos() + 20;
    }
}
