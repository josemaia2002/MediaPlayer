package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.model.Music;
import br.ufrn.imd.model.User;
import br.ufrn.imd.model.UserVip;

public class UserDao {
	public ArrayList<User> findAllUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		BufferedReader buffRead;
		try {
			
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/users.txt").getFile()));
		
			String line = buffRead.readLine();
			while (true) {
				if (line != null) {
					String[] credentials = line.split("\t");
					User u = new User(Integer.parseInt(credentials[0]), credentials[2], credentials[3], credentials[4]);
					if(credentials[1].equals("vipUser")) 
					{
						line = buffRead.readLine();
						ArrayList<String> playlistsFound = new ArrayList<String>();
						if(line != null) {
							for(String p : line.split("\t")) 
							{
								playlistsFound.add(p);
							}
						}
						u = (User) new UserVip(u, playlistsFound);
					}
					users.add(u);
					line = buffRead.readLine();
				}
				else break;
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
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
	
	public boolean saveUser(String username, String userType, String email, String password) {

		try {
			String str = findAllUsers().size() + "\t"
					+ userType + "\t"
					+ username + "\t"
					+ email + "\t"
					+ password + "\t";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(getClass().getResource("/resources/data/usuarios.txt").getFile(), true));
	    
			writer.append('\n');
		    writer.append(str);
		    writer.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}


}