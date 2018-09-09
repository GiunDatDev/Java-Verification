package JavaActivatingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseProcessingPackage.config;
import DatabaseProcessingPackage.loginToDatabase;

public class hasUpgraded {
	private String userName;
	private String passWord;
	
	public hasUpgraded(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}	
	
	public boolean hasUpgradedCheck() {
		// True means that the account has been upgraded
		// False means that the account has not been upgraded
		// Need to check if a user is in the database or not !!!		
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			connection.setAutoCommit(true);
			String sqlCmd = String.format("SELECT * FROM userFullInFo WHERE userName = '%s'", userName);
			ResultSet resultSet = statement.executeQuery(sqlCmd);
			String userStatus = resultSet.getString("userStatus");
			
			if (userStatus.equals("true")) {
				resultSet.close();
				statement.close();
				connection.close();
				return true;
			}
			else {
				resultSet.close();
				statement.close();
				connection.close();
				return false;
			}
		}
		catch (SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
		return false;
	}
	
	public boolean hasExistsAndUpgraded() {
		loginToDatabase newUser = new loginToDatabase(userName, passWord);
		boolean loginState = newUser.authenticateUser();
		
		if (loginState == true) {
			hasUpgraded existsUser = new hasUpgraded(userName, passWord);
			boolean upgradedState = existsUser.hasUpgradedCheck();
			if (upgradedState == true) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			System.out.println("YOU HAVE ENTERED A WRONG USERNAME OR PASSWORD");
			System.out.println("PLEASE TRY AGAIN !!!");
			return false;
		}
	}
}	
