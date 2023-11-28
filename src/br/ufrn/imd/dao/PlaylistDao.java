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
	
	//TODO
	public ArrayList<User> findAllUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader("/MediaPlayer/data/usuarios.txt"));
		
			String line = "";
			while (true) {
				if (line != null) {
					String[] credentials = line.split("\t");
					User u = new User(Integer.parseInt(credentials[0]), credentials[2], credentials[3], credentials[4]);
					if(credentials[1].equals("vipUser")) 
					{
						PlaylistDao playlistDAO = new PlaylistDao();
						u = (User) new UserVip(u, playlistDAO.findPlaylistsByUserID(u.getId()));
					}
					users.add(u);
				}
				else break;
			}
			buffRead.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public ArrayList<Playlist> findPlaylistsByUserID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}