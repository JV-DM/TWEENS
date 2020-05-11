package data_type.Puntuacion;

public class DecoradorParejaIncorrecta extends DecoradorConcreto {
    Decorador decorador;
    public DecoradorParejaIncorrecta(Decorador decorador){
        this.decorador = decorador;
    }

    @Override
    public int getPuntos() {
        return decorador.getPuntos() - 3;
    }
}
