package project0;
import java.sql.SQLException;
import java.util.List;
public interface TransactionDao {
	
	List<Transaction> checkTransaction(String name) throws SQLException;

	void deleteTransaction(int id) throws SQLException;
}
