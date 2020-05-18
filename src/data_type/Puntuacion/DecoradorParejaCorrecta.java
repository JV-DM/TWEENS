package data_type.Puntuacion;

public class DecoradorParejaCorrecta extends DecoradorConcreto{
    public DecoradorParejaCorrecta(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }

    @Override
    public int getPuntos() {
        return IPuntuacion.getPuntos() + 10;
    }
}
