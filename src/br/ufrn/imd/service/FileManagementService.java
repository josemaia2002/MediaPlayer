package br.ufrn.imd.service;

import java.util.ArrayList;

import br.ufrn.imd.dao.MusicDao;
import br.ufrn.imd.dao.PlaylistDao;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.UserVip;

public class FileManagementService {
	
	private MusicDao musicDataAccess;
	
	private PlaylistDao playlistDataAccess;
	
	
	
	public FileManagementService() {
		musicDataAccess = new MusicDao();
		playlistDataAccess = new PlaylistDao();
	}

	/**
     * Metodo que comanda a inicialização das músicas.
     * @param user o usuário cujas playlists serão carregadas.
     * @return Uma ArrayList com as músicas carregadas.
     */
	public ArrayList<Playlist> loadPlaylists(UserVip user) { /*TODO*/ return new ArrayList<Playlist>(); }
	
	/**
     * Metodo que comanda a inicialização das playlists.
     * @return Uma ArrayList com as playlists carregadas.
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
     * Metodo que comanda a inicialização dos diretórios de músicas.
     * @return Uma ArrayList com os diretórios de músicas que foram carregados.
     */
	public ArrayList<String> loadDirectories() { /*TODO*/ return new ArrayList<String>(); }
	
	
	// Operational Methods
	public void addMusic(String path) { /*TODO*/ }
	
	public void addMusic(Music song) { /*TODO*/ }
	
	public void addMusicToPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void removeMusicFromPlaylist(Playlist playlist, Music song) { /*TODO*/ }
	
	public void addDirectory(String path) { /*TODO*/ }
	
	public void removeDirectory(String path) { /*TODO*/ }
}
