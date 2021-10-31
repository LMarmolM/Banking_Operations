package project0;

import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
private int id;
private String name;
private String email;
private String status;
private int balance;
private String account;
	public Customer() {}
	
	public Customer(String name) {
		
		this.name=name;
	}
	
	
	public Customer(int id, String name, String email,String status,int balance, String account) {
		this.id = id;
		this.name=name;
		this.email=email;
		this.status=status;
		this.balance = balance;
		this.account = account;
	}
	
	public int getId() {return id;}
	
	public void setId(int id) {this.id = id;}
	
	public String getName() {return name;}

	public void setName(String name) {this.name = name;	}

	public String getEmail() {return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	public String getStatus() {return status;}
	
	public void setStatus(String status) {this.status = status;}
	
	public int getBalance() {return balance;}
	
	public void setBalance(int balance) {this.balance = balance;}
	
	public String getAccount() {return account;}
	
	public void setAccount(String account) {this.account= account;}
	
	public String toString() {
		String s = "\n\tID: "+id+"\tName: "+name+"\tEmail: "+email+"\tStatus: "+status+"\t"+"Account: "+account+"\tBalance: "+balance+"\n";
		return s;
	}
	
	public void customerMenu() throws SQLException {
		System.out.println("Welcome, what actions do you wish to take?\n");
		Scanner userInput = new Scanner(System.in);
		boolean switchFlag=true;
		CustomerDao dao = DaoFactory.getCustomerDao();
		while (switchFlag) {
			System.out.println("\nWelcome, what actions do you wish to take?\n");
			System.out.println("\t1. View balance\n\t2. Deposit funds\n\t3. Withdraw funds\n\t4. Transfer funds to another customer's account\n\t5. Review transactions\n\t6. Exit\n\n Your Input: ");
			switch (userInput.next()) {
			
			//View balance
			case "1":
				System.out.println("\nYour current balance is: "+balance+"\n");
				break;
			//Deposit funds
			case "2":
				System.out.println("\nEnter amount to deposit: ");
				int deposit = enterInt();
				depositValid(deposit);
				System.out.println("Your new balance is "+balance+"\n");
				break;
			//Withdraw funds
			case "3":
				System.out.println("\nEnter amount to withdraw: ");
				int withdraw = enterInt();
				withdrawValid(withdraw);
				System.out.println("Your new balance is "+balance+"\n");
				break;
			case "4":
			//Transfer funds to other account
				System.out.println("\nEnter amount to Transfer: ");
				int transfer = enterInt();
				transferValid(transfer);
				System.out.println("Your new balance is "+balance+"\n");
				break;
			
			//Approve pending deposits
			case "5":
				//dao.checkTransaction where toUser is this.name
				//return other customer with name and amount
				//print other.getName and amount
				//prompt user for positive for accepting and 0 for deny.
				//if accept, delete transaction and increment MY balance
				//if deny, delete transaction and increase OTHER balance
				//Both operations result in an increase of balance
				
				
				System.out.println("Your new balance is "+balance+"\n");	
				break;
				
			case "6":
				System.out.println("\nLogging out...\n");
				switchFlag=false;
				break;
			default:
				System.out.println("This input is not supported, please enter an option number from the list");
				break;
			}
	   }//While end
		
	}//CustomerMenuEnd

private void transferValid(int transfer) throws SQLException {
		CustomerDao dao = DaoFactory.getCustomerDao();
		System.out.println("\nEnter username of user you wish to transfer to: ");
		Scanner Input = new Scanner(System.in);
		String userDestiny = Input.nextLine();
	
		if(!withdrawValid(transfer)) {return;}
		//true is good
		boolean transferFlag = true;
		boolean catchFlag = true;
		try {
		transferFlag = dao.postTransfer(transfer,name,userDestiny);
		}
		
		catch(Exception e){
			catchFlag = false;
			System.out.println("\nContact manager\n"+e);
		}
		//If any isn't true, return balance
		if(!transferFlag||!catchFlag) {
			depositValid(transfer);
		}
	}

public boolean isCustomer() {
	if(status.equals("customer")) return true;
	
	return false;
}	

public boolean isRequested() {
	
	if(account.equals("requested")) return true;
	
	return false;
}	

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

private void depositValid(int deposit) throws SQLException{
	CustomerDao dao = DaoFactory.getCustomerDao();
	int temp = balance;
	balance+= deposit; 
	try {
		dao.updateCustomer(this);
	}
	catch (Exception e) {
	balance = temp;	
		System.out.println("\nPlease contact the manager to rectify this operation\n");
	}	
}

private boolean withdrawValid(int withdraw) throws SQLException {
	CustomerDao dao = DaoFactory.getCustomerDao();
	int temp = balance;
	if(balance>=withdraw) {
		
		balance-=withdraw;
	try {
		dao.updateCustomer(this);
		
	}
	catch (Exception e) {
		balance = temp;
		System.out.println("\nPlease contact the manager to rectify this operation\n");
		return false;
	}}
	else{
		balance = temp;
		System.out.println("\nError: Quantity to withdraw is greater than balance.");
		return false;
	}
	return true;
	
}


}//end class
