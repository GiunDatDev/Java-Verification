package DatabaseProcessingPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DatabaseProcessingPackage.config;

public class createSqlDatabase {
	
	private String userName;
	private String passWord;
	private String userStatus;
		
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public createSqlDatabase(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
		this.userStatus = "false";
		// userStatus will be set to true if the user provide the right key
		// Whenever the key is true, the program will be unlocked, so the user can use all the services inside
		// The key will be store in another database named "Servicekey.db"
	}
	public void writeIntoDatabase() {
		try {
			Connection connection = DriverManager.getConnection(config.CONNECTION_STRING);
			Statement statement = connection.createStatement();
			connection.setAutoCommit(true);
			
			// Create table userLogin and userInfo
			String sqlCmd = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT)", config.TABLE_USERLOGIN, config.COLUMN_ID, config.COLUMN_USERNAME, config.COLUMN_PASSWORD, config.INISTATUS);
			statement.execute(sqlCmd);
			sqlCmd = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT NOT NULL)", config.TABLE_USERINFO, config.COLUMN_ID, config.COLUMN_STATUS);
			statement.execute(sqlCmd);
			
			// Insert user information into the database
			insertInfo(statement, userName, passWord, userStatus);
			statement.close();
			connection.close();
		}
		catch (SQLException exception) {
			System.out.println("SOMETHING WENT WRONG: " + exception.getMessage());
			exception.printStackTrace();
		}
	}
	
	public void insertInfo(Statement statement, String userName, String passWord, String userStatus) throws SQLException {
		String sqlCmd = String.format("INSERT INTO %s(%s, %s) VALUES ('%s', '%s')", config.TABLE_USERLOGIN, config.COLUMN_USERNAME, config.COLUMN_PASSWORD, userName, passWord);
		statement.execute(sqlCmd);
		sqlCmd = String.format("INSERT INTO %s(%s) VALUES ('%s')", config.TABLE_USERINFO, config.COLUMN_STATUS, userStatus);
		statement.execute(sqlCmd);
		sqlCmd = String.format("UPDATE %s SET %s = %s WHERE %s = '%s'", config.TABLE_USERLOGIN, config.COLUMN_STATUS, config.COLUMN_ID, config.COLUMN_USERNAME, userName);
		statement.execute(sqlCmd);
		sqlCmd = String.format("CREATE VIEW IF NOT EXISTS userFullInfo(_id, userName, passWord, userStatus) AS "
				+ "SELECT userLogin._id, userLogin.userName, userLogin.passWord, userInfo.userStatus FROM userLogin INNER JOIN userInfo ON "
				+ "userLogin.userStatus = userInfo._id ORDER BY userLogin.userName");
		statement.execute(sqlCmd);
	}
}
