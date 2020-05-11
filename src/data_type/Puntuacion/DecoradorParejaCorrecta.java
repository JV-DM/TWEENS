package data_type.Puntuacion;

public class DecoradorParejaCorrecta implements Decorador{
    Decorador decorador;
    public DecoradorParejaCorrecta(Decorador decorador){
        this.decorador = decorador;
    }

    @Override
    public int getPuntos() {
        return decorador.getPuntos() + 10;
    }
}
