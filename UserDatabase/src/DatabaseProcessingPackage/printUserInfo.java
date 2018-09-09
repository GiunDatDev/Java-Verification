package DatabaseProcessingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseProcessingPackage.config;

public class printUserInfo {
	private String userName;
	private String passWord;
	
	public printUserInfo(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public void checkAndPrintInfo() {
		loginToDatabase newUser = new loginToDatabase(userName, passWord);
		boolean loginState = newUser.authenticateUser();
		
		if (loginState == true) {
			// Login successfully
			printInfo();			
		}
		else {
			System.out.println("YOU HAVE ENTERED A WRONG USERNAME OR PASSWORD");
			System.out.println("PLEASE TRY AGAIN !!!");
		}
	}
	
	private void printInfo() {
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			String sqlCmd = String.format("SELECT * FROM userFullInfo WHERE userName = '%s'", userName);
			ResultSet resultSet = statement.executeQuery(sqlCmd);
			connection.setAutoCommit(true);

			String userNameDbs;
			String passWordDbs;
			String activationStatus;
			
			while (resultSet.next()) {
				userNameDbs = resultSet.getString("userName");
				passWordDbs = resultSet.getString("passWord");
				activationStatus = resultSet.getString("userStatus");
				
				System.out.println("");
				System.out.println("==== USER INFORMATION ====");
				System.out.println("==== USER NAME: " + userNameDbs + " ====");
				System.out.println("==== PASSWORD: " + passWordDbs + " ====");
				System.out.println("==== ACTIVATION STATUS: " + activationStatus + " =====");
				System.out.println("==== THE END ====");
				System.out.println("");
				resultSet.close();
				statement.close();
				connection.close();
				return;
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
}
