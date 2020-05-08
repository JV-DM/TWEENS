package data_type;

import javafx.scene.image.Image;
import view.mainViewController;
import java.time.Duration;
import java.util.*;
import java.util.Timer;

public class Partida {
    private Baraja baraja;
    private Image background;
    private int errorCounter;
    private List<Carta> selectedCards = new ArrayList<>();
    private boolean isFinished = false;
    private Timer timer;
    private boolean running;
    private int puntuacion = 30;
    private long startTime = 0L, endTime = 0L;
    private mainViewController controller;
    private EstrategiaModoJuego modoJuego;
    private boolean victoria = false;
    

    public Partida(Baraja b, Image back){
        this.baraja = b;
        this.background = back;
        errorCounter = 0;
        running = false;
        timer = new Timer();
    }

    /**
     * @return Lista de cartas seleccionadas
     */
    public List<Carta> getSelectedCards(){
        return this.selectedCards;
    }

    /**
     * Selecciona una carta y la pone como seleccionada
     * @param card
     */
    public void pickCard(Carta card){
        modoJuego.pickCard(card);
    }

    /**
     *
     * @return true si se ha completado el tablero
     */
    public boolean isGameCompleted(){
        return baraja.getCartas().stream().allMatch(Carta::isFound);
    }

    /**
     * limpia la lista de cartas seleccionadas
     */
    public void clearSelection(){
        selectedCards.clear();
    }

    public int getPuntuacion(){
        return puntuacion;
    }


    /**
     * Sets the controller of the game
     * @param controller
     */
    public void setController(mainViewController controller) {
        this.controller = controller;
    }
    /**
     * Incrementa en 1 el número de errores
     */
    public void increaseErrors(){
        errorCounter += 1;
    }

    public void setPuntuacion(int n){ puntuacion = n;}

    /**
     * @return Devuelve la baraja de la partida
     */
    public Baraja getBaraja() { return this.baraja;}

    /**
     * @return Fondo de pantalla de la partida
     */
    public Image getBackground(){ return this.background;}

    /**
     * @return devuelve el timer de la partida
     */
    public Timer getTimer(){ return timer;}

    /**
     * Para el tiempo de la partida
     */
   public void stopTimer(){
        timer.cancel();
        if(isGameCompleted()) {
            this.victoria = true;
            baraja.resetBaraja();
        }
        controller.pantallaFinPartida(victoria);
        controller.actualizarPerfil();
    }

    /**
     * @return Duración de la partida
     */
    public Duration getTimeLasted() {
        return Duration.ofMillis((isRunning() ? System.currentTimeMillis() : endTime) - startTime);
    }

    /**
     * @return true si la partida está en curso
     */
    public boolean isRunning() {
        return running;
    }

    public boolean isFinished(){
        return isFinished;
    }
    
    /**
     * Devuelve si la partida está ganada
     * @return 
     */
    public boolean isVictoria(){
        return victoria;
    }
    /**
     * Empieza la partida
     */
    public void startGame() {
        if (isRunning())
            return;
        running = true;
        startTime = System.currentTimeMillis();
    }

    public void finish(){ this.isFinished = true;}

    public mainViewController getController(){
        return controller;
    }
    public void setModoJuego(EstrategiaModoJuego modoJuego){
        this.modoJuego = modoJuego;
    }
}