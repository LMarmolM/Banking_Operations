package project0;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//Customer class has some useful methods and data relevant to itself
//Stores everything except a 
public class Customer {
private int id;
private String name;
private String email;
private String status;
private int balance;

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
	
	
	public String toString() {
		String s = "\n\tID: "+id+"\tName: "+name+"\tEmail: "+email+"\tStatus: "+status+"\t"+"\tBalance: "+balance+"\n";
		return s;
	}
	
	public void customerMenu() throws SQLException {
		System.out.println("Welcome, what actions do you wish to take?\n");
		Scanner userInput = new Scanner(System.in);
		boolean switchFlag=true;
		CustomerDao cDao = DaoFactory.getCustomerDao();
		TransactionDao tDao = DaoFactory.getTransactionDao();
		while (switchFlag) {
			System.out.println("\nThe following are your options:\n");
			System.out.println("\t1. View balance\n\t2. Deposit funds\n\t3. Withdraw funds\n\t4. Transfer funds to another customer's account\n\t5. Review pending deposits\n\t6. Exit\n\n Your Input: ");
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
				System.out.println("Your balance is "+balance+"\n");
				break;
			
			//Approve pending deposits
			case "5":

				List<Transaction> listedTransactions = tDao.checkTransaction(this.name);
				transactionOptions(listedTransactions);				
				System.out.println("Your current balance is "+balance+"\n");	
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

private void transactionOptions(List<Transaction> listedTransactions) throws SQLException {
		if(listedTransactions.isEmpty()) {
			System.out.println("\nNo deposits are pending to you account.\n");
			return;
		}
		System.out.println("\nYou have "+listedTransactions.size()+" deposits pending for your review.\n");
		for(Transaction current : listedTransactions) {
			System.out.println(current.toString());
			System.out.println("Do you accept this transaction? Enter 0 for rejection and any other number to accept.\n");
			int selection = enterInt();
			if(selection ==0) {
				System.out.println("\nYou have rejected the deposit from "+current.getUser()+ ", funds will now be refund to sender.\n");
				//Simply call deposit to other.
				CustomerDao dao = DaoFactory.getCustomerDao();
				dao.refundOther(current.getUser(),current.getAmount());
				
			}
			
			else {
				System.out.println("\nYou have accepted the deposit from "+current.getUser()+ ", funds will now be deposited to your account.\n");
				depositValid(current.getAmount());
			}
			//If successfully called either deposit, delete the transaction pending.
			TransactionDao tdao = DaoFactory.getTransactionDao();
			tdao.deleteTransaction(current.getId());
			
			
		}//endfor
		
	}

private void transferValid(int transfer) throws SQLException {
		CustomerDao dao = DaoFactory.getCustomerDao();
		System.out.println("\nEnter username of user you wish to transfer to: ");
		Scanner Input = new Scanner(System.in);
		String userDestiny = Input.nextLine();
		if(userDestiny.equals(this.name)) {
			System.out.println("\nUse the deposit option for transfers to self\n");
			return;}
		
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

public boolean isPending() {
	System.out.println(" ");
	if(status.equals("pending")) return true;
	
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

public void userMenu() throws SQLException {
	System.out.println("Welcome, what actions do you wish to take?\n");
	Scanner userInput = new Scanner(System.in);
	boolean switchFlag=true;
	CustomerDao cDao = DaoFactory.getCustomerDao();
	while (switchFlag) {
		System.out.println("\nThe following are your options:\n");
		System.out.println("\t1. Apply for Customer Banking Account\n\t2. Exit\n\n Your Input: ");
		switch (userInput.next()) {
		
		//First option is to apply
		case "1":
			cDao.updateStatus(this,"pending");
			System.out.println("\nYou have successfully aplied. Please wait for your account to be approved.\n");
			break;
		//Second option is to leave
		case "2":
			System.out.println("\nLogging out...\n");
			switchFlag=false;
			break;
		default:
			System.out.println("This input is not supported, please enter an option number from the list");
			break;
		}
   }//While end
	
	
}


}//end class
