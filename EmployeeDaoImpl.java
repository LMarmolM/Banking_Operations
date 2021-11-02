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
		String sql = "update users set balance = ? where id =?;";	
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, customer.getBalance());
		preparedStatement.setInt(2, customer.getId());
		System.out.println(customer.getId());
		int count = preparedStatement.executeUpdate();
		if(count>0) System.out.println("Customer account successfully changed.");
		else System.out.println("Something went wrong");
	}
	

//	@Override
//	public List<Customer> getUsers() throws SQLException {
//		// TODO Auto-generated method stub
//		List<Customer> listedCustomers= new ArrayList<Customer>();
//        String sql = "Select id, name, email, status, balance from Users";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(sql);
//
//        while (resultSet.next()) {
//	        Customer customer = new Customer();
//
//	        customer.setId(resultSet.getInt(1));
//	        customer.setName(resultSet.getString(2));
//	        customer.setEmail(resultSet.getString(3));
//	        customer.setStatus(resultSet.getString(4));
//	        customer.setBalance(resultSet.getInt(5));
//        	listedCustomers.add(customer);    
//        	
//        }
//        System.out.println(listedCustomers);
//        return listedCustomers;
//	}//End list
	
	@Override
	public Customer getCustomerById(int id) throws SQLException {

	    String sql = "Select id,name,email,status,balance from users where id = "+id;
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
	  
	        return customer;
	     }
	     catch(Exception e){
	    	 System.out.println("This id does not correspond to anyone, the location is empty");
	    	return null; 
	     }

	}

	@Override
	public boolean Login(String name, String password) throws SQLException {
		//String sql = "select * from Employee where name = "+name+" and password = "+password;	
		//String sql = "select * from Employee where name= ? and password =?";
		String sql = "Call employeePassword(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}

	
	
}
