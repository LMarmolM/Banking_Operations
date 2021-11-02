package project0;

import java.sql.SQLException;
import java.util.Scanner;

public class Employee {
private int id;
private String name;
private String email;
	public Employee() {}
	
	public Employee(String name) {
		
		this.name=name;
	}
	
	
	public Employee(int id, String name, String email) {
		this.id = id;
		this.name=name;
		this.email=email;
	}
	
	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
	this.name = name;	
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub

	this.email = email;
	}
	
	public String toString() {
		String s = "\n\tID: "+id+"\tName: "+name+"\tEmail: "+email+"\n";
		return s;
	}
	
	public void employeeMenu() throws SQLException {
		System.out.println("Welcome, what actions do you wish to take?\n");
		Scanner userInput = new Scanner(System.in);
		boolean switchFlag=true;
		EmployeeDao dao = DaoFactory.getEmployeeDao();
		CustomerDao cDao = DaoFactory.getCustomerDao();
		while (switchFlag) {
			System.out.println("\nEmployee menu: \n");
			System.out.println("\t1. View all User Accounts\n\t2. Search User by ID\n\t3. Approve Accounts\n\t4. View Log for Cusotmer PENDING\n\t5. Exit\n\n Your Input: ");
			Customer customer = new Customer();
			switch (userInput.next()) {
			
			//View all users
			case "1":
				cDao.getUsers();
				break;
				
			//Search by ID
			case "2":
				System.out.println("Please enter the ID you wish to search: ");
				int input = enterInt();
				customer = dao.getCustomerById(input);
				System.out.println("Displaying customer information: ");
				System.out.println(customer);
				break;
				
			//Approve Accounts
			case "3":
				System.out.println("Please enter the ID of customer you wish to approve or reject: ");
				int inputCheck = enterInt();
				customer = dao.getCustomerById(inputCheck);
				//Check if customer.isCustomer(); otherwise say its not a customer
				if(customer.isPending()) {
				
						System.out.println("Pending approval process for "+customer.getName());
						System.out.println("Enter 0 to disapprove or any other number to approve customer account creation: ");
						inputCheck = enterInt();
						if(inputCheck >0) {
							cDao.updateStatus(customer,"customer");
							customer.setBalance(10000);
							customer.setStatus("customer");
						}
						else {
							cDao.updateStatus(customer,"user");
							customer.setStatus("user");
						}
						//Push value to database
						dao.updateCustomer(customer);
		
					
				}//end iscustomer
				
				else {
					System.out.println("\nThis user status is not pending.\n");
					continue;
				}
				System.out.println("Displaying customer information: ");
				System.out.println(customer);
				
				break;
				
			//View Log for Customer
			case "4":
				System.out.println("Pending JLOG4");
				break;
			//Exit
			case "5":
				
				switchFlag=false;
				break;
				
			//Error Message
			default:
				System.out.println("This input is not supported, please enter an option number from the list");
				break;
			}
	   }//While end
		
	}//EmployeeMenuEnd

	//Most useful, consider an entire class for support methods.
	//Only positive integers allowed, otherwise loop.
	private int enterInt() {
		
		boolean flag = true;
		int id=0;
		while(flag||id<0) {
		try {
			if (id<0) {
				id=0;
				System.out.println("Invalid entry, please enter a positive number: ");
			}
				Scanner input = new Scanner(System.in);
				id = Integer.parseInt(input.nextLine());
				flag = false;	
			}
			catch (Exception e) {
				System.out.println("Invalid entry, please enter a positive number: ");
				flag=true;
			}			
		}
				
		return id;
	}//Enter int end

}
