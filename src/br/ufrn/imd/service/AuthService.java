package br.ufrn.imd.service;

import br.ufrn.imd.modelo.User;
import br.ufrn.imd.dao.UserDao;

public class AuthService {
	static User currentUser;
	private UserDao userDAO;
	
	
	public AuthService() {
		this.userDAO = new UserDao();
	}


	public boolean loginCredentials(String username, String password) 
	{
		User u = null;
		if(username.contains("@"))  u = userDAO.findUserByEmail(username); 
		else u = userDAO.findUserByUsername(username);
		if(u == null) return false;
		AuthService.currentUser = u;
		return true;
	}
	
	public String signupCredentials(String username, String userType, String email, String p1, String p2) 
	{
		if(username.length() == 0 || email.length() == 0) return "Please fill all the fields above.";
		if(!p1.equals(p2)) return "The Passwords have to match!";
		if(p1.length() < 8) return "Passwords must have at least 8 characters.";
		User u = userDAO.findUserByUsername(username);
		if(u != null) return "This username is already taken!";
		u = userDAO.findUserByEmail(email);
		if(u != null) return "This e-mail address is already being used!";
		
		userDAO.saveUser(username, userType, email, p1);
		return "Singed Up!";
	}
}
