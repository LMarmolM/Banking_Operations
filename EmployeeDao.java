package project0;
import java.sql.SQLException;
import java.util.List;
public interface EmployeeDao {
	
	boolean Login(String name, String password) throws SQLException;
	
	void updateCustomer(Customer customer) throws SQLException;

	Customer getCustomerById(int id) throws SQLException;

	//List<Customer> getUsers() throws SQLException;
	
}
