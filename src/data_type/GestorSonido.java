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

    public GestorSonido(){}
    public void playCorrectSound(){
        Media sound = new Media(new File(SONIDO_CORRECTO).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void playErrorSound(){
        Media sound = new Media(new File(SONIDO_INCORRECTO).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void playCartaSound(){
        Media sound = new Media(new File(SONIDO_CARTA).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void playDerrotaSound(){
        Media sound = new Media(new File(SONIDO_DERROTA).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    public void playVictoriaSound(){
        Media sound = new Media(new File(SONIDO_VICTORIA).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
