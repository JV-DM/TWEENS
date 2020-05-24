package data_type.Puntuacion;

public class DecoradorLogroPrimeraCorrecta extends DecoradorConcreto {

    public DecoradorLogroPrimeraCorrecta(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + 10;
    }
}
