package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.User;
import br.ufrn.imd.model.UserVip;
import javafx.beans.property.MapProperty;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;
import javafx.scene.media.Media;

public class MusicDao {
	private ArrayList<Music> songs = new ArrayList<Music>();
	
	public void addSong(Music m) {
		songs.add(m);
	}
	
	public void removeSong(Music m) {
		songs.remove(m);
	}
	
	public ArrayList<Music> listSongs() {
		return songs;
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
		Media m = new Media(path);
		
		MapProperty<String, Object> metaData = (MapProperty<String, Object>) m.getMetadata();
		Music song = new Music();
		song.setPath(path);
		metaData.addListener( (Change<? extends String, ? extends Object> c) -> {
	        if (c.wasAdded()) {
	            if ("artist".equals(c.getKey())) {
	                song.setAuthor(c.getValueAdded().toString());
	            } else if ("title".equals(c.getKey())) {
	            	 song.setTitle(c.getValueAdded().toString());
	            } else if ("album".equals(c.getKey())) {
	                song.setAlbum(c.getValueAdded().toString());
	            } else if ("genre".equals(c.getKey())) {
	                song.setGenre(c.getValueAdded().toString());
	            }
	        }
	    });
		
		return song;
	}
	
	public ArrayList<Music> loadDirectorySongs(String directoryPath)
	{
		ArrayList<Music> directorySongs = new ArrayList<Music>();
		
		File dir = new File(directoryPath);
		
		if(!dir.isDirectory()) return directorySongs;
		
		for(File f : dir.listFiles()) 
		{	
			directorySongs.add(loadSong(f.toURI().toString()));
		}
		
		return directorySongs;
	}
	
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
				selectedSongs.addAll(loadDirectorySongs(line));
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return selectedSongs;
	}
	
	
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
				selectedSongs.add(loadSong(new File(line).toURI().toString()));
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		return selectedSongs;
	}
	
	public ArrayList<Music> loadSongs()
	{
		songs = loadSongsFromSelectedDirectories();
		songs.addAll(loadSelectedSongs());
		return songs;
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
	
}
