package br.ufrn.imd.model;

public class User {
	private String username;
	private String userType;
	private String email;
	private String password;
	
	private int id;
	
	public User(int id, String userType, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.userType = userType;
		this.email = email;
		this.password = password;
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
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
