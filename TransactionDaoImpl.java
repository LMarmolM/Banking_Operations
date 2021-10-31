package project0;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TransactionDaoImpl implements TransactionDao {
Connection connection;
	
	public TransactionDaoImpl() throws SQLException{
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public void deleteTransaction(int id) throws SQLException {
			//DELETE FROM table_name WHERE condition;
		String sql = "delete from transactions where id=?";		
				
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,id);
		int count = preparedStatement.executeUpdate();
		if(count>0) System.out.println("Transaction concluded.\n");
		else System.out.println("Something went wrong, your balance is unchanged\n ");
	        
			
		return ;
	}

	@Override
	public List<Transaction> checkTransaction(String name) throws SQLException {
		String sql = "select id,fromUser, amount from transactions where toUser= ?";		
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Transaction> listedTransactions = new ArrayList<Transaction>(); 
			while (resultSet.next()) {
				Transaction current = new Transaction(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3));
				//System.out.println( current.toString());
				
				listedTransactions.add(current);
	        	}
	        
			
	return listedTransactions;
	}

}
