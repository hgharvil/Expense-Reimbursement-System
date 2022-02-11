package project.service;

import project.model.User;

public interface UserService {
	// Create
	public boolean insertUser(User myUser);

	// Read
	public User selectUserById(int user_id);

	public User selectUserByUsername(String user_username);

	// Update
	public boolean updateUser(User myUser);

	// Delete
	public boolean removeUser(int user_id);

	public User login(String user_username, String user_password);

}
