package br.ufrn.imd.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class that handles media playing operations, such as playing and pausing.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class PlayerService {
	
	/**
	 * A MediaPlayer object.
	 */
    private MediaPlayer mediaPlayer;

    /**
     * Initializes the MediaPlayer with the provided file path.
     *
     * @param FilePath The path of the MP3 file to be played.
     */
    public PlayerService(String FilePath) {
        Media media = new Media("file:///" + FilePath.replace("\\", "/"));
        mediaPlayer = new MediaPlayer(media);
    }

    /**
     * Starts playing the file.
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * Pauses the currently playing file.
     */
    public void pause() {
        mediaPlayer.pause();
    }

    /**
     * Stops the currently playing file.
     */
    public void stop() {
        mediaPlayer.stop();
    }

    /**
     * Sets the volume level of the MediaPlayer.
     *
     * @param volume The volume level.
     */
    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    /**
     * Sets the position in the file to the specified time.
     *
     * @param seconds The time in seconds.
     */
    public void seek(double seconds) {
        mediaPlayer.seek(Duration.seconds(seconds));
    }

    /**
     * Retrieves the total duration of the loaded file.
     *
     * @return The total duration of the file.
     */
    public Duration getDuration() {
        return mediaPlayer.getTotalDuration();
    }
}


