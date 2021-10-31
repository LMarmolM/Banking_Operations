package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

	Connection connection;
	
	public CustomerDaoImpl() throws SQLException{
		this.connection = ConnectionFactory.getConnection();
	}
	
	//Employee can change ONLY the customer Approval Status. Employees do not have any other authority.
	public void updateCustomer(Customer customer) throws SQLException {
		String sql = "update users set balance = ? where name =?;";	
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, customer.getBalance());
		preparedStatement.setString(2, customer.getName());
		int count = preparedStatement.executeUpdate();
		if(count>0) System.out.println("Balance updated.");
		else System.out.println("Something went wrong, your balance is unchanged");
	}
	

	@Override
	public List<Customer> getUsers() throws SQLException {
		// TODO Auto-generated method stub
		List<Customer> listedCustomers= new ArrayList<Customer>();
        String sql = "Select id, name, email, status, balance, account from Users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
	        Customer customer = new Customer();

	        customer.setId(resultSet.getInt(1));
	        customer.setName(resultSet.getString(2));
	        customer.setEmail(resultSet.getString(3));
	        customer.setStatus(resultSet.getString(4));
	        customer.setBalance(resultSet.getInt(5));
	        customer.setAccount(resultSet.getString(6));
        	listedCustomers.add(customer);    
        	
        }
        System.out.println(listedCustomers);
        return listedCustomers;
	}//End list
	
	@Override
	public Customer getCustomerByName(String name) throws SQLException {

	    String sql = "Select id,name,email,status,balance,account from users where name = '"+name+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Customer customer = new Customer();
	    resultSet.next();
        try {
        	customer.setId(resultSet.getInt(1));
	        customer.setName(resultSet.getString(2));
	        customer.setEmail(resultSet.getString(3));
	        customer.setStatus(resultSet.getString(4));
	        customer.setBalance(resultSet.getInt(5));
	        customer.setAccount(resultSet.getString(6));
	     }
	     catch(Exception e){
	    	 System.out.println("This id does not correspond to annyone, the location is empty ");
	    	return null; 
	     }
        return customer;
	}

	public boolean Login(String name, String password) throws SQLException {	
		String sql = "select * from users where name= ? and password =?";		
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}

	@Override
	public boolean postTransfer(int transfer, String name, String userDestiny) throws SQLException {
		if(getCustomerByName(userDestiny)!=null) {
			String sql = "insert transactions (amount,fromUser,toUser ) values(?,?,?)";	
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, transfer);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, userDestiny);
			int count = preparedStatement.executeUpdate();
			if(count>0) System.out.println("Transaction has been posted.");
			else System.out.println("Transaction went wrong, your balance is unchanged");
		}
		else{
			System.out.println("\nUsername does not exist\n");
			return false;
		}
		return true;
	}//endtransfer

	

	//Refund
	public void refundOther(String user, int amount) throws SQLException {

		Customer other = getCustomerByName(user);
		int oldBalance = other.getBalance();
		int newBalance = oldBalance + amount;
		other.setBalance(newBalance);
		updateCustomer(other);
	}

	
	
}
