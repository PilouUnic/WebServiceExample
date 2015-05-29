package fr.web.service.example.basic;

public class User {

	private String firstName;
	private String lastName;

	// Must have no-argument constructor
	public User() {

	}

	public User(String fname, String lname) {
		this.firstName = fname;
		this.lastName = lname;
	}

	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lname) {
		this.lastName = lname;
	}

	public String getLastName() {
		return this.lastName;
	}


	@Override
	public String toString() {
		return new StringBuffer(" First Name : ").append(this.firstName)
				.append(" Last Name : ").append(this.lastName).toString();
	}
}
