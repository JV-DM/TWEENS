package data_structures;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private Baraja baraja;
    private Imagen background;
    private int errorCounter;
    private List<Carta> selectedCards = new ArrayList<>();
    private boolean isFinished = false;

    public Partida(Baraja b, Imagen back){
        this.baraja = b;
        this.background = back;
        errorCounter = 0;
    }

    public List<Carta> getSelectedCards(){
        return this.selectedCards;
    }

    public void pickCard(Carta card){

        //si la carta ya está seleccionada no la coje
        if(getSelectedCards().stream().anyMatch(x -> x.getId() == card.getId())){
            return;
        }
        if(!checkCardsCombination()){
            increaseErrors();
            clearSelection();
        } else{
            selectedCards.stream().forEach(x -> x.foundCard());

            clearSelection();

            isFinished = isGameCompleted();
        }
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
    private boolean checkCardsCombination(){
        if(getSelectedCards().isEmpty())
            return false;

        int firstId = getSelectedCards().get(0).getId();

        return getSelectedCards().stream().allMatch(x -> x.getId() == firstId);
    }

    public Baraja getBaraja() { return this.baraja;}
    public Imagen getBackground(){ return this.background;}

}
