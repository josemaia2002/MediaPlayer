package br.ufrn.imd.modelo;

public class Music {
	
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

	public Music() {
		this.title = "Unknown";
		this.duration = 0;
		this.author = "Unknown Artist";
		this.genre = "No genre";
		this.album = "No album";
		this.path = "";
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
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void getDuration(double duration) {
		this.duration = duration;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public boolean equals(Music m) 
	{
		if(this.getPath().equals(m.getPath())) return true;
		if(!this.getTitle().equals(m.getTitle())) return false;
		if(this.getDuration() != m.getDuration()) return false;
		if(!this.getAuthor().equals(m.getAuthor())) return false;
		if(!this.getGenre().equals(m.getGenre())) return false;
		return true;
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
