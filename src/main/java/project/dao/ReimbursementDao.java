package project.dao;

import java.util.ArrayList;

import project.model.Reimbursement;

public interface ReimbursementDao {
	// Create
	public boolean insertReimbursement(Reimbursement myReimbursement);

	public boolean insertReimbursementFromUser(Reimbursement myReimbursement);
	// Read
	public Reimbursement selectReimbursementId(int reimbursement_id);

	public ArrayList<Reimbursement> selectAllReimbursement();
	
	public ArrayList<Reimbursement> selectAllReimbursementFromAuthor(int author_id);

	// Update
	public boolean updateReimbursement(Reimbursement myReimbursement);


	
}
