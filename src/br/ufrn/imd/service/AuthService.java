package br.ufrn.imd.service;

import br.ufrn.imd.dao.UserDao;
import br.ufrn.imd.model.User;

/**
 * Class that handles user authentication operations.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class AuthService {
	
	/**
	 * The user that is currently using the system.
	 */
	private static User currentUser;
	
	/**
	 * An object that allows the manipulation of the users.
	 */
	private UserDao userDAO;
	
	/**
	 * Consctructs a new instance of the AuthService Class.
	 * 
	 */
	public AuthService() {
		this.userDAO = new UserDao();
	}

	/**
	 * Method that controls the login of a user, verifying if his 
	 * credentials correspond to any user registered in the system.
	 * 
	 * @param username The user's username.
	 * @param password The user's password. 
	 */
	public boolean loginCredentials(String username, String password) 
	{
		User u = null;
		if(username.contains("@"))  u = userDAO.findUserByEmail(username); 
		else u = userDAO.findUserByUsername(username);
		if(u == null) return false;
		if(!password.equals(u.getPassword())) return false;
		AuthService.setCurrentUser(u);
		return true;
	}
	
	/**
	 * Method that controls the signup of a user, verifying if the 
	 * given parameters match the business logic of the system, and if
	 * the credentials correspond to some user already registered in the system.
	 * Finally, the method will register the user.
	 * 
	 * @param username The user's username.
	 * @param userType The user's type.
	 * @param email The user's email.  
	 * @param p1 The user's password first insertion.
	 * @param p2 The user's password second insertion, must match the first. 
	 */
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

	/**
	 * Method that retrieves the current user.
	 *
	 * @return The current user.
	 */
	public static User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Method that sets the current user.
	 * 
	 * @param currentUser The user that's using the system.
	 */
	public static void setCurrentUser(User currentUser) {
		AuthService.currentUser = currentUser;
	}
}
