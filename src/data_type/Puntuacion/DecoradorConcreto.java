package data_type.Puntuacion;

public class DecoradorConcreto implements IPuntuacion {
    IPuntuacion IPuntuacion;
    public DecoradorConcreto(){}
    public DecoradorConcreto(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }
    @Override
    public int getPuntos() {
        return 0;
    }
}
