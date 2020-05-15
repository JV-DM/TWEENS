package data_type.Puntuacion;

public class DecoradorLogroPrimeraCorrecta extends DecoradorConcreto {

    public DecoradorLogroPrimeraCorrecta(Decorador decorador){
        this.decorador = decorador;
    }

    @Override
    public int getPuntos() {
        return super.getPuntos() + 10;
    }
}
