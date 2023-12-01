package br.ufrn.imd.service;

import java.io.BufferedReader;
import java.io.FileReader;
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
     * Metodo que comanda a inicializacao das playlists.
     * @param user o usuario cujas playlists serao carregadas.
     * @return Uma ArrayList com as playlists carregadas.
     */
	public ArrayList<Playlist> loadPlaylists(UserVip user) { 
		/*TODO*/ 
		
		return new ArrayList<Playlist>(); 
	}
	
	
	/**
     * Metodo que comanda a inicializacao das musicas.
     * @return Uma ArrayList com as musicas carregadas.
     */
	public ArrayList<Music> loadMusics() {
		ArrayList<Music> musics = new ArrayList<Music>(); 
		/*TODO*/ 
		
		
		/*STUB*/
		musics.add(new Music());
		return musics;
		/*STUB*/
	}
	
	/**
     * Metodo que comanda a inicializacao dos diretorios de musicas.
     * @return Uma ArrayList com os diretorios de musicas que foram carregados.
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
	public void addMusic(String path) { /*TODO*/ }
	
	public void addMusic(Music song) { /*TODO*/ }
	
	public void addMusicToPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void removeMusicFromPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void addDirectory(String path) { /*TODO*/ }
	
	public void removeDirectory(String path) { /*TODO*/ }
}
