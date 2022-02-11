package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.model.Reimbursement;
import project.model.User;
import project.service.ReimbursementService;
import project.service.ReimbursementServiceImplementation;

public class UserDaoImplementation implements UserDao {

	
	public static void main(String[] args) {
		UserDao dao = new UserDaoImplementation();
//		User newUser = new User("ttt","ttt","Ttt","Tttttt","ttt@example.com",1);
//		System.out.println(dao.insertUser(newUser));
		System.out.println(dao.selectUserById(1));
//		System.out.println(dao.selectUserByUsername("qqq"));
//		System.out.println(dao.updateUser(newUser));
//		System.out.println(dao.selectUserByUsername("ttt"));
	}
	
	@Override
	public boolean insertUser(User myUser) {
		if(selectUserByUsername(myUser.getUsername()) != null) return false;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "INSERT INTO ers_users VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, myUser.getUsername());
			ps.setString(2, myUser.getPassword());
			ps.setString(3, myUser.getName());
			ps.setString(4, myUser.getLast());
			ps.setString(5, myUser.getEmail());
			ps.setInt(6, myUser.getRole());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User selectUserById(int user_id) {
		User answerUser = null;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_users WHERE ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
				answerUser = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
			ReimbursementService rserv = new ReimbursementServiceImplementation();
			ArrayList<Reimbursement> reimbursementList = rserv.selectAllReimbursementFromAuthor(user_id);
			//Set the 
			if(reimbursementList != null) answerUser.setReimbursementList(reimbursementList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return answerUser;
	}
	
	@Override
	public User selectUserByUsername(String user_username) {
		User answerUser = null;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, user_username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
				answerUser = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return answerUser;
	}

	@Override
	public boolean updateUser(User myUser) {
		if(selectUserByUsername(myUser.getUsername()) == null) return false;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "UPDATE ers_users SET"
					+ " ers_username = ?,"
					+ " ers_password = ?,"
					+ " user_first_name = ?,"
					+ " user_last_name = ?,"
					+ " user_email = ?,"
					+ " user_role_id = ?"
					+ " WHERE ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, myUser.getUsername());
			ps.setString(2, myUser.getPassword());
			ps.setString(3, myUser.getName());
			ps.setString(4, myUser.getLast());
			ps.setString(5, myUser.getEmail());
			ps.setInt(6, myUser.getRole());
			ps.setInt(7, myUser.getId());
			ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User login(String user_username, String user_password) {
		User answerUser = null;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setString(1, user_username);
			ps.setString(2, user_password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
				answerUser = new User(
						rs.getInt("ers_users_id"),
						rs.getString("ers_username"),
						rs.getString("ers_password"),
						rs.getString("user_first_name"),
						rs.getString("user_last_name"),
						rs.getString("user_email"),
						rs.getInt("user_role_id")
						);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return answerUser;
	}

	@Override
	public boolean removeUser(int user_id) {
		if(selectUserById(user_id) == null) return false;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "DELETE FROM ers_users WHERE ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, user_id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

}
