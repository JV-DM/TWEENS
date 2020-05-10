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
    private int puntuacion = 0;
    private long startTime = 0L, endTime = 0L;
    private mainViewController controller;
    private EstrategiaModoJuego modoJuego;
    private boolean victoria = false;
    public GestorSonido soundManager;
    private static Partida instancia;
    private int intentos = 10;
    private boolean isNivel = false;
    private int level;

    private Partida(Baraja b, Image back){
        this.baraja = b;
        this.background = back;
        errorCounter = 0;
        running = false;
        timer = new Timer();
        soundManager = new GestorSonido();
    }

    public static Partida getInstance(Baraja baraja, Image back){
        if(instancia == null){
            instancia = new Partida(baraja,back);
        }
        return instancia;
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
    public void clearSelection(){ selectedCards.clear(); }

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

    public void setErrorCounter( int n) { errorCounter = n; }

    public void setIntentos(int i) { intentos = i; }

    public void setNivel (boolean b) { isNivel = b; }

    public void setLevel (int n) { level = n; }

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

    public int getPuntuacion(){
        return puntuacion;
    }

    public int getErrorCounter() { return errorCounter; }

    public int getIntentos() { return intentos; }

    public int getLevel() { return level; }

    /**
     * Para el tiempo de la partida
     */
   public void stopTimer(){
       if (isNivel == false){
            if(timer == null) return;
                 timer.cancel();
             if(isGameCompleted()) {
                  soundManager.playVictoriaSound();
                  this.victoria = true;
                  baraja.resetBaraja();
              }
            else{
            soundManager.playDerrotaSound();
             }
       }else if(isNivel && level == 2){
           if (timer == null) return;
           timer.cancel();
           if(isGameCompleted() && puntuacion >= 60) {
               soundManager.playVictoriaSound();
               this.victoria = true;
               baraja.resetBaraja();
           }
           else{
               soundManager.playDerrotaSound();
           }
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

    public boolean isNivel(){ return isNivel; }
    
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
        timer = new Timer();
        startTime = System.currentTimeMillis();
    }

    public void finish(){ this.isFinished = true;}

    public mainViewController getController(){
        return controller;
    }
    public void setModoJuego(EstrategiaModoJuego modoJuego){
        this.modoJuego = modoJuego;
    }
    public void setBaraja(Baraja baraja){
        this.baraja = baraja;
    }
    public void setBackground(Image image){
        this.background = image;
    }

    public void restartTimer(){
        this.timer = new Timer();
    }

    public void setTime(long tiempo){
        controller.setTime(tiempo);
    }
}