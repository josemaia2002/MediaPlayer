package br.ufrn.imd.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.dao.MusicDao;
import br.ufrn.imd.dao.PlaylistDao;
import br.ufrn.imd.model.DirectoryDTO;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.User;

/**
 * Class that handles file management operations.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class FileManagementService {
	
	/**
	 * An object that allows the manipulation of the songs.
	 */
	private MusicDao musicDataAccess;
	
	/**
	 * An object that allows the manipulation of the playlist.
	 */
	private PlaylistDao playlistDataAccess;
	
	/**
	 * Consctructs a new instance of the FileManagementService Class.
	 * 
	 */
	public FileManagementService() {
		musicDataAccess = new MusicDao();
		playlistDataAccess = new PlaylistDao();	}
	
	/**
	 * Method that creates a playlist with the name and the given songs.
	 * 
	 * @param playlistName The name of the playlist to be created.
	 * @param songs An ArrayList with the playlist's songs. 
	 */
	public void createPlaylist(String playlistName, ArrayList<Music> songs)
	{
		User owner = AuthService.getCurrentUser();
		playlistDataAccess.createPlaylist(owner, playlistName, songs).getId();
	}
	
	/**
	 * Method that adds specific songs to a playlist.
	 * 
	 * @param playlist The playlist where the songs will be added.
	 * @param songs An ArrayList with the songs to be added.
	 */
	public void addMusicsToPlaylist(Playlist playlist, ArrayList<Music> songs) 
	{ 
		playlist.addAllSongs(songs);
		playlistDataAccess.addMusicsToPlaylist(playlist, songs);
	}
	
	/**
	 * Method that removes specific songs from a playlist.
	 * 
	 * @param playlist The playlist where the songs will be removed.
	 * @param songs An ArrayList with the songs to be removed.
	 */
	public void removeMusicsFromPlaylist(Playlist playlist, ArrayList<Music> songs) 
	{	
		playlistDataAccess.removeMusicsFromPlaylist(playlist, songs);
		playlist.removeAllSongs(songs);
	}
	
	/**
	 * Method that removes a playlist.
	 * 
	 * @param playlist The playlist to be removed.
	 */
	public void removePlaylist(Playlist playlist) 
	{
		playlistDataAccess.removePlaylist(playlist);
	}
	
	/**
     * Method that removes all the given playlists.
     * 
     * @param playlists The playlists to be removed.
     */
	public void removeAllPlaylists(ArrayList<Playlist> playlists) 
	{
		for(Playlist p : playlists) {
			playlistDataAccess.removePlaylist(p);
		}
	}

	/**
     * Method that manages the playlists initialization.
     * 
     * @return An ArrayList with the loaded playlists.
     */
	public ArrayList<Playlist> loadCurrentUserPlaylists() { 
		return playlistDataAccess.loadPlaylistsOfUser(AuthService.getCurrentUser());
	}
	
	
	/**
     * Method that manages the songs initialization.
     * 
     * @return An ArrayList with the loaded songs.
     */
	public ArrayList<Music> loadMusics() {
		return musicDataAccess.listSongs();
	}
	
	/**
     * Method that manages the directories initialization.
     * 
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
	
	/**
     * Method that adds a song based on its path.
     * 
     * @param path The song's path.
     */
	public void addSong(String path) { 
		Music m = new Music(path);
		addSong(m);
	}
	
	/**
     * Method that adds a song.
     * 
     * @param songs The song to be added.
     */
	public void addSong(Music song) { 
		musicDataAccess.addSong(song);
	}
	
	/**
     * Method that removes all the given songs.
     * 
     * @param songs The song to be removed.
     */
	public void removeAllSongs(ArrayList<Music> songs) 
	{
		musicDataAccess.removeAllSong(songs);
	}
	
	/**
     * Method that adds a directory.
     * 
     * @param path The directory's path.
     */
	public void addDirectory(String path) { 
		musicDataAccess.addDirectory(path);
	}
	
	/**
     * Method that removes a given directory.
     * 
     * @param path The directory's path.
     */
	public void removeDirectory(String path) { 
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/directories.txt").getFile()));
		
			String line = buffRead.readLine();
			while(true) {
				if(line != null) {
					if(line == path) {
						//musicDataAccess.ad
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
