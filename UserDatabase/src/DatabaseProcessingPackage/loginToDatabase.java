package DatabaseProcessingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DatabaseProcessingPackage.config;

public class loginToDatabase {	
	private String userName;
	private String passWord;
	
	public loginToDatabase(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;		
	}
	
	public boolean authenticateUser() {
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			String sqlCmd = "SELECT * FROM userFullInfo";
			ResultSet resultSet = statement.executeQuery(sqlCmd);
			connection.setAutoCommit(true);
			
			String userNameDbs;
			String passWordDbs;
			
			while (resultSet.next()) {
				userNameDbs = resultSet.getString("userName");
				passWordDbs = resultSet.getString("passWord");
				if (userNameDbs.equals(userName) && passWordDbs.equals(passWord)) {
					resultSet.close();
					statement.close();
					connection.close();
					return true;
				}
			}
			resultSet.close();
			statement.close();
			connection.close();
			return false;
		}
		catch (SQLException exception){
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
		return false;
	}
}
