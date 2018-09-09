package JavaActivatingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseProcessingPackage.config;

public class checkUserKey {
	private String keyText;
	
	public checkUserKey(String keyText) {
		this.keyText = keyText;
	}
	
	public boolean checkKey() {
		try {
			Connection keyConnection = DriverManager.getConnection(config.KEY_CONNECTION_STRING);
			Statement keyStatement = keyConnection.createStatement();
			keyConnection.setAutoCommit(true);
			String sqlCmd = "SELECT * FROM key";
			ResultSet keyResultset = keyStatement.executeQuery(sqlCmd);
			
			String keyDbs;
			while (keyResultset.next()) {
				keyDbs = keyResultset.getString("userKey");
				if (keyDbs.equals(keyText)) {
					keyResultset.close();
					keyStatement.close();
					keyConnection.close();
					return true;
				}
			}
			keyResultset.close();
			keyStatement.close();
			keyConnection.close();
			return false;
		}
		catch (SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
		return false;
	}
}
