package br.ufrn.imd.modelo;

public class User {
	protected String username;
	protected String email;
	protected String password;
	protected int id;
	
	public User(int id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
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

	@Override
	public String toString() {
		String s = id + "\t"
				+ "defaultUser\t"
				+ username + "\t"
				+ email + "\t"
				+ password + "\t";
				
		return s;
	}
	
	
	
	
}
