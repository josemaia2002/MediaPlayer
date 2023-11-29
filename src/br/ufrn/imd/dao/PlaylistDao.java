package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import br.ufrn.imd.modelo.Playlist;
import br.ufrn.imd.modelo.User;
import br.ufrn.imd.modelo.UserVip;

public class PlaylistDao {
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	

	public ArrayList<Playlist> findPlaylistsByUserID(int id) {
		
		return playlists;
	}
	
}