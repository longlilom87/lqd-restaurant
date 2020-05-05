
public class User {
	String username = null, password = null, role=null;
	String name,address;
	
	public User(String u, String p,String r,String n, String a) {
		username = u;
		password = p;
		role = r;
		name = n;
		address = a;
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
	public void Logout() {
		username = null;
		password = null;
		role = null;
		name = null;
		address = null;
	}
	
}