package project.dao;

import project.model.User;

public interface UserDao {
	// Create
	public boolean insertUser(User myUser);

	// Read
	public User selectUserById(int user_id);

	public User selectUserByUsername(String user_username);

	// Update
	public boolean updateUser(User myUser);

	public User login(String user_username, String user_password);

	// Delete
	public boolean removeUser(int user_id);

}
