package banking.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import banking.db.Database;
import banking.model.TestAccount;

public class TestAccountDao {

	/**
	 * Returneaza contul cu ID-ul dat.
	 * Daca nu exista cont, va returna null.
	 */
	public TestAccount getAccountById(Integer id) {
		Connection connection = null;
		try {
			
			connection = Database.getConnection();
			
			String query = "SELECT id, name, sum FROM test_accounts WHERE id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			Integer indexParameter = 1;
			statement.setInt(indexParameter++, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				
				Integer accountId = resultSet.getInt("id");
				String accountName = resultSet.getString("name");
				BigDecimal accountSum = resultSet.getBigDecimal("sum");
				
				TestAccount account = new TestAccount();
				account.setId(accountId);
				account.setName(accountName);
				account.setSum(accountSum);
				return account;
				
			} else {
				return null;
			}
			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}
	
	public void update(TestAccount accountToEdit) {
		Connection connection = null;
		try {
			
			connection = Database.getConnection();
			
			PreparedStatement statement = connection.prepareStatement("UPDATE test_accounts SET name = ?, sum = ? WHERE id = ?");
			int parameterIndex = 1;
			statement.setString(parameterIndex++, accountToEdit.getName());
			statement.setBigDecimal(parameterIndex++, accountToEdit.getSum());
			statement.setInt(parameterIndex++, accountToEdit.getId());
			
			statement.executeUpdate();
			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}
}