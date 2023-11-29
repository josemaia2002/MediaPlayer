package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class Playlist {
	private int id;
	private String name;
	private ArrayList<Music> songs = new ArrayList<Music>();
	
	/*
	public Playlist(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	*/
	
	public ArrayList<Music> getSongs() {
		return songs;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addSong(Music m) {
		songs.add(m);
	}
	
	public void removeSong(Music m) {
		songs.remove(m);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}