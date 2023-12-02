package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import br.ufrn.imd.model.DirectoryDTO;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.User;
import br.ufrn.imd.model.UserVip;
import javafx.beans.property.MapProperty;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;
import javafx.scene.media.Media;

public class MusicDao {
	private ArrayList<Music> songs;
	private ArrayList<String> directories;
	
	
	
	public MusicDao() {
		this.songs = new ArrayList<Music>();
		this.directories = new ArrayList<String>();
		loadSongs();
	}

	/**
     * Method that adds a song to the loaded songs and saves it to be loaded the next time.
     * @param The new song to be saved
     */
	public void addSong(Music song) {
		if(!songs.contains(song))
		{
			songs.add(song);
			saveSong(song);
		}
	}
	
	/**
     * Method that adds many songs.
     * @param A list with the new songs to be saved
     */
	public void addAllSongs(Collection<Music> songs) {
		for(Music m : songs) 
		{
			addSong(m);
		}
	}
	
	public void removeSong(Music song) {
		songs.remove(song);
	}
	
	public ArrayList<Music> listSongs() {
		return songs;
	}
	
	public ArrayList<String> listDirectories() {
		return directories;
	}
		
	public void printSongs() {
		for(Music m : songs) {
			System.out.println(m);
		}
	}
	
	public Music findSongByPath(String path) {
		for(Music m : songs) {
			if(m.getPath().equals(path)) {
				return m;
			}
		}
		return null;
	}
	
	
	
	public Music loadSong(String path) 
	{
		URI u = (new File(path)).toURI();
		String[] slicedpath = path.split("\\\\");
		Music song = new Music(slicedpath[slicedpath.length-1]);
		Media m ;
		m = new Media(u.toString());	
		ObservableMap<String,Object> metaData = m.getMetadata();
		
		metaData.addListener( (Change<? extends String, ? extends Object> atributeChange) -> {
	        if (atributeChange.wasAdded()) {
	        	//System.out.println("atrchange on: " + u + ": " + atributeChange.getKey());
	            if (atributeChange.getKey().equals("artist")) {
	                song.setAuthor(atributeChange.getValueAdded().toString());
	            } else if ("title".equals(atributeChange.getKey())) {
	            	 song.setTitle(atributeChange.getValueAdded().toString());
	            } else if ("album".equals(atributeChange.getKey())) {
	                song.setAlbum(atributeChange.getValueAdded().toString());
	            } else if ("genre".equals(atributeChange.getKey())) {
	                song.setGenre(atributeChange.getValueAdded().toString());
	            }
	        }
	        
	    });
		return song;
	}
	
	/**
	 * Method that loads the songs from a specific directory.
	 * @return An ArrayList with all the loaded songs
	 */
	public ArrayList<Music> loadSongsFromDirectory(String directoryPath)
	{
		System.out.println(directoryPath);
		ArrayList<Music> directorySongs = new ArrayList<Music>();
		
		File dir = new File(directoryPath);
		
		if(!dir.isDirectory()) return directorySongs;
		
		
		for(File f : dir.listFiles()) 
		{	
			if(f.toString().contains(".mp3")) 
			{
				directorySongs.add(loadSong(f.toPath().toString()));
			}
			
		}
		
		return directorySongs;
	}
	
	/**
	 * Method that loads all the songs in the selected directories.
	 * @return An ArrayList with all the loaded songs
	 */
	public ArrayList<Music> loadSongsFromSelectedDirectories()
	{
		BufferedReader buffRead;
		ArrayList<Music> selectedSongs = new ArrayList<Music>();
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/directories.txt").getFile()));
		
			String line;
			
			while(true) 
			{
				line = buffRead.readLine();
				if(line == null) break;
				directories.add(line);
				selectedSongs.addAll(loadSongsFromDirectory(line));
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return selectedSongs;
	}
	
	public Boolean addDirectory(String path) 
	{
		try {

		    BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/resources/data/songs.txt").getFile(), true));
			writer.append('\n' + path);
		    writer.close();
		    directories.add(path);
		    return true;
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Method that loads all the songs selected by the user.
	 * @return An ArrayList with all the loaded songs
	 */
	public ArrayList<Music> loadSelectedSongs() {
		BufferedReader buffRead;
		ArrayList<Music> selectedSongs = new ArrayList<Music>();
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/songs.txt").getFile()));
		
			String line;
			
			while(true) 
			{
				line = buffRead.readLine();
				if(line == null) break;
				if(line.equals("")) break;
				selectedSongs.add(loadSong(new File(line).toURI().toString()));
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return selectedSongs;
	}
	
	
	/**
	 * Method that loads all songs saved, both in the selected directories and specifically saved by the user
	 */
	public void loadSongs()
	{
		addAllSongs(loadSongsFromSelectedDirectories());
		addAllSongs(loadSelectedSongs());
	}

	public boolean saveSong(Music song) 
	{
		try {

		    BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/resources/data/songs.txt").getFile(), true));
	    
			writer.append('\n');
		    writer.append(song.toString());
		    writer.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * A method that manages song deleting from file loading data
	 * @param song that will be deleted
	 * @return true when the song was correctly deleted, false otherwise.
	 */
	public boolean deleteSong(Music song)
	{
		//TODO
		return false;
	}
	
}
