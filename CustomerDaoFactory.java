package project0;

import java.sql.SQLException;

public class CustomerDaoFactory {
private static EmployeeDao dao;

private CustomerDaoFactory() {}

public static EmployeeDao getEmployeeDao() throws SQLException {
	if (dao ==null) {
		dao = new EmployeeDaoImpl();
	}
	return dao;
}

}
