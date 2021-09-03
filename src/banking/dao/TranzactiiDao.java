package banking.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import banking.db.Database;
import banking.model.TransactionDetails;

public class TranzactiiDao {
public List<TransactionDetails> getTrasactionList(Integer idCont, Date startDate, Date endDate) {
		
		Connection connection = null;
		try {
			connection = Database.getConnection();

			String queryString = ""
						+ "SELECT * "
						+ "FROM tranzactii "
						+ "WHERE IdContSursa=? "
						+ "AND DataOperatiei "
							+ "BETWEEN "
								+ "? "
								+ "AND "
								+ "? "
					+ "UNION "
						+ "SELECT * "
						+ "FROM tranzactii "
						+ "WHERE IdContDestinatie=? "
						+ "AND DataOperatiei "
							+ "BETWEEN "
								+ "? "
								+ "AND "
								+ "? ";
				
			PreparedStatement statement = connection.prepareStatement(queryString);
			statement.setInt(1, idCont);
			statement.setDate(2, startDate);
			statement.setDate(3, endDate);
			statement.setInt(4, idCont);
			statement.setDate(5, startDate);
			statement.setDate(6, endDate);

			ResultSet resultSet = statement.executeQuery();

			List<TransactionDetails> tranzactii = new LinkedList<TransactionDetails>();
			while (resultSet.next()) {
				
				Integer nrContSursa = getNumarContByIdClient(resultSet.getInt("IdContSursa"));
				Integer nrContDest = getNumarContByIdClient(resultSet.getInt("IdContDestinatie"));
				
				TransactionDetails tranzactie = new TransactionDetails();
				
				tranzactie.setDate(resultSet.getDate("DataOperatiei"));
				tranzactie.setContSursa(nrContSursa);
				tranzactie.setSoldFinalContSursa(resultSet.getDouble("SoldfinalContSursa"));
				tranzactie.setContDestinatie(nrContDest);
				tranzactie.setSoldFinalContDestinatie(resultSet.getDouble("SoldfinalContSursa"));
				tranzactie.setSumaTransfer(resultSet.getDouble("SumaTranzactie"));
				tranzactii.add(tranzactie);
			} 
			 return tranzactii;
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}

	public Integer getNumarContByIdClient(Integer idCont) {
	
	Connection connection = null;
	try {
		connection = Database.getConnection();

		String queryString = ""
				+ "SELECT NrCont "
				+ "FROM conturi "
				+ "WHERE ID = ? ";
		PreparedStatement statement = connection.prepareStatement(queryString);
		statement.setInt(1, idCont);

		ResultSet resultSet = statement.executeQuery();

		Integer nrCont = null;
		if (resultSet.next()) {
			nrCont = resultSet.getInt("NrCont");
		} 
		return nrCont;
	} catch (SQLException sqle) {
		throw new RuntimeException(sqle);
	} finally {
		Database.closeConnection(connection);
	}
}

}
