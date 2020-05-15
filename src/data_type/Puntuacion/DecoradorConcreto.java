package data_type.Puntuacion;

public class DecoradorConcreto implements Decorador {
    Decorador decorador;
    public DecoradorConcreto(){}
    public DecoradorConcreto(Decorador decorador){
        this.decorador = decorador;
    }
    @Override
    public int getPuntos() {
        return 0;
    }
}
