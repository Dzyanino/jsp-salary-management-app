package mg.se.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public interface ConnectionProperties {
	final String connectionURL = "jdbc:mysql://192.168.56.101:3306/salaireEnseignant";
	final String username = "remoteUser";
	final String password = "remote_User";
	
	default Connection getDatabaseConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connectionURL, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return conn;
	}
}
