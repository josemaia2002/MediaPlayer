package br.ufrn.imd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.dao.MusicDao;
import br.ufrn.imd.dao.PlaylistDao;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.User;
import br.ufrn.imd.model.UserVip;

public class FileManagementService {
	
	private MusicDao musicDataAccess;
	private PlaylistDao playlistDataAccess;
	
	
	
	public FileManagementService() {
		musicDataAccess = new MusicDao();
		playlistDataAccess = new PlaylistDao();
	}

	/**
     * Method that manages the playlists initialization.
     * @param user the user whose playlists will be loaded.
     * @return An ArrayList with the loaded playlists.
     */
	public ArrayList<Playlist> loadPlaylists(UserVip user) { 
		ArrayList<Playlist> playlists = new ArrayList<Playlist>(); 
		
		int userId = user.getId();
		
		BufferedReader buffRead;
		int playlistId = 0;
		
		try {
			while(true) {
				playlistId++;
				
				File directory = new File("/home/maia/eclipse-workspace/MediaPlayer/src/resources/data/playlists");
				int fileCount = directory.list().length;
				
				
				if(playlistId > fileCount)
					break;
					
				
				buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/playlists/playlist" + playlistId + ".txt").getFile()));
				
				String line = buffRead.readLine();
				
				if(line == null) {
					break;
				}
				
				int fileUserId = Integer.parseInt(line);
				
				if(fileUserId != userId) {
					continue;
				}
				
				line = buffRead.readLine();
				
				String userName = line;
				line = buffRead.readLine();
				
				line = buffRead.readLine();
				
				String playlistName = line;
				line = buffRead.readLine();
				
				Playlist p = new Playlist(playlistId);
				p.setName(playlistName);
				
				
				
				while(true) {
					if(line != null) {
						String songPath = line;
						Music m = new Music();
						m.setPath(songPath);
						
						p.addSong(m);
						
						line = buffRead.readLine();
					}
					else {
						break;
					}
				}
				
				playlists.add(p);
				
				buffRead.close();
				
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return playlists; 
	}
	
	
	/**
     * Method that manages the songs initialization.
     * @return An ArrayList with the loaded songs.
     */
	public ArrayList<Music> loadMusics() {
		ArrayList<Music> musics = new ArrayList<Music>(); 
		
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/songs.txt").getFile()));
		
			String line = buffRead.readLine();
			while(true) {
				if (line != null) {
					String[] info = line.split(",");					
							
					Music m = new Music();
					m.setTitle(info[1]);
					m.setAuthor(info[2]);
					m.setAlbum(info[3]);
					m.setGenre(info[4]);
					m.setDuration(Double.parseDouble(info[5]));
					
					musics.add(m);
					line = buffRead.readLine();
				}
				else break;
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return musics;
	}
	
	/**
     * Method that manages the directories initialization.
     * @return An ArrayList with the loaded directories.
     */
	public ArrayList<String> loadDirectories() { 
		ArrayList<String> loadedDirectories = new ArrayList<String>();
		
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/directories.txt").getFile()));
		
			String line = buffRead.readLine();
			while(true) {
				if(line != null) {
					loadedDirectories.add(line);
					line = buffRead.readLine();
				}
				else { 
					break;
				}
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return loadedDirectories; 
	}
	
	
	// Operational Methods
	public void addMusic(String path) { 
		Music m = new Music();
		m.setPath(path);
		musicDataAccess.addSong(m);
	}
	
	public void addMusic(Music song) { 
		musicDataAccess.addSong(song);
	}
	
	public void addMusicToPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void removeMusicFromPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void addDirectory(String path) { 
		// TODO Implement relative path
		try {
			FileWriter myWriter = new FileWriter("/home/maia/eclipse-workspace/MediaPlayer/src/resources/data/directories.txt", true);
			myWriter.write("\n" + path);
			myWriter.close();
	    } 
		catch (IOException e) {
			// e.printStackTrace();
	    }
	}
	
	public void removeDirectory(String path) { 
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/directories.txt").getFile()));
		
			String line = buffRead.readLine();
			while(true) {
				if(line != null) {
					if(line == path) {
						
					}
							
					line = buffRead.readLine();
				}
				else break;
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
