
public class User {
	String username = null, password = null, role=null;
	
	public User(String u, String p,String r) {
		username = u;
		password = p;
		role = r;
	}

	public String toString() {
		return this.getUsername()+" "+this.getPassword()+" "+this.getRole();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
