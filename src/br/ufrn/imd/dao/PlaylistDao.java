package br.ufrn.imd.dao;

import java.util.ArrayList;
import br.ufrn.imd.modelo.Playlist;

public class PlaylistDao {
	private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
	
	public void addVeiculo(Playlist p) {
		playlists.add(p);
	}
	
	public void removeVeiculo(Playlist p) {
		playlists.remove(p);
	}
}