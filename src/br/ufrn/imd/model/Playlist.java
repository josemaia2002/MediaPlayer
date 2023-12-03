package br.ufrn.imd.model;

import java.util.ArrayList;

/**
 * Class that represents a playlist with its attributes.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class Playlist {
	
	/**
	 * The playlist's id.
	 */
	private int id;
	
	/**
	 * The playlist's name.
	 */
	private String name;
	
	/**
	 * The name of the user that owns the playlist.
	 */
	private String ownerName;
	
	/**
	 * The id of the user that owns the playlist.
	 */
	private int ownerId;
	
	/**
	 * This ArrayList represents a collection of playlists.
	 */
	private ArrayList<Music> songs;
	
	/**
	 * Consctructs a new instance of the Playlist Class with the 
	 * given id.
	 * 
	 * @param id The playlist's id.
	 */
	public Playlist(int id) {
		this.id = id;
		songs = new ArrayList<Music>();
	}
	
	/**
	 * Consctructs a new instance of the Music Class with the 
	 * given parameters.
	 * 
	 * @param id The id of the playlist.
	 * @param name The playlist's name.
	 * @param songs An ArrayList with the playlist's songs.
	 */
	public Playlist(int id, String name, ArrayList<Music> songs) {
		this.id = id;
		this.name = name;
		this.songs = songs;
	}
	
	/**
	 * Method that retrieves the playlist's songs.
	 *
	 * @return An ArrayList with the playlist's songs.
	 */
	public ArrayList<Music> getSongs() {
		return songs;
	}
	
	/**
	 * Method that sets the playlist's songs.
	 * 
	 * @param songs The songs to be assigned to the playlist.
	 */
	public void setSongs(ArrayList<Music> songs) {
		this.songs = songs;
	}
	
	/**
	 * Method that retrieves the playlist's id.
	 *
	 * @return The playlist's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method that sets the playlist's id.
	 * 
	 * @param id The id to be assigned to the playlist.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Method that adds a song to the playlist.
	 * 
	 *@param m The song to be added.
	 */
	public void addSong(Music m) {
		songs.add(m);
	}
	
	/**
	 * Method that adds all the songs of a given ArrayList
	 * to the playlist.
	 * 
	 * @param m An ArrayList with the songs to be added.
	 */
	public void addAllSongs(ArrayList<Music> m) {
		songs.addAll(m);
	}
	
	/**
	 * Method that removes all the songs of a given ArrayList
	 * from the playlist.
	 * 
	 * @param m An ArrayList with the songs to be removed.
	 */
	public void removeAllSongs(ArrayList<Music> m) 
	{
		songs.removeAll(m);
	}
	
	/**
	 * Method that removes a specific song from the playlist.
	 * 
	 * @param m The song to be removed.
	 */
	public void removeSong(Music m) {
		songs.remove(m);
	}

	/**
	 * Method that retrieves the playlist's name.
	 *
	 * @return The playlist's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method that sets the playlist's name.
	 * 
	 * @param name The name to be assigned to the playlist.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method that retrieves the name of the playlist's owner.
	 *
	 * @return The name of the playlist's owner.
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * Method that sets the name of the playlist's owner.
	 *
	 *@param ownerName The name of the playlist owner to be assigned to the playlist.
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * Method that retrieves the name of the playlist's owner.
	 *
	 * @return The id of the playlist's owner.
	 */
	public int getOwnerID() {
		return ownerId;
	}

	/**
	 * Method that sets the id of the playlist's owner.
	 *
	 *@param ownerId The id of the playlist owner to be assigned to the playlist.
	 */
	public void setOwnerID(int ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * Method that returns a string representation of a Playlist object.
	 * 
	 * @return A string with a simple representation of a Playlist object.
	 */
	@Override
	public String toString() {
		String s = name + "\t" + ownerName + "\t" + ownerId + "\n";
		for(Music m: songs) 
		{
			s += m.getPath() + "\n";
		}
		return s;
	}
}