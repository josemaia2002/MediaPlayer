package br.ufrn.imd.model;

import java.util.ArrayList;

public class UserVip extends User {
	
	private ArrayList<String> playlistsIDs;
	
	public UserVip(int id, String nome, String email, String senha) {
		super(id, nome, email, senha);
	}
	
	public UserVip(User user, ArrayList<String> arrayList){
		super(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
		this.playlistsIDs = arrayList;
	}
	
	public void addPlaylist(String id) {
		playlistsIDs.add(id);
	}

	public ArrayList<String> getPlaylistsIDs() {
		return playlistsIDs;
	}

	public void setPlaylists(ArrayList<String> playlists) {
		this.playlistsIDs = playlists;
	}
	
	@Override
	public String toString() {
		String s = id + "\t"
				+ "vipUser\t"
				+ username + "\t"
				+ email + "\t"
				+ password + "\t"
				+ playlistsIDs;
		return s;
	}
}
