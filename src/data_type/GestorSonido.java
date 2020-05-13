package data_type;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.lang.reflect.Field;

public class GestorSonido {
    private String SONIDO_CORRECTO = "src/sound/sonido_correcto.m4a";
    private String SONIDO_INCORRECTO = "src/sound/sonido_incorrecto.mp3";
    private String SONIDO_CARTA = "src/sound/sonido_carta.mp3";
    private String SONIDO_DERROTA = "src/sound/sonido_derrota.mp3";
    private String SONIDO_VICTORIA = "src/sound/sonido_victoria.mp3";
    MediaPlayer mediaPlayer;
    public GestorSonido(){}
    public void playCorrectSound(){
        new Thread(()->
        {
            Media sound = new Media(new File(SONIDO_CORRECTO).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        }).start();
    }
    public void playErrorSound(){
        new Thread(()->
        {
            Media sound = new Media(new File(SONIDO_INCORRECTO).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        }).start();
    }
    public void playCartaSound(){
        new Thread(()->
        {
            Media sound = new Media(new File(SONIDO_CARTA).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        }).start();
    }
    public void playDerrotaSound(){
        new Thread(()->
        {
            Media sound = new Media(new File(SONIDO_DERROTA).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        }).start();
    }
    public void playVictoriaSound(){
        new Thread(()->
        {
            Media sound = new Media(new File(SONIDO_VICTORIA).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        }).start();
    }
}
