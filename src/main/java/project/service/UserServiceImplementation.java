package project.service;

import project.dao.UserDao;
import project.dao.UserDaoImplementation;
import project.model.User;

public class UserServiceImplementation implements UserService {

	UserDao dao = new UserDaoImplementation();
	
	@Override
	public boolean insertUser(User myUser) {
		return dao.insertUser(myUser);
	}

	@Override
	public User selectUserById(int user_id) {
		return dao.selectUserById(user_id);
	}
	
	@Override
	public User selectUserByUsername(String user_username) {
		return dao.selectUserByUsername(user_username);
	}

	@Override
	public boolean updateUser(User myUser) {
		return dao.updateUser(myUser);
	}

	@Override
	public User login(String user_username, String user_password) {
		return dao.login(user_username, user_password);
	}

	@Override
	public boolean removeUser(int user_id) {
		return dao.removeUser(user_id);
	}

	

}
