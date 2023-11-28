package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import br.ufrn.imd.modelo.User;
import br.ufrn.imd.modelo.UserVip;

public class UserDao {


	public ArrayList<User> findAllUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader buffRead;

		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/usuarios.txt").toString()));
		
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
	
	public User findUserByUsername(String username)
	{
		User user = null;
		ArrayList<User> users = findAllUsers();
		for(User u : users) 
		{
			if(u.getUsername().equals(username)) 
			{
				return u;
			}
		}
		return user;
	}
	
	public User findUserByEmail(String email)
	{
		User user = null;
		ArrayList<User> users = findAllUsers();
		for(User u : users) 
		{
			if(u.getEmail().equals(email)) 
			{
				return u;
			}
		}
		return user;
	}
	
	public boolean saveUser(User user) 
	{
		ArrayList<User> users = findAllUsers();
		for(User u : users) 
		{
			if(u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail())) 
			{
				return false;
			}
		}
		
		try {
			String str = user.toString();
		    BufferedWriter writer = new BufferedWriter(new FileWriter("/MediaPlayer/data/usuarios.txt", true));
	    
			writer.append('\n');
		    writer.append(str);
		    writer.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}