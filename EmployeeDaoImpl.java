package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

	Connection connection;
	
	public EmployeeDaoImpl() throws SQLException{
		this.connection = ConnectionFactory.getConnection();
	}
	
	//Employee can change ONLY the customer Approval Status. Employees do not have any other authority.
	public void updateCustomer(Customer customer) throws SQLException {
		//The customer object passed down MUST have all variables filled in
		//Since it is recovered from the database.
		//Also ensures no mistakes since id is primary key.
		String sql = "update users set account =? , balance = ? where id =?;";	
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, customer.getAccount());
		preparedStatement.setInt(2, customer.getBalance());
		preparedStatement.setInt(3, customer.getId());
		System.out.println(customer.getId());
		int count = preparedStatement.executeUpdate();
		if(count>0) System.out.println("Customer account value successfully changed.");
		else System.out.println("Something went wrong");
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
	public Customer getCustomerById(int id) throws SQLException {

	    String sql = "Select id,name,email,status,balance,account from users where id = "+id;
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
	  
	        return customer;
	     }
	     catch(Exception e){
	    	 System.out.println("This id does not correspond to annyone, the location is empty");
	    	return null; 
	     }

	}

	@Override
	public boolean Login(String name, String password) throws SQLException {
		//String sql = "select * from Employee where name = "+name+" and password = "+password;	
		String sql = "select * from Employee where name= ? and password =?";		
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}

	
	
}
