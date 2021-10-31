package project0;

import java.sql.SQLException;

public class DaoFactory {
private static EmployeeDao eDao;
private static CustomerDao cDao;

private DaoFactory() {}

public static EmployeeDao getEmployeeDao() throws SQLException {
	if (eDao ==null) {
		eDao = new EmployeeDaoImpl();
	}
	return eDao;
}

public static CustomerDao getCustomerDao() throws SQLException {
	if (cDao ==null) {
		cDao = new CustomerDaoImpl();
	}
	return cDao;
}

}
