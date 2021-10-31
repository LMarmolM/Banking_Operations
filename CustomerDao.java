package project0;
import java.sql.SQLException;
import java.util.List;
public interface CustomerDao {
	
	boolean Login(String name, String password) throws SQLException;
	
	void updateCustomer(Customer customer) throws SQLException;

	List<Customer> getUsers() throws SQLException;

	Customer getCustomerByName(String name) throws SQLException;

	boolean postTransfer(int transfer, String name, String userDestiny) throws SQLException;
	
}
