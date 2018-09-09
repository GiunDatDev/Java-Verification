package JavaMainPackage;

import java.util.Scanner;

import DatabaseProcessingPackage.createSqlDatabase;
import DatabaseProcessingPackage.deleteUserInfo;
import DatabaseProcessingPackage.loginToDatabase;
import DatabaseProcessingPackage.mainUserInfoControlSystem;
import DatabaseProcessingPackage.printUserInfo;
import JavaActivatingPackage.createSqlKey;
import JavaActivatingPackage.hasUpgraded;
import JavaActivatingPackage.mainUserUpgradeControlSystem;
import JavaActivatingPackage.upgradeUserAccess;
import UserApplication.testingApp;

public class mainJava {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		System.out.println("Enter user name at here: ");
		String userName = scanner.nextLine();
		System.out.println("Enter password at here: ");
		String passWord = scanner.nextLine();
		loginToDatabase existsUser = new loginToDatabase(userName, passWord);
		boolean loginState = existsUser.authenticateUser();
		
		if (loginState == true) {
			mainUserUpgradeControlSystem newUser = new mainUserUpgradeControlSystem();
			newUser.executeCmdLine();
		}
		else {
			System.out.println("SOMETHING WENT WRONG !!!");
		}
	}
}
