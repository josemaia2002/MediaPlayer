package br.ufrn.imd.model;

import java.util.ArrayList;

public class Playlist {
	private int id;
	private String name;
	private String ownerName;
	private int ownerId;
	private ArrayList<Music> songs;
	
	public Playlist(int id) {
		this.id = id;
		songs = new ArrayList<Music>();
	}

	public Playlist(int id, String name, ArrayList<Music> songs) {
		this.id = id;
		this.name = name;
		this.songs = songs;
	}
	
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
	
	public void addAllSongs(ArrayList<Music> m) {
		songs.addAll(m);
	}
	
	public void removeAllSongs(ArrayList<Music> m) 
	{
		songs.removeAll(m);
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


	public String getOwnerName() {
		return ownerName;
	}


	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}


	public int getOwnerID() {
		return ownerId;
	}


	public void setOwnerID(int ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		String s = name + "\t" + ownerName + "\t" + ownerId + "\n";
		for(Music m: songs) 
		{
			s += m.getPath() + "\n";
		}
		return super.toString();
	}
	
	
	
	
}