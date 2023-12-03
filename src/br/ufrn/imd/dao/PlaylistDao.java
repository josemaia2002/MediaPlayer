package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.dao.MusicDao;
import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.Playlist;
import br.ufrn.imd.model.User;


/**
 * Data Access Object (DAO) class for managing interactions
 * with the files that store the playlists.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class PlaylistDao {
	
	/**
	 * This ArrayList represents a collection of playlists.
	 */
	private ArrayList<Playlist> playlists;
	
	/**
	 * Consctructs a new instance of the PlaylistDao Class.
	 */
	public PlaylistDao() {
		this.playlists = new ArrayList<Playlist>();
	}
	
	/**
     * Method that adds a specific playlist.
     * 
     * @param p The playlist to be added.
     */
	public void addPlaylist(Playlist p) {
		playlists.add(p);
	}
	
	/**
     * Method that removes a specific playlist.
     * 
     * @param p The playlist to be removed.
     */
	public void removePlaylist(Playlist p) {
		playlists.remove(p);
		p.getId();
		File playlistFile = new File(getClass().getResource("/resources/data/playlists/").getPath() + p.getId() + ".txt");
		playlistFile.delete();
	}
	
	/**
	 * Method that lists all the playlists.
	 * 
	 * @return An ArrayList with all the playlists.
	 */
	public ArrayList<Playlist> listPlaylists() {
		return playlists;
	}

	/**
	 * Method that creates a playlist with the given parameters.
	 * 
	 * @param owner The vip user that owns the playlist.
	 * @param playlistName The name of the playlist to be created.
	 * @param songs An ArrayList with the playlist's songs. 
	 * @return The Playlist that was created.
	 */
	public Playlist createPlaylist(User owner, String playlistName, ArrayList<Music> songs) 
	{
		String path = getClass().getResource("/resources/data/playlists/").getPath();
		File dir = new File(path);
		Playlist p = new Playlist(dir.list().length, playlistName, songs);
		p.setOwnerID(owner.getId());
		p.setOwnerName(owner.getUsername());
		p.setSongs(songs);
		
		File playlistFile = new File(path + p.getId() + ".txt");
		try {
			playlistFile.createNewFile();
			FileWriter writer = new FileWriter(playlistFile, false);
			
		    writer.append(p.toString());
		    writer.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playlists.add(p);
		return p;
	}
	
	
	/**
	 * Method that adds specific songs to a playlist.
	 * 
	 * @param p The playlist where the songs will be added.
	 * @param songs An ArrayList with the songs to be added.
	 */
	public void addMusicsToPlaylist(Playlist p, ArrayList<Music> songs) 
	{
		String path = getClass().getResource("/resources/data/playlists/").getPath();
		File playlistFile = new File(path + p.getId() + ".txt");
		try {
			FileWriter writer = new FileWriter(playlistFile, true);
			for(Music m : songs) 
			{
				writer.append(m.getPath());
				writer.append('\n');
			}
		    
		    writer.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that removes specific songs from a playlist.
	 * 
	 * @param p The playlist where the songs will be removed.
	 * @param songs An ArrayList with the songs to be removed.
	 */
	public void removeMusicsFromPlaylist(Playlist p, ArrayList<Music> songs) 
	{
		p.removeAllSongs(songs);
			
		try {			
			FileWriter writer = new FileWriter(getClass().getResource("/resources/data/playlists/playlist" + p.getId() + ".txt").getFile(), false);
		    
			writer.append(p.toString());
		    
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method that loads a specific playlist.
	 * 
	 * @param id The playlist's id.
	 * @return The loaded playlist.
	 */
	public Playlist loadPlaylist(int id) {
		BufferedReader buffRead;
		Playlist p = new Playlist(id);
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/playlists/" + id + ".txt").getFile()));
			String line = buffRead.readLine();
			
			if(line == null) 
			{
				buffRead.close();
				return null;
			}
			String[] headerData = line.split("\t");
			p.setName(headerData[0]);
			p.setOwnerName(headerData[1]);
			p.setOwnerID(Integer.parseInt(headerData[2]));
			
			MusicDao musicdao = new MusicDao();
			
			while(true) {
				String musicPath = buffRead.readLine();
				if(musicPath == null) break;
				Music m = musicdao.loadSong(musicPath);
				p.addSong(m);
			}
			playlists.add(p);
			
			buffRead.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}
	
	/**
	 * Method that reads playlists from a file line.
	 * 
	 * @param readingPoint The point to be readed in the file.
	 * @return An ArrayList with the playlists.
	 */
	public ArrayList<Playlist> readPlaylistsFromLine(BufferedReader readingPoint)
	{
		String[] playlistsIDs = null;
		try {
			playlistsIDs = readingPoint.readLine().split("\t");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		for(String id : playlistsIDs)
		{
			playlists.add(loadPlaylist(Integer.parseInt(id)));
		}
		
		
		return playlists;
		
	}
	
	/**
	 * Method that loads the playlists of a vip user.
	 * 
	 * @param user The vip user that owns the playlists to be loaded.
	 * @return An ArrayList with the loaded playlists.
	 */
	public ArrayList<Playlist> loadPlaylistsOfUser(User user) {
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		int id = user.getId();
		
		for(Playlist p : loadAllPlaylists()) 
		{
			if(p.getOwnerID() == user.getId()) playlists.add(p);
		}
		
		return playlists;
	}

	/**
	 * Method that loads all playlists.
	 * 
	 * @return An ArrayList with the loaded playlists.
	 */
	private ArrayList<Playlist> loadAllPlaylists() {
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		
		int size = (new File(getClass().getResource("/resources/data/playlists/").getPath())).list().length;
		for(int i = 0; i < size; i++) 
		{
			playlists.add(loadPlaylist(i));
		}
		return playlists;

	}
}