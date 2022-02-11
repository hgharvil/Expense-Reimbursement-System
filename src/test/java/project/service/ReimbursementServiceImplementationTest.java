package project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Reimbursement;


class ReimbursementServiceImplementationTest {

	ReimbursementService userv = new ReimbursementServiceImplementation();
	static String url = "jdbc:postgresql://"+System.getenv("TRAINING_DB_ENDPOINT")+":"+System.getenv("TRAINING_DB_PORT")+"/"+System.getenv("TRAINING_DB_PROJECT01DB");
	static String username = System.getenv("TRAINING_DB_USERNAME");
	static String password = System.getenv("TRAINING_DB_PASSWORD");
	static Connection conn = null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		conn.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		String ourSQLStatement = "CREATE TABLE ers_user_roles"
				+ "	ers_user_role_id INT NOT NULL UNIQUE,"
				+ "	user_role VARCHAR(10) NOT NULL,"
				+ "	CONSTRAINT ers_user_roles_pk PRIMARY KEY (ers_user_role_id)"
				+ ")";
		PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "INSERT INTO ers_user_roles (ers_user_role_id,user_role) VALUES "
				+ "(1, 'Employee'),(2, 'F. Manager')";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "CREATE TABLE ers_users("
				+ "	ers_users_id SERIAL NOT NULL,"
				+ "	ers_username VARCHAR(50) NOT NULL,"
				+ "	ers_password VARCHAR(50) NOT NULL,"
				+ "	user_first_name VARCHAR(100) NOT NULL,"
				+ "	user_last_name VARCHAR(100) NOT NULL, "
				+ "	user_email VARCHAR(100) NOT NULL,"
				+ "	user_role_id INT NOT NULL,"
				+ "	CONSTRAINT ers_users_pk PRIMARY KEY (ers_users_id),"
				+ "	CONSTRAINT ers_users__unv1 UNIQUE (ers_username, user_email),"
				+ "	CONSTRAINT user_roles_fk FOREIGN KEY (user_role_id) REFERENCES ers_user_roles (ers_user_role_id)"
				+ ")";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "CREATE TABLE ers_reimbursement_type("
				+ "	reimb_type_id INT NOT NULL,"
				+ "	reimb_type VARCHAR(10) NOT NULL,"
				+ "	CONSTRAINT reimb_type_pk PRIMARY KEY (reimb_type_id)"
				+ ")";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "INSERT INTO ers_reimbursement_type (reimb_type_id,reimb_type) VALUES "
				+ "(1, 'Lodging'),(2,'Travel'),(3,'Food'),(4,'Other')";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "CREATE TABLE ers_reimbursement_status("
				+ "	reimb_status_id INT NOT NULL,"
				+ "	reimb_status VARCHAR(10) NOT NULL,"
				+ "	CONSTRAINT reimb_status_pk PRIMARY KEY (reimb_status_id)"
				+ ")";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "INSERT INTO ers_reimbursement_status (reimb_status_id,reimb_status) VALUES "
				+ "(1, 'Pending'),(2,'Approved'),(3,'Denied')";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "CREATE TABLE ers_reimbursement("
				+ "	reimb_id SERIAL NOT NULL,"
				+ "	reimb_amount INT NOT NULL,"
				+ "	reimb_submitted TIMESTAMP NOT NULL,"
				+ "	reimb_resolved TIMESTAMP,"
				+ "	reimb_description VARCHAR(250) NOT NULL,"
				+ "	reimb_receipt BYTEA,"
				+ "	reimb_author INT NOT NULL,"
				+ "	reimb_resolver INT DEFAULT NULL,"
				+ "	reimb_status_id INT NOT NULL,"
				+ "	reimb_type_id INT NOT NULL,"
				+ "	CONSTRAINT ers_reimbursement_pk PRIMARY KEY (reimb_id),"
				+ "	CONSTRAINT ers_users_fk_auth FOREIGN KEY (reimb_author) REFERENCES ers_users (ers_users_id),"
				+ "	CONSTRAINT ers_users_fk_reslvr FOREIGN KEY (reimb_resolver) REFERENCES ers_users (ers_users_id),"
				+ "	CONSTRAINT ers_reimbursement_status_fk FOREIGN KEY (reimb_status_id) REFERENCES ers_reimbursement_status (reimb_status_id),"
				+ "	CONSTRAINT ers_reimbursement_type_fk FOREIGN KEY (reimb_type_id) REFERENCES ers_reimbursement_type (reimb_type_id)"
				+ ")";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "INSERT INTO ers_users (ers_users_id,ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id) VALUES"
				+ "(DEFAULT, 'laura', 'laura', 'Laura', 'Smith', 'www@bexample.com', 2),"
				+ "(DEFAULT, 'carlos', 'carlos', 'Carlos', 'Santos', 'qqq@aexample.com', 1),"
				+ "(DEFAULT, 'mirian', 'mirian', 'Mirian', 'Lei', 'aaa@aexample.com', 1)";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		// rollingback the DB state after you've manipulated it.
		String ourSQLStatement = "DROP TABLE ers_reimbursement";
		PreparedStatement ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "DROP TABLE ers_reimbursement_status";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "DROP TABLE ers_reimbursement_type";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "DROP TABLE ers_users";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
		ourSQLStatement = "ers_user_roles";
		ps = conn.prepareStatement(ourSQLStatement);
		ps.executeUpdate();
	}
	
	@Test
	void insertFoodTest() {
		int reimbursementAmount = 32;
		LocalDateTime reimbursementSubmitted = LocalDateTime.now();
		LocalDateTime reimbursementResolved = null;
		String reimbursementDescription = "description";
		byte[] reimbursementReceipt = null;
		int reimbursementAuthor = 2;
		int reimbursementResolver = 1;
		int reimbursementStatus = 1;
		int reimbursementType = 4;

		Reimbursement newReimbursement = new Reimbursement(reimbursementAmount, reimbursementSubmitted,
				reimbursementResolved, reimbursementDescription, reimbursementReceipt, reimbursementAuthor,
				reimbursementResolver, reimbursementStatus, reimbursementType);
		assertEquals(true, userv.insertReimbursement(newReimbursement));
	}

}
