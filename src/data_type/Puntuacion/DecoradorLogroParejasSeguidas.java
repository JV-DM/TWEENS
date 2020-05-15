package data_type.Puntuacion;

public class DecoradorLogroParejasSeguidas extends DecoradorConcreto {

    public DecoradorLogroParejasSeguidas(Decorador decorador){
        this.decorador = decorador;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + 50;
    }
}
