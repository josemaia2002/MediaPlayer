package br.ufrn.imd.model;

/**
 * Class that represents a user with its attributes.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class User {
	
	/**
	 * The user's username.
	 */
	private String username;
	
	/**
	 * The user's type.
	 */
	private String userType;
	
	/**
	 * The user's email.
	 */
	private String email;
	
	/**
	 * The user's password.
	 */
	private String password;
	
	/**
	 * The user's id.
	 */
	private int id;
	
	/**
	 * Consctructs a new instance of the User Class with the 
	 * given parameters.
	 * 
	 * @param id The id of the user.
	 * @param userType The user's type.
	 * @param username The user's username.
	 * @param email The user's email.
	 * @param password The user's password.
	 */
	public User(int id, String userType, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.userType = userType;
		this.email = email;
		this.password = password;
		
	}
	
	/**
	 * Method that retrieves the user's username.
	 *
	 * @return The user's username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Method that sets the user's username.
	 * 
	 * @param username The username to be assigned to the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Method that retrieves the user's email.
	 *
	 * @return The user's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method that sets the user's email.
	 * 
	 * @param email The email to be assigned to the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Method that retrieves the user's password.
	 *
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method that sets the user's password.
	 * 
	 * @param password The password to be assigned to the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Method that retrieves the user's id.
	 *
	 * @return The user's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method that sets the user's id.
	 * 
	 * @param id The id to be assigned to the user.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Method that retrieves the user's tyoe.
	 *
	 * @return The user's type.
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * Method that sets the user's userType.
	 * 
	 * @param userType The userType to be assigned to the user.
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * Method that returns a string representation of a User object.
	 * 
	 * @return A string with a simple representation of a User object.
	 */
	@Override
	public String toString() {
		String s = id + "\t"
				+ userType + "\t"
				+ username + "\t"
				+ email + "\t"
				+ password + "\t";
				
		return s;
	}
	
	
	
	
}
