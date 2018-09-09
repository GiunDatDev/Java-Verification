package JavaActivatingPackage;

import java.util.Scanner;

public class mainUserUpgradeControlSystem {
	private static Scanner scanner = new Scanner(System.in);
	private String userName;
	private String passWord;
	
	public mainUserUpgradeControlSystem() {
		
	}
	
	public void showHelp() {
		System.out.println("");
		System.out.println("==== PROGRAM INSTRUCTION ====");
		System.out.println(".quit--QUIT PROGRAM");
		System.out.println(".printIns--PRINT INSTRUCTION");
		System.out.println(".makeKey--CREATE NEW PRODUCT KEY");
		System.out.println(".accType--CHECK USER TYPE OF ACCOUNT");
		System.out.println(".upgradeAcc--UPGRADE USER ACCESS");
		System.out.println("==== END OF INSTRUCTION =====");
		System.out.println("");
	}
	
	public void makeKey() {
		System.out.println("ENTER THE NEW PRODUCT KEY AT HERE: ");
		String keyText = scanner.nextLine();
		createSqlKey newProductKey = new createSqlKey(keyText);
		newProductKey.addKeyText();		
	}
	
	public void checkUserType() {
		hasUpgraded userAccount = new hasUpgraded(userName, passWord);
		boolean userAccountStatus = userAccount.hasExistsAndUpgraded();
		if (userAccountStatus == true) {
			System.out.println("CHECKING DONE !!!");
			System.out.println("YOUR ACCOUNT HAS BEEN UPGRADED !!!");
		}
		else {
			System.out.println("CHECKING DONE !!!");
			System.out.println("YOUR ACCOUNT HAS NOT BEEN UPGRADED");
		}
	}
	
	public void upgradeUserAccount() {
		System.out.println("ENTER YOUR PRODUCT KEY AT HERE: ");
		String keyText = scanner.nextLine();
		upgradeUserAccess userAccount = new upgradeUserAccess(keyText, userName, passWord);
		userAccount.checkAndUpgrade();
	}
	
	public void executeCmdLine() {
		showHelp();
		boolean quit = false;
		
		while (!quit) {
			System.out.print("USER COMMAND: >>> ");
			String usrCmd = scanner.nextLine();
			if (usrCmd.equals(".quit")) {
				quit = true;
				System.out.println("SHUTDOWN USER COMMAND LINE");
				break;
			}
			else if (usrCmd.equals("")) {
				
			}
		}
	}
}
