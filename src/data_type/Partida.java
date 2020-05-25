package data_type;

import data_type.Desafio.Desafio;
import data_type.Puntuacion.IPuntuacion;
import data_type.Puntuacion.Puntuacion;
import javafx.scene.image.Image;
import view.mainViewController;
import java.time.Duration;
import java.util.*;
import java.util.Timer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Partida {
    private Baraja baraja;
    private Image background;
    private int errorCounter;
    private List<Carta> selectedCards = new ArrayList<>();
    private boolean isFinished = false;
    private Timer timer;
    private boolean running;
    private IPuntuacion IPuntuacion = new Puntuacion();
    private long startTime = 0L, endTime = 0L;
    private mainViewController controller;
    private EstrategiaSeleccion modoJuego;
    private boolean victoria = false;
    public GestorSonido soundManager;
    private static Partida instancia;
    private int intentos;
    private boolean isNivel = false;
    private int level;
    private int parejasSeguidas;
    public boolean esPrimera;
    private boolean sonido = true;
    private Desafio desafio;
    private List<CartaCategoria> selectedCardsCategoria = new ArrayList<>();
    private String categoria;
    public boolean esDinamico;

    public Partida() {
        this.getInstance(null,null);
    }
    private Partida(Baraja b, Image back){
        this.baraja = b;
        this.background = back;
        errorCounter = 0;
        running = false;
        timer = new Timer();
        esDinamico = false;
        soundManager = new GestorSonido();
        esPrimera = true;
    }

    public static Partida getInstance(Baraja baraja, Image back){
        if(instancia == null){
            instancia = new Partida(baraja,back);
        }
        return instancia;
    }
    public String categoriaABuscar(Baraja baraja){
        if(!(baraja.getCartas().get(0) instanceof CartaCategoria))
            return null;
        CartaCategoria cartaaencontrar = null;
        Collections.shuffle(baraja.getCartas());
        if(isGameCompleted()){
            return " ";
        }
        for (int i = 0; i < baraja.getCartas().size(); i++) {
            if (!baraja.getCartas().get(i).isFound()) {
                cartaaencontrar = (CartaCategoria) baraja.getCartas().get(i);
                this.categoria = cartaaencontrar.getCategoria();
                controller.setLabelCategoria(categoria);
                return cartaaencontrar.getCategoria();
            }
        }
        this.categoria = cartaaencontrar.getCategoria();
        controller.setLabelCategoria(categoria);
        return cartaaencontrar.getCategoria();

    }
    public String getCategoria(){
        return this.categoria;
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
        modoJuego.pickCard(card,this);
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

    public IPuntuacion getPuntuacion(){
        return IPuntuacion;
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

    public void setSonido(boolean nuevoSonido){sonido = nuevoSonido;}
    public boolean getSonido(){return sonido;}

    /**
     * Para el tiempo de la partida
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws javax.xml.transform.TransformerException
     */
   public void stopTimer() throws ParserConfigurationException, TransformerException{
       if (isNivel == false ||isNivel && level == 1 || isNivel && level == 3){
           if(timer == null) return;

           if(isGameCompleted()) {
               if(sonido)
                soundManager.playVictoriaSound();
               this.victoria = true;
               this.controller.comprobarDesafio();
           }
           else{
               if(sonido)
                soundManager.playDerrotaSound();
               
           }
       }else if(isNivel && level == 2){
           if (timer == null) return;

           if(isGameCompleted() && getPuntuacion().getPuntos() >= 60) {
               if(sonido)
                soundManager.playVictoriaSound();
               this.victoria = true;
           }
           else{
               if(sonido)
                soundManager.playDerrotaSound();
           }           
       }
       //timer = new Timer();
       timer.cancel();
       timer.purge();
       baraja.resetBaraja();
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
        timer = new Timer();
        startTime = System.currentTimeMillis();
    }

    public void finish(){ this.isFinished = true;}

    public mainViewController getController(){
        return controller;
    }
    public void setModoJuego(EstrategiaSeleccion modoJuego){
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

    public void setDesafio(Desafio desafio){
        this.desafio = desafio;
    }

    public void setTime(long tiempo){
        controller.setTime(tiempo);
    }
    public void setPuntuacion(IPuntuacion IPuntuacion){
        this.IPuntuacion = IPuntuacion;
    }

    public void setErrorCounter(int errors){
        errorCounter = errors;
    }
    public int getErrorCounter(){
        return errorCounter;
    }
    public void setIntentos(int intentos){
        this.intentos = intentos;
    }
    public int getIntentos(){
        return intentos;
    }

    public void setNivel (boolean b) { isNivel = b; }

    public void setLevel (int n) { level = n; }

    public int getLevel() { return level; }

    public boolean isNivel(){ return isNivel; }

    public void resetParejasSeguidas(){this.parejasSeguidas = 0;}
    public void parejaSeguida(){
        this.parejasSeguidas += 1;
    }
    public int getParejasSeguidas(){return this.parejasSeguidas;}

    public Carta cartaABuscar(Baraja baraja){
        Carta cartaaencontrar = null;
        if(isGameCompleted()){
            return cartaaencontrar;
        }

        for (int i = 0; baraja.getCartas().size() > i; i++) {
            if (!baraja.getCartas().get(i).isFound()) {
                cartaaencontrar = baraja.getCartas().get(i);
                return cartaaencontrar;
            }
        }
        return cartaaencontrar;
    }

    public List<CartaCategoria> getlSelectedCardsCategoria(){
        return this.selectedCardsCategoria;
    }
    public void setCategoria(String categoriaABuscar) {
        this.categoria = categoriaABuscar;
    }

    public void setDinamico(boolean b){ esDinamico = b; }

    public boolean isEsDinamico(){ return esDinamico; }

}