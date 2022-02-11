package project.service;

import java.util.ArrayList;

import project.dao.ReimbursementDao;
import project.dao.ReimbursementDaoImplementation;
import project.model.Reimbursement;

public class ReimbursementServiceImplementation implements ReimbursementService {

	ReimbursementDao dao = new ReimbursementDaoImplementation();
	
	@Override
	public boolean insertReimbursement(Reimbursement myReimbursement) {
		return dao.insertReimbursement(myReimbursement);
	}
	
	@Override
	public boolean insertReimbursementFromUser(Reimbursement myReimbursement) {
		return dao.insertReimbursementFromUser(myReimbursement);
	}

	@Override
	public Reimbursement selectReimbursementId(int reimbursement_id) {
		return dao.selectReimbursementId(reimbursement_id);
	}

	@Override
	public ArrayList<Reimbursement> selectAllReimbursement() {
		return dao.selectAllReimbursement();
	}
	
	@Override
	public ArrayList<Reimbursement> selectAllReimbursementFromAuthor(int author_id) {
		return dao.selectAllReimbursementFromAuthor(author_id);
	}

	@Override
	public boolean updateReimbursement(Reimbursement myReimbursement) {
		return dao.updateReimbursement(myReimbursement);
	}

	

	

}
