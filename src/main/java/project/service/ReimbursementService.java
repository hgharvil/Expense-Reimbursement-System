package project.service;

import java.util.ArrayList;

import project.model.Reimbursement;

public interface ReimbursementService {
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
