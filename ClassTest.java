package project0;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ClassTest {

	@Test
	//Check if database correctly returns cases for employee and customer logins
	void checkLogins() throws SQLException {

		CustomerDao cDao = DaoFactory.getCustomerDao();
		assertEquals(true, cDao.Login("Juan Rios", "2436"));
		assertEquals(false, cDao.Login("Juan Rios", "2434"));
		
		EmployeeDao eDao = DaoFactory.getEmployeeDao();
		
		assertEquals(true, eDao.Login("Mike", "abc"));
		assertEquals(false, eDao.Login("Mike", "LOP"));
	}
	
	@Test
	//Check that users can be returned properly
	void checkDatabase() throws SQLException {

		CustomerDao cDao = DaoFactory.getCustomerDao();
		assertEquals(true, !cDao.getUsers().isEmpty());
		
	}
}
