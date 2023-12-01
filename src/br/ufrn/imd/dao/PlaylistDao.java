package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.modelo.Music;
import br.ufrn.imd.modelo.Playlist;
import br.ufrn.imd.modelo.User;
import br.ufrn.imd.modelo.UserVip;

import br.ufrn.imd.dao.MusicaDao;

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
	
	public Playlist loadPlaylist(int id) {
		BufferedReader buffRead;
		Playlist p = new Playlist(id);
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/playlists/" + id + ".ply").getFile()));
			String line = buffRead.readLine();
			
			if(line == null) return null;
			p.setName(line);
			
			MusicaDao musicdao = new MusicaDao();
			
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
	
	public ArrayList<Playlist> findPlaylistsByUserID(int id) {
		
		return playlists;
	}
	
	
}