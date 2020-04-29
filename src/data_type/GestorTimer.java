/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_type;

import java.time.Duration;
import java.util.Timer;

/**
 *
 * @author Javier
 */
public class GestorTimer {
    private Timer timer;
    private Long startTime = 0L, endTime = 0L;
    
    public GestorTimer(){
        this.timer = new Timer();
    }
    
    public GestorTimer(Timer timer){
        this.timer = timer;
    }
    
    public Timer getTimer(){ return timer; }
    
    public void setTimer(Timer nuevoTimer){ this.timer = nuevoTimer;}
    
    public void stopTimer(){
        timer.cancel();
        endTime = System.currentTimeMillis();
    }
    
    public void startTimer(){
        startTime = System.currentTimeMillis();
    }
    
    public Duration getTimeLasted() {
        return Duration.ofMillis(endTime - startTime);
    }
}
