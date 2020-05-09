package data_type;

import javafx.scene.image.Image;

public class Nivel  extends Partida{

    private int id;
    private boolean isComplete;
    private int errorCounter;

    public Nivel(Baraja b, Image back) {
        super(b, back);
    }

    public Nivel(Baraja b, Image back, int id, boolean isComplete, int error){
        super(b, back);
        this.id = id;
        this.isComplete = isComplete;
        this.errorCounter = error;
    }

    public int getId(){return id;}

    public boolean getComplete(){return  isComplete;}

    public int getErrorCounter(){return errorCounter;}


}
