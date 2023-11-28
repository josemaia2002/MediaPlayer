package br.ufrn.imd.modelo;

import java.util.ArrayList;

public class UserVip extends User {
	
	private ArrayList<Playlist> playlists;
	
	public UserVip(int id, String nome, String email, String senha) {
		super(id, nome, email, senha);
	}
	
	public UserVip(User user, ArrayList<Playlist> playlists){
		super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
		this.playlists = playlists;
	}
	
	public void addPlaylist(Playlist p) {
		playlists.add(p);
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	@Override
	public String toString() {
		String s = id + "\t"
				+ "vipUser\t"
				+ username + "\t"
				+ email + "\t"
				+ password + "\t";
				
		return s;
	}
}
