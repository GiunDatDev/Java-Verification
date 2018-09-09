package DatabaseProcessingPackage;

import java.util.Scanner;

/*
 * This function will help the developer to see every account information in the database
 * */

public class mainUserInfoControlSystem {
	private static Scanner scanner = new Scanner(System.in);
	private String userName;
	private String passWord;
	
	public mainUserInfoControlSystem(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public void showHelp() {
		System.out.println("");
		System.out.println("==== PROGRAM INSTRUCTION ====");
		System.out.println("0--QUIT PROGRAM");
		System.out.println("9--PRINT INSTRUCTION");
		System.out.println("1--SHOW USER INFORMATION");
		System.out.println("2--DELETE USER INFORMATION");
		System.out.println("3--CREATE NEW ACCOUNT");
		System.out.println("==== END OF INSTRUCTION =====");
		System.out.println("");
	}
	
	public void showUserInfo() {
		printUserInfo existsUser = new printUserInfo(userName, passWord);
		existsUser.checkAndPrintInfo();
	}
	
	public void deleteUserInfo() {
		deleteUserInfo existsUser = new deleteUserInfo(userName, passWord);
		existsUser.checkAndDeleteInfo();
	}
	
	public void createNewAccount() {
		System.out.println("ENTER NEW USER NAME AT HERE: ");
		String newUserName = scanner.nextLine();
		System.out.println("ENTER NEW PASSWORD AT HERE: ");
		String newPassWord = scanner.nextLine();
		createSqlDatabase newUser = new createSqlDatabase(newUserName, newPassWord);
		newUser.writeIntoDatabase();
	}
	
	public void executeCmdLine() {
		showHelp();
		boolean quit = false;
		
		while (!quit) {
			System.out.print("USER COMMAND: >>> ");
			int usrCmd = scanner.nextInt();
			scanner.nextLine();
			switch (usrCmd) {
			case 0:
				quit = true;
				System.out.println("SHUTDOWN USER INFORMATION COMMAND LINE !!!");
				break;
			case 9:
				showHelp();
				break;
			case 1:
				showUserInfo();
				break;
			case 2:
				deleteUserInfo();
				break;
			case 3:
				createNewAccount();
				break;
			}
		}
	}
}
