package br.ufrn.imd.model;

import java.util.Observable;

/**
 * Class that represents a song with its attributes.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class Music extends Observable {
	
	private String title;
	private double duration;
	private String author;
	private String genre;
	private String album;
	private String path;
	
	/**
	 * Consctructs a new instance of the Music Class with the 
	 * given parameters.
	 * 
	 * @param name The name of the song.
	 * @param duration The song's duration in minutes.
	 * @param author The song's author.
	 * @param genre The song's genre.
	 * @param album The song's album.
	 * @param path The song's path.
	 */
	public Music(String name, double duration, String author, String genre, String album, String path) {
		this.title = name;
		this.duration = duration;
		this.author = author;
		this.genre = genre;
		this.album = album;
		this.path = path;
	}

	/**
	 * Consctructs a new instance of the Music Class with its path.
	 * 
	 * @param path The song's path.
	 */
	public Music(String path) {
		String[] t = path.split("/");
		this.title = t[t.length-1];
		this.duration = 0;
		this.author = "Unknown Artist";
		this.genre = "No genre";
		this.album = "No album";
		this.path = path;
	}

	/**
	 * Method that retrieves the song's path.
	 *
	 * @return The song's path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Method that sets the song's path.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Method that retrieves the song's title.
	 *
	 * @return The song's title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Method that sets the song's title.
	 */
	public void setTitle(String name) {
		this.title = name;
		setChanged();
		this.notifyObservers();
	}

	/**
	 * Method that retrieves the song's duration.
	 *
	 * @return The song's duration.
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Method that sets the song's duration.
	 */
	public void setDuration(double duration) {
		this.duration = duration;
		setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Method that retrieves the song's author.
	 *
	 * @return The song's author.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Method that sets the song's author.
	 */
	public void setAuthor(String author) {
		this.author = author;
		setChanged();
		this.notifyObservers();
	}

	/**
	 * Method that retrieves the song's genre.
	 *
	 * @return The song's genre.
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * Method that sets the song's genre.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
		setChanged();
		this.notifyObservers();
	}

	/**
	 * Method that retrieves the song's album.
	 *
	 * @return The song's album.
	 */
	public String getAlbum() {
		return album;
	}
	
	/**
	 * Method that sets the song's album.
	 */
	public void setAlbum(String album) {
		this.album = album;
		setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Method that verifies whether a song is equals to the current one.
	 * 
	 * @param m The song to be compared.
	 * @return True if the given song's path is equal, false otherwise.
	 */
	public boolean equals(Music m) 
	{
		return this.getPath().equals(m.getPath());
	}
	
	/**
	 * Method that returns a string representation of a Music object.
	 * 
	 * @return A string with a simple representation of a Music object.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title + "\t"
				+ duration + "\t"
				+ author + "\t"
				+ genre + "\t"
				+ album + "\t"
				+ path;
	}	
	
}
