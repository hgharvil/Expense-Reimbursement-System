package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomConnectionFactory {
	private static String url = "jdbc:postgresql://" + System.getenv("TRAINING_DB_ENDPOINT") + ":"
			+ System.getenv("TRAINING_DB_PORT") + "/" + System.getenv("TRAINING_DB_PROJECT01DB");
	private static String username = System.getenv("TRAINING_DB_USERNAME");
	private static String password = System.getenv("TRAINING_DB_PASSWORD");

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("CustomConnectionFactory \"org.postgresql.Driver\" not found.");
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}