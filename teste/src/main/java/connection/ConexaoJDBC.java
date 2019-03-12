package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoJDBC {
	
	private Connection connection = null;
	
	public ConexaoJDBC() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		
		Properties props = new Properties();
		props.setProperty("user","postgres");
		props.setProperty("password","postgres");
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
			connection.setAutoCommit(false);
			this.connection = connection;
		}catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	};
	
	public void close() {
		if(this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	};
	
	public void commit() throws SQLException {
		this.connection.commit();
		this.close();
	};
}
