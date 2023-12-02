package br.ufrn.imd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.dao.MusicDao;
import br.ufrn.imd.dao.PlaylistDao;
import br.ufrn.imd.model.DirectoryDTO;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.UserVip;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileManagementService {
	
	private MusicDao musicDataAccess;
	private PlaylistDao playlistDataAccess;
	
	public FileManagementService() {
		musicDataAccess = new MusicDao();
		playlistDataAccess = new PlaylistDao();
	}
	
	public void createPlaylist(String playlistName, ArrayList<Music> songs)
	{
		UserVip owner = (UserVip) AuthService.getCurrentUser();
		owner.addPlaylist(playlistDataAccess.createPlaylist(owner, playlistName, songs).getId());
	}
	
	public void addMusicsToPlaylist(Playlist playlist, ArrayList<Music> songs) 
	{ 
		playlist.addAllSongs(songs);
		playlistDataAccess.addMusicsToPlaylist(playlist, songs);
	}
	
	public void removeMusicsFromPlaylist(Playlist playlist, ArrayList<Music> songs) 
	{	
		playlistDataAccess.removeMusicsFromPlaylist(playlist, songs);
		playlist.removeAllSongs(songs);
	}
	
	public void removePlaylist(Playlist playlist) 
	{
		playlistDataAccess.removePlaylist(playlist);
	}

	/**
     * Method that manages the playlists initialization.
     * @param user the user whose playlists will be loaded.
     * @return An ArrayList with the loaded playlists.
     */
	public ArrayList<Playlist> loadPlaylists(UserVip user) { 
		ArrayList<Playlist> playlists = new ArrayList<Playlist>(); 
				
		for(Integer id : user.getPlaylistsIDs())
		{
			Playlist p = playlistDataAccess.loadPlaylist(id);
			if( p!= null ) playlists.add(p);
		}
		return playlists;
	}
	
	
	/**
     * Method that manages the songs initialization.
     * @return An ArrayList with the loaded songs.
     */
	public ArrayList<Music> loadMusics() {
		return musicDataAccess.listSongs();
	}
	
	/**
     * Method that manages the directories initialization.
     * @return An ArrayList with the loaded directories.
     */
	public ArrayList<DirectoryDTO> listDirectoriesDTO() {
		ArrayList<DirectoryDTO> dirDTO = new ArrayList<DirectoryDTO>();
		for(String d: musicDataAccess.listDirectories()) 
		{
			dirDTO.add(new DirectoryDTO(d));
		}
		return dirDTO;
	}
	
	// Operational Methods
	public void addMusic(String path) { 
		Music m = new Music(path);
		musicDataAccess.addSong(m);
	}
	
	public void addMusic(Music song) { 
		musicDataAccess.addSong(song);
	}
	
	
	
	public void addDirectory(String path) { 
		musicDataAccess.addDirectory(path);
	}
	
	public void removeDirectory(String path) { 
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/directories.txt").getFile()));
		
			String line = buffRead.readLine();
			while(true) {
				if(line != null) {
					if(line == path) {
						//TODO
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
