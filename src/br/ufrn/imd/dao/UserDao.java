package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.model.User;


/**
 * Data Access Object (DAO) class for managing interactions
 * with the files that store the users.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class UserDao {
	
	/**
	 * Method that finds all the users of the system.
	 * 
	 * @return An ArrayList with the found users.
	 */
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
					User u = new User(Integer.parseInt(credentials[0]), credentials[1], credentials[2], credentials[3], credentials[4]);
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
	
	/**
	 * Method that finds a user of the system based on the username.
	 * 
	 * @return The requested user.
	 */
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
	
	/**
	 * Method that finds a user of the system based on the email.
	 * 
	 * @return The requested user.
	 */
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
	
	/**
	 * Method that saves a user with the given parameters.
	 * 
	 * @param username The username of the user.
	 * @param userType Whether the user is vip or default.
	 * @param email The email of the user.
	 * @param password The password of the user.
	 * @return True if the operation was successfull, and false otherswise.
	 */
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
	
	/**
	 * Method that saves a given user.
	 * 
	 * @param user The user to be saved.
	 * @return True if the operation was successfull, and false otherswise.
	 */
	public boolean saveUser(User user) {
		return saveUser(user.getUsername(), user.getUserType(), user.getEmail(), user.getPassword());
		
	}
	
}