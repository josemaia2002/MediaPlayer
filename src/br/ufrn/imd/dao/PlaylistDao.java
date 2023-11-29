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
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
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
				System.out.println(m.getNome());
			}
		}
	}
	
	public void loadPlaylists() {
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/playlist002.txt").getFile()));
		
			String line = buffRead.readLine();
			
			// int userId = Integer.parseInt(line);
			line = buffRead.readLine();
			
			String userName = line;
			line = buffRead.readLine();
			
			UserDao ud = new UserDao();
			
			User u = new User();
			u = ud.findUserByUsername(userName);
			
			int playlistId = Integer.parseInt(line);
			line = buffRead.readLine();
			
			String playlistName = line;
			line = buffRead.readLine();
			
			Playlist p = new Playlist();
			p.setName(playlistName);
			p.setId(playlistId);
			
			
			while(true) {
				if(line != null) {
					String songPath = line;
					
					MusicaDao md = new MusicaDao();
					md.loadSongs();
					
					Music m = md.findSongByPath(songPath);
					p.addSong(m);
					
					line = buffRead.readLine();
				}
				else break;
			}
			playlists.add(p);
			((UserVip)u).addPlaylist(p);
			
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public ArrayList<Playlist> findPlaylistsByUserID(int id) {
		
		return playlists;
	}
	
}