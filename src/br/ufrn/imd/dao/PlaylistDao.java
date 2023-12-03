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

public class PlaylistDao {
	private ArrayList<Playlist> playlists;
	
	public PlaylistDao() {
		this.playlists = new ArrayList<Playlist>();
	}

	public void addPlaylist(Playlist p) {
		playlists.add(p);
	}
	
	public void removePlaylist(Playlist p) {
		playlists.remove(p);
		p.getId();
	}
	
	public ArrayList<Playlist> listPlaylists() {
		return playlists;
	}
	
	public void printPlaylists() {
		for(Playlist p : playlists) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			
			for(Music m : p.getSongs()) {
				System.out.println(m);
			}
		}
	}
	
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

	public ArrayList<Playlist> loadPlaylistsOfUser(User user) {
		ArrayList<Playlist> playlists = new ArrayList<Playlist>();
		int id = user.getId();
		
		for(Playlist p : loadAllPlaylists()) 
		{
			if(p.getOwnerID() == user.getId()) playlists.add(p);
		}
		
		return playlists;
	}

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