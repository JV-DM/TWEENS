package data_type;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
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
    boolean running;
    private long startTime = 0L, endTime = 0L;
    private int puntuacion = 0;
    private mainViewController controller;

    public Partida(Baraja b, Image back){
        this.baraja = b;
        this.background = back;
        errorCounter = 0;
        running = false;
        timer = new Timer();
    }
    public List<Carta> getSelectedCards(){
        return this.selectedCards;
    }

    public void pickCard(Carta card){
        if(!baraja.GetCartas().contains(card) || card.isFound())
            return;

        //si la carta ya está seleccionada no la coje
        if(getSelectedCards().stream().anyMatch(x -> x.getUuid() == card.getUuid())){
            return;
        }

        getSelectedCards().add(card);

        if(!checkCardsCombination() && getSelectedCards().size() == 2){
            increaseErrors();
            clearSelection();
            puntuacion -= 3;
        }
        if(checkCardsCombination() && getSelectedCards().size() == 2){
            selectedCards.stream().forEach(x -> x.foundCard());
            System.out.println("cartas encontradas");
            clearSelection();
            puntuacion += 10;

            if(isGameCompleted())
                stopTimer();

        }
        if (controller != null)
             controller.setPuntuacion(puntuacion);
    }

    private boolean isGameCompleted(){
        return baraja.GetCartas().stream().allMatch(Carta::isFound);
    }

    public void clearSelection(){
        selectedCards.clear();
    }

    public void increaseErrors(){
        errorCounter += 1;
    }

    public void setController(mainViewController controller) {
        this.controller = controller;
    }

    public boolean checkCardsCombination(){
        if(getSelectedCards().isEmpty())
            return false;

        int firstId = getSelectedCards().get(0).getId();

        return getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }

    public Baraja getBaraja() { return this.baraja;}
    public Image getBackground(){ return this.background;}

    public Timer getTimer(){ return timer;}

    public void stopTimer(){
        timer.cancel();
    }

    public Duration getTimeLasted() {
        return Duration.ofMillis((isRunning() ? System.currentTimeMillis() : endTime) - startTime);
    }
    public boolean isRunning() {

        return running;
    }
    public void startGame(){
        if(isRunning())
            return;
        if(this.getBaraja().GetCartas().isEmpty())
            buildCards();
        running = true;
        startTime = System.currentTimeMillis();
    }

    private void buildCards() {
        Random r = new Random();
        List<Carta> copiaCartasDesorden = new ArrayList<>();
        for(int i = 0; i < this.getBaraja().GetCartas().size(); i++){
            Carta c = this.getBaraja().GetCartas().remove(r.nextInt(this.getBaraja().GetCartas().size()));
        }
    }


}
