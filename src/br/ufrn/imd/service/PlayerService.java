package br.ufrn.imd.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class PlayerService extends Service<Void> {
	private final String musicFilePath;

	public PlayerService(String musicFilePath) {
		this.musicFilePath = musicFilePath;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() {
				playMusic();
				return null;
			}
		};
	}
	
	public void playMusic()  {
		Media media = new Media(getClass().getResource(musicFilePath).toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setOnEndOfMedia(() -> {
			mediaPlayer.stop();
		});
		
		mediaPlayer.play();
		
	}
}
