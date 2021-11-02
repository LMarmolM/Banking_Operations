package project0;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ClassTest {

	@Test
	void testLogin() throws SQLException {

	CustomerDao cDao = DaoFactory.getCustomerDao();
	 assertEquals(true, cDao.Login("Juan Rios", "2436"));
	 assertEquals(false, cDao.Login("Juan Rios", "2434"));
	}
//
//	@Test
//	void testEmployeeLogin() {
//		EmployeeDao dao = DaoFactory.getEmployeeDao();
//	}
//
//	@Test
//	void testUserLogin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreateAccount() {
//		fail("Not yet implemented");
//	}

}
