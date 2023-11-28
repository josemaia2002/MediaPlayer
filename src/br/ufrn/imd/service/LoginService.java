package br.ufrn.imd.service;

import br.ufrn.imd.modelo.User;
import br.ufrn.imd.dao.UserDao;

public class LoginService {
	static User currentUser;
	private UserDao userDAO;
	
	
	public LoginService() {
		this.userDAO = new UserDao();
	}


	public boolean loginCredentials(String username, String password) 
	{
		User u = null;
		if(username.contains("@"))  u = userDAO.findUserByEmail(username); 
		else u = userDAO.findUserByUsername(username);
		if(u == null) return false;
		LoginService.currentUser = u;
		return true;
	}
}
