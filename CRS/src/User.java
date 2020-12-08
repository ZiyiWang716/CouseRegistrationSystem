import java.io.Serializable;

/**
 * defines basic attributes that a user of the course registration system have, inherited by both Student class
 * and Admin class
 * 
 *
 */

public class User implements Serializable{
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String name;
		
	public User(String u, String p, String f, String l) {
		this.username=u;
		this.password=p;
		this.firstName=f;
		this.lastName=l;
		this.name=f+" "+l;
	}
	
	public void viewRegisteredCourses(Student u) {
		for (int i=0;i<u.getRegisteredCourses().size();i++) {
			System.out.println(u.getRegisteredCourses().get(i).getCourseName());
		}
	}
	
	
	
	public String getName() {
		return this.firstName+" "+this.lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
