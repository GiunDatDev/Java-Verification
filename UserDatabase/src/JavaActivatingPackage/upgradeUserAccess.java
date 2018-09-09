package JavaActivatingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseProcessingPackage.config;
import DatabaseProcessingPackage.loginToDatabase;

/*
 * This class will compare the key given from the user with the keys inside the database
 * If they are matching, the user can get access to every service of the system 
 * Otherwise they just can access to the basic function of the program
 * The V.I.P user (the one has the key) will be given the right to use the function inside the 'javaVipPackage' and the basic one
 * The normal user just can access to the 'basicPakage' only
 * */

public class upgradeUserAccess {
	private String keyText;
	private String userName;
	private String passWord;
	
	public upgradeUserAccess(String keyText, String userName, String passWord ) {
		this.keyText = keyText;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public void checkAndUpgrade() {
		loginToDatabase newUser = new loginToDatabase(userName, passWord);
		boolean loginState = newUser.authenticateUser();
		checkUserKey newUserKey = new checkUserKey(keyText);
		boolean keyStatus = newUserKey.checkKey();
		
		if (loginState == true && keyStatus == true) {
			upgradeUserAccount();
		}
		else {
			System.out.println("YOU HAVE ENTERED A WRONG USERNAME, PASSWORD OR PRODUCT KEY");
			System.out.println("PLEASE TRY AGAIN !!!");
		}
	}
	
	private void upgradeUserAccount() {
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			connection.setAutoCommit(true);
			
			String sqlCmd = String.format("SELECT * FROM userFullInfo WHERE userName = '%s'", userName);
			ResultSet resultSet = statement.executeQuery(sqlCmd);
			
			int _id = resultSet.getInt("_id");
			sqlCmd = String.format("UPDATE userInfo SET userStatus = 'true' WHERE _id = %d", _id);
			statement.execute(sqlCmd);
			resultSet.close();
			statement.close();
			connection.close();
			System.out.println("USER ACCOUNT HAS BEEN UPGRADED !!!");
			System.out.println("NOW YOU CAN GET ACCESS TO EVERY FUNCTION IN THE APP !!!");
		}
		catch (SQLException exception){
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
}









