package br.ufrn.imd.model;

import java.util.Observable;

public class Music extends Observable {
	
	private String title;
	private double duration;
	private String author;
	private String genre;
	private String album;
	private String path;
	
	public Music(String name, double duration, String author, String genre, String album, String path) {
		this.title = name;
		this.duration = duration;
		this.author = author;
		this.genre = genre;
		this.album = album;
		this.path = path;
	}

	public Music(String path) {
		String[] t = path.split("/");
		this.title = t[t.length-1];
		this.duration = 0;
		this.author = "Unknown Artist";
		this.genre = "No genre";
		this.album = "No album";
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String name) {
		this.title = name;
		setChanged();
		this.notifyObservers();
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
		setChanged();
		this.notifyObservers();
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
		setChanged();
		this.notifyObservers();
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
		setChanged();
		this.notifyObservers();
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
		setChanged();
		this.notifyObservers();
	}
	
	public boolean equals(Music m) 
	{
		return this.getPath().equals(m.getPath());
	}

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
