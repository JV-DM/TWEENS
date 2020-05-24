package data_type.Puntuacion;

public class DecoradorParejaIncorrecta extends DecoradorConcreto {
    public DecoradorParejaIncorrecta(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }

    @Override
    public int getPuntos() {
        return IPuntuacion.getPuntos() - 3;
    }
}
