package DatabaseProcessingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class deleteUserInfo {
	private String userName;
	private String passWord;
	private Scanner scanner = new Scanner(System.in);
	
	public deleteUserInfo(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public void checkAndDeleteInfo() {
		loginToDatabase newUser = new loginToDatabase(userName, passWord);
		boolean loginState = newUser.authenticateUser();
		
		if (loginState == true) {
			System.out.println("ARE YOU SURE YOU WANT TO DELETE THIS ACCOUNT !!!");
			String choice = scanner.nextLine();
			choice = choice.toLowerCase();
			if (choice.equals("yes")) {
				deleteInfo();
				return;
			}
			else if (choice.equals("no")) {
				System.out.println("YOU HAVE TERMINATED THE DELETING ACTIVITY !!!");
				return;
			}
		}
		else {
			System.out.println("YOU HAVE ENTERED A WRONG USERNAME OR PASSWORD");
			System.out.println("PLEASE TRY AGAIN !!!");
		}
	}
	
	private void deleteInfo() {
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			connection.setAutoCommit(true);
			
			String sqlCmd = String.format("SELECT * FROM userFullInfo WHERE userName = '%s'", userName);
			ResultSet resultSet = statement.executeQuery(sqlCmd);
			int userId = resultSet.getInt("_id");
			sqlCmd = String.format("DELETE FROM %s WHERE userName = '%s'", config.TABLE_USERLOGIN, userName);
			statement.execute(sqlCmd);
			sqlCmd = String.format("DELETE FROM %s WHERE _id = %d", config.TABLE_USERINFO, userId);
			System.out.println("USER RECORD HAS BEEN DELETED FROM THE SYSTEM !!!");
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch(SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
}










