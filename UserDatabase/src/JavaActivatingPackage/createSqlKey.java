package JavaActivatingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseProcessingPackage.config;

public class createSqlKey {
	private String keyText; 
	
	public createSqlKey(String keyText) {
		this.keyText = keyText;
	}
	
	public void addKeyText() {
		try {
			Connection connection = DriverManager.getConnection(config.KEY_CONNECTION_STRING);
			Statement statement = connection.createStatement();
			connection.setAutoCommit(true);
			String sqlCmd = String.format("INSERT INTO key(userKey) VALUES ('%s')", keyText);
			statement.execute(sqlCmd);
			System.out.println("KEY HAS BEEN ADDED TO THE KEY DATABASE !!!");
			statement.close();
			connection.close();
		}
		catch(SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
}
