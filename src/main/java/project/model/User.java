package project.model;

import java.util.ArrayList;

public class User {

	private int id; // Primary Key
	private String username;
	private String password;
	private String name;
	private String last;
	private String email;
	private int role; // Foreign Key
	ArrayList<Reimbursement> reimbursementList = new ArrayList<>();

	public User(int id, String username, String password, String name, String last, String email, int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.last = last;
		this.email = email;
		this.role = role;
	}

	public User(int id, String username, String password, String name, String last, String email, int role,
			ArrayList<Reimbursement> reimbursementList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.last = last;
		this.email = email;
		this.role = role;
		this.reimbursementList = reimbursementList;
	}

	public User(String username, String password, String name, String last, String email, int role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.last = last;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public ArrayList<Reimbursement> getReimbursementList() {
		return reimbursementList;
	}

	public void setReimbursementList(ArrayList<Reimbursement> reimbursementList) {
		this.reimbursementList = reimbursementList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", last="
				+ last + ", email=" + email + ", role=" + role + ", reimbursementList=" + reimbursementList + "]";
	}

	

}
