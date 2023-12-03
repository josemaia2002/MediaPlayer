package br.ufrn.imd.service;

import java.io.File;
import java.util.ArrayList;

import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ScrollBar;
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
	
	private ArrayList<Music> songQueue;
	
	private Playlist currentPlaylist;
	
	private Music currentSong;
	
    private MediaPlayer mediaPlayer;
    
    private ScrollBar progressBar;
    
    private InvalidationListener currentTimeListener;
    

    /**
     * Initializes the MediaPlayer with the provided song.
     *
     * @param Song the music to play
     */
    public PlayerService(Music song, ScrollBar pb) {
        //Media media = new Media("file:///" + FilePath.replace("\\", "/"));
        mediaPlayer = new MediaPlayer(new Media((new File(song.getPath())).toURI().toString()));
        this.progressBar = pb;
        songQueue = new ArrayList<Music>();
        songQueue.add(song);
        currentSong = song;
        mediaPlayer.setVolume(1.0);
        play();
    }
    
    
    public Music getCurrentSong() 
    {
    	return currentSong;
    }
    
    public ArrayList<Music> getSongQueue() {
		return songQueue;
	}

	/**
     * Plays the next song in the songQueue and adds the current song to its end.
     */
    public void nextSong()
    {
    	songQueue.remove(currentSong);
    	songQueue.add(currentSong);
    	currentSong = songQueue.get(0);
    	changeMusic(currentSong);
    }
    
    /**
     * Plays the previous song in the songQueue, which is at its end.
     */
    public void prevSong()
    {
    	Music currentSong = songQueue.get(songQueue.size()-1);
    	songQueue.remove(currentSong);
    	songQueue.add(0, currentSong);
    }
    
    
    /**
     * Adds a song to the queue to be played after the current song.
     * 
     * @param Song the song to be added.
     */
    public void addNextSong(Music song)
    {
    	if(songQueue.contains(song)) songQueue.remove(song);
    	songQueue.add(0, song);
    }
    
    
    /**
     * Adds a song to the end of the queue.
     * 
     * @param Song the song to be added.
     */
    public void addLastSong(Music song)
    {
    	if(songQueue.contains(song)) songQueue.remove(song);
    	songQueue.add(song);
    }
    
    
    /**
     * Adds a song to the queue and immediately plays it, skipping the current song.
     * 
     * @param Song the song to be added.
     */
    public void addCurrentSong(Music song)
    {
    	addNextSong(song);
    	nextSong();
    }
    
    public void removeSong(Music song) 
    {
    	if (song.equals(currentSong)){nextSong();}
    	songQueue.remove(song);
    	if(songQueue.size() <= 0 ) {
    		currentSong = null; 
    		stop(); 
    		return;
    	}
    	currentSong = songQueue.get(0);
    }
    
    /**
     * Restarts the player in order to play a new song.
     * 
     * @param Song the song to be played. 
     */
    public void changeMusic(Music song)
    {
    	MediaPlayer.Status previousStatus = mediaPlayer.getStatus();
    	double volume = mediaPlayer.getVolume();
    	mediaPlayer.stop();
    	mediaPlayer.dispose();
    	mediaPlayer = new MediaPlayer(new Media((new File(song.getPath())).toURI().toString()));
    	setVolume(volume);
    	if(previousStatus.equals(MediaPlayer.Status.PLAYING)) play();
    }
    
    
    public void setPlaylist(Playlist playlist) 
    {
    	currentPlaylist = playlist;
    	songQueue = playlist.getSongs();
    	changeMusic(songQueue.get(0));
    }

    /**
     * Starts playing the file.
     */
    public void play() {
    	currentTimeListener = new InvalidationListener() 
        {

    		@Override
    		public void invalidated(Observable observable) {
    			if(isPlaying()) 
    			{
    				progressBar.setValue(100*mediaPlayer.currentTimeProperty().get().toSeconds()/mediaPlayer.getMedia().getDuration().toSeconds());
    			}
    			
    		}
        };
    	mediaPlayer.currentTimeProperty().addListener(currentTimeListener);
        mediaPlayer.play();
    }

    /**
     * Pauses the currently playing file.
     */
    public void pause() {

    	mediaPlayer.currentTimeProperty().removeListener(currentTimeListener);
        mediaPlayer.pause();
    }

    /**
     * Stops the currently playing file.
     */
    public void stop() {
    	mediaPlayer.currentTimeProperty().removeListener(currentTimeListener);
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
     * @param proportion A value between 0 and 1, indicating the position to seek for.
     */
    public void seek(double proportion) {
    	double seconds = mediaPlayer.getMedia().getDuration().toSeconds()*proportion;
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
    
    /**
     * Retrieves whether the media is muted or not.
     *
     * @return True if the media is muted, false otherwise.
     */
    public boolean isMuted() 
    {
    	return mediaPlayer.isMute();
    }
    
    /**
     * Leave the media muted.
     *
     * @param mute Whether the media must be muted or not.
     */
    public void setMute(boolean mute) 
    {
    	mediaPlayer.setMute(mute);
    }
    
    public boolean isPlaying() 
    {
    	return mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING);
    }
}


