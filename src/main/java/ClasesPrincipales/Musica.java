package ClasesPrincipales;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Musica
{
    public static void playMusic()
    {
        String musicFile = Musica.class.getResource("/sounds/LOTR.mp3").toExternalForm();
        // Crear Media y MediaPlayer
        Media sound = new Media(musicFile);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Iniciar la reproducción automáticamente
        mediaPlayer.play();

    }
}
