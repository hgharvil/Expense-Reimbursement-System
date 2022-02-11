package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;

import project.model.Reimbursement;
import project.service.ReimbursementService;
import project.service.ReimbursementServiceImplementation;

public class ReimbursementDaoImplementation implements ReimbursementDao {

//	public static void main(String[] args) {
//		ReimbursementService serv = new ReimbursementServiceImplementation();
//		System.out.println(serv.selectAllReimbursementFromAuthor(1));
//	}
	
	@Override
	public boolean insertReimbursement(Reimbursement myReimbursement) {
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "INSERT INTO ers_reimbursement VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, myReimbursement.getAmount());
			ps.setObject(2, myReimbursement.getSubmitted());
			ps.setObject(3, myReimbursement.getResolved());
			ps.setString(4, myReimbursement.getDescription());
			ps.setBytes(5, myReimbursement.getReceipt());
			ps.setInt(6, myReimbursement.getAuthor());
			ps.setInt(7, myReimbursement.getResolver());
			ps.setInt(8, myReimbursement.getStatusId());
			ps.setInt(9, myReimbursement.getTypeId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertReimbursementFromUser(Reimbursement myReimbursement) {
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "INSERT INTO ers_reimbursement VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, myReimbursement.getAmount());
			ps.setObject(2, myReimbursement.getSubmitted());
			ps.setNull(3, Types.NULL);
			ps.setString(4, myReimbursement.getDescription());
			ps.setBytes(5, myReimbursement.getReceipt());
			ps.setInt(6, myReimbursement.getAuthor());
			ps.setNull(7, Types.NULL);
			ps.setInt(8, myReimbursement.getStatusId());
			ps.setInt(9, myReimbursement.getTypeId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Reimbursement selectReimbursementId(int reimbursement_id) {
		Reimbursement answerReimbursement = null;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, reimbursement_id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
				answerReimbursement = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class),
						rs.getString("reimb_description"),
						rs.getBytes("reimb_receipt"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return answerReimbursement;
	}

	@Override
	public ArrayList<Reimbursement> selectAllReimbursement() {
		ArrayList<Reimbursement> answerReimbursementList = new ArrayList<>();
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_reimbursement ORDER BY reimb_id ASC";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
				answerReimbursementList.add(new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class),
						rs.getString("reimb_description"),
						rs.getBytes("reimb_receipt"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return answerReimbursementList;
	}
	
	@Override
	public ArrayList<Reimbursement> selectAllReimbursementFromAuthor(int author_id) {
		ArrayList<Reimbursement> answerReimbursementList = new ArrayList<>();
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "SELECT * FROM ers_reimbursement a INNER JOIN ers_users b ON a.reimb_author = b.ers_users_id WHERE reimb_author = ? ORDER BY reimb_id ASC";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, author_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
				answerReimbursementList.add(new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getObject("reimb_submitted", LocalDateTime.class),
						rs.getObject("reimb_resolved", LocalDateTime.class),
						rs.getString("reimb_description"),
						rs.getBytes("reimb_receipt"),
						rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"),
						rs.getInt("reimb_type_id")
						));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return answerReimbursementList;
	}

	@Override
	public boolean updateReimbursement(Reimbursement myReimbursement) {
		if(selectReimbursementId(myReimbursement.getId()) == null) return false;
		try (Connection conn = CustomConnectionFactory.getConnection()) {
			String ourSQLStatement = "UPDATE ers_reimbursement SET "
					+ "reimb_amount=?,"
					+ "reimb_submitted=?,"
					+ "reimb_resolved=?,"
					+ "reimb_description=?,"
					+ "reimb_receipt=?,"
					+ "reimb_author=?,"
					+ "reimb_resolver=?,"
					+ "reimb_status_id=?,"
					+ "reimb_type_id=?"
					+ " WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
			ps.setInt(1, myReimbursement.getAmount());
			ps.setObject(2, myReimbursement.getSubmitted());
			ps.setObject(3, myReimbursement.getResolved());
			ps.setString(4, myReimbursement.getDescription());
			ps.setBytes(5, myReimbursement.getReceipt());
			ps.setInt(6, myReimbursement.getAuthor());
			ps.setInt(7, myReimbursement.getResolver());
			ps.setInt(8, myReimbursement.getStatusId());
			ps.setInt(9, myReimbursement.getTypeId());
			ps.setInt(10, myReimbursement.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

	

}
