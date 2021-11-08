package project0;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
	//Starting point of the program. 
	public static void main(String[] args) throws SQLException {
		System.out.println("Welcome to LE Banking, the Wholesome bank: ");
		//EmployeeDao dao = new EmployeeDaoImpl();
		mainMenu();

	}//main end

	//Main Menu with options and references
private static void mainMenu() throws SQLException {
	//Input to use in option selection. Arranged in a functional way for the user.
	Scanner userInput = new Scanner(System.in);
	boolean switchFlag=true;
	while (switchFlag) {
		System.out.println("\nMain Menu, please choose the login you require using numbers from 1 to 3 \n");
		System.out.println("\t1. User\n\t2. Employee\n\t3. Create new Wholesome Account \n\t4. Exit\n\nYour Input: ");

			//Switch loop locking for valid input
		switch (userInput.next()) {
		case "1":
			Login.userLogin();
			break;
		case "2":
			Login.employeeLogin();
			break;
		case "3":
			Login.createAccount();
			break;
		case "4":
			System.out.println("Closing service...");
			System.out.println("Thank you for choosing LE Banking");
			userInput.close();
			switchFlag=false;
			break;
		default:
			System.out.println("This input is not supported, please enter an option number from the list");
			break;
		}
   }//While end
		
 }//Main end

}//class end
