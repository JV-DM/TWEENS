package data_type.Puntuacion;

public class DecoradorLogroParejasSeguidas extends DecoradorConcreto {

    public DecoradorLogroParejasSeguidas(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + 50;
    }
}
