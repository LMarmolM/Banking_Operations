package project0;

import java.sql.SQLException;
import java.util.Scanner;
//This class Holds the static login methods for both employee and for User. Meant to clean up the Main class and to
//Provide a separate space.
//Methods are public because there are no risks here. No variables.
public class Login {
	Login(){}
	public static void employeeLogin() throws SQLException {

		
		System.out.println("\nWelcome Employee, enter your credentials");
		System.out.println("Please enter your Username: ");
		Scanner userInput = new Scanner(System.in);
		String username = userInput.nextLine();
		
		System.out.println("Please enter your Password: ");
		String password = userInput.next();
		System.out.println("Entered User: "+username+"\tEntered Pass: "+password);
		
		EmployeeDao dao = DaoFactory.getEmployeeDao();
		
		//Here is where you create an employee object, then pass on the name, as its the only critical factor so far.
		if(dao.Login(username, password)){
			System.out.println("Login Successful\n");
			Employee employee = new Employee(username);
			employee.setName(username);
			employee.employeeMenu();
		}
		else {	System.out.println("Login Failed\n");}
		
	}//employee login end

	public static void userLogin() throws SQLException {
		System.out.println("\nWelcome User, enter your credentials");
		System.out.println("Please enter your Username: ");
		Scanner userInput = new Scanner(System.in);
		String username = userInput.nextLine();
		System.out.println("Please enter your Password: ");
		String password = userInput.next();
		CustomerDao dao = DaoFactory.getCustomerDao();
		
		//Here is where you create an employee object, then pass on the name, as its the only critical factor so far.
		if(dao.Login(username, password)){
			System.out.println("Login Successful\n");
			Customer customer = dao.getCustomerByName(username);
			customer.customerMenu();
		}
		else {	System.out.println("Login Failed\n");}
		
		
		
	}//userLogin end


	}//class end
