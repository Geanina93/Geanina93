package banking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(JDBC_DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			throw new Error("Nu s-a putut incarca driver-ul JDBC MySQL.", cnfe);
		}
	}

	private static final String HOST_NAME = "localhost";
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	private static final String DATABASE_NAME = "ebanking";
	
	private static final String JDBC_URL = "jdbc:mysql://" + HOST_NAME + "/" + DATABASE_NAME;
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {}
		}
	}
}