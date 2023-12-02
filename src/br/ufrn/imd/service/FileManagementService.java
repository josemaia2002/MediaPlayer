package br.ufrn.imd.service;

import java.io.BufferedReader;
import java.io.File;
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
		ArrayList<Playlist> playlists = new ArrayList<Playlist>(); 
		
		int userId = user.getId();
		
		BufferedReader buffRead;
		int playlistId = 1;
		
		try {
			while(true) {
				buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/playlists/playlist" + playlistId + ".txt").getFile()));
				
				String line = buffRead.readLine();
				
				if(line == null) {
					break;
				}
				
				int fileUserId = Integer.parseInt(line);
				
				if(fileUserId != userId) {
					playlistId++;
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
					}
					else {
						break;
					}
				}
				
				buffRead.close();
				
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return playlists; 
	}
	
	
	public ArrayList<Playlist> loadPlaylists(int idUsuario) { 
		ArrayList<Playlist> playlists = new ArrayList<Playlist>(); 
		
		int userId = idUsuario;
		
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
     * Metodo que comanda a inicializacao das musicas.
     * @return Uma ArrayList com as musicas carregadas.
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
