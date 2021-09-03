package banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import banking.db.Database;
import banking.model.AccountInfo;
import banking.model.TransferDetails;

public class AccountDao {

	public List<String> getAccountsById(Integer userId) {
		Connection connection = null;
		try {

			connection = Database.getConnection();

			String query = "SELECT NrCont FROM conturi WHERE UserID = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);

			ResultSet resultSet = statement.executeQuery();

			List<String> account = new LinkedList<String>();
			while (resultSet.next()) {
				account.add(resultSet.getString("NrCont"));
				// System.out.println("get account:" + account);
			}
			return account;
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}

	public boolean checkNewAccountIfExist(String nrCont) {
		Connection connection = null;
		try {

			connection = Database.getConnection();
			String query = "SELECT * FROM conturi WHERE NrCont = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, nrCont);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				return true;
			} else {
				return false;
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}

	public boolean addNewAccount(String newAccountNr, Integer id) {
		Connection connection = null;
		try {

			connection = Database.getConnection();
			String query = "" + "INSERT INTO "
					+ "conturi(NrCont,Banca,UserID,Suma,Stare) "
					+ "VALUES(?,'BT',?,0,1)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newAccountNr);
			statement.setInt(2, id);
			statement.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			return false;
		} finally {
			Database.closeConnection(connection);
		}
	}

	public List<AccountInfo> viewAccountInfo(Integer userId) {
		Connection connection = null;
		try {

			connection = Database.getConnection();
			String query = "SELECT  ID, NrCont, Suma FROM conturi WHERE UserID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			List<AccountInfo> accountsInfo = new LinkedList<AccountInfo>();

			while (resultSet.next()) {

				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setId(resultSet.getInt("ID"));
				accountInfo.setNrCont(resultSet.getString("NrCont"));
				accountInfo.setSuma(resultSet.getDouble("Suma"));

				accountsInfo.add(accountInfo);
				// System.out.println("cont:" + accountInfo);
			}
			return accountsInfo;

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}

	// alimentare cont
	// select all account
	// update selecteted account

	public boolean updateAlimentareCont(AccountInfo account, Integer userId) {
		Connection connection = null;
		try {
			// System.out.println("update cont suma");
			connection = Database.getConnection();

			PreparedStatement statement = connection
					.prepareStatement("UPDATE conturi  SET Suma = Suma + ? WHERE ID=? AND UserID=?");
			int parameterIndex = 1;
			statement.setDouble(parameterIndex++, account.getSuma());
			statement.setInt(parameterIndex++, account.getId());
			statement.setInt(parameterIndex++, account.getUserId());

			statement.executeUpdate();
			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			Database.closeConnection(connection);
		}
	}

	public Integer getIdByNrCont(Integer nrcont) {

		Integer idCont = null;

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String stringGetId = "" + "SELECT ID " + "FROM conturi"
					+ " WHERE NrCont = ?";
			PreparedStatement statement = connection
					.prepareStatement(stringGetId);
			statement.setInt(1, nrcont);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				idCont = resultSet.getInt("id");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			Database.closeConnection(connection);
		}
		return idCont;

	}

	public Double getSumaByIdCont(Integer idCont) {

		Double Suma = null;

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String query = "" + "SELECT Suma FROM conturi WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, idCont);

			ResultSet resultSetSoldSursa = statement.executeQuery();

			if (resultSetSoldSursa.next()) {
				Suma = resultSetSoldSursa.getDouble("Suma");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			Database.closeConnection(connection);
		}
		return Suma;

	}

	public boolean addALimentareToTranzactii(Integer idCont, Double sumaAdaugata) {
		Connection connection = null;
		try {
			connection = Database.getConnection();

			Double soldFinal = getSumaByIdCont(idCont);
			Double soldInitial = soldFinal - sumaAdaugata;

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date now = new Date();
			String data = dateFormat.format(now);

			String queryString = "" + "INSERT INTO " + "tranzactii("
					+ "DataOperatiei," + "IdContSursa, "
					+ "SoldfinalContSursa," + "IdContDestinatie, "
					+ "SoldFinalContDestinatie, " + "SumaTranzactie" + ") "
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement statement = connection
					.prepareStatement(queryString);
			statement.setString(1, data);
			statement.setInt(2, idCont);
			statement.setDouble(3, soldInitial);
			statement.setInt(4, idCont);
			statement.setDouble(5, soldFinal);
			statement.setDouble(6, sumaAdaugata);
			statement.executeUpdate();

			return true;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return false;
		} finally {
			Database.closeConnection(connection);
		}

	}

	public List<String> getNumereConturiByIdClient(Integer clientId) {

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String queryString = "" + "SELECT NrCont " + "FROM conturi "
					+ "WHERE UserID = ? ";
			PreparedStatement statement = connection
					.prepareStatement(queryString);
			statement.setInt(1, clientId);

			ResultSet resultSet = statement.executeQuery();

			List<String> accounts = new LinkedList<String>();
			while (resultSet.next()) {
				accounts.add(resultSet.getString("NrCont"));
			}
			return accounts;
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}

	public boolean addTransferToTranzacti(TransferDetails transferDetails) {

		Connection connection = null;

		try {
			connection = Database.getConnection();
			connection.setAutoCommit(false);

			Integer IdContDestinatie = getIdByNrCont(transferDetails
					.getNrContDestinatie());
			Double soldFinalSursa = getSumaByIdCont(transferDetails
					.getIdContSursa());
			Double soldFinalDestinatie = getSumaByIdCont(IdContDestinatie);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			String data = dateFormat.format(now);

			String queryString = "" + "INSERT INTO " + "tranzactii("
					+ "DataOperatiei," + "IdContSursa, "
					+ "SoldfinalContSursa," + "IdContDestinatie, "
					+ "SoldFinalContDestinatie, " + "SumaTranzactie" + ") "
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement statement = connection
					.prepareStatement(queryString);
			statement.setString(1, data);
			statement.setInt(2, transferDetails.getIdContSursa());
			statement.setDouble(3, soldFinalSursa);
			statement.setInt(4, IdContDestinatie);
			statement.setDouble(5, soldFinalDestinatie);
			statement.setDouble(6, transferDetails.getSumaTransfer());
			statement.executeUpdate();

			connection.commit();
			return true;
		} catch (SQLException sqle) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sqle.printStackTrace();
			return false;
		} finally {
			Database.closeConnection(connection);
		}
	}

	public boolean doTransfer(TransferDetails transferDetails, Integer idClient) {

		Connection connection = null;

		try {
			connection = Database.getConnection();
			connection.setAutoCommit(false);

			String updateSursa = "UPDATE conturi  SET Suma = Suma - ? WHERE UserID=? AND ID=?";

			PreparedStatement statementSursa = connection
					.prepareStatement(updateSursa);
			statementSursa.setDouble(1, transferDetails.getSumaTransfer());
			statementSursa.setInt(2, idClient);
			statementSursa.setInt(3, transferDetails.getIdContSursa());
			statementSursa.executeUpdate();

			String updateDestinatie = "UPDATE conturi SET Suma=Suma+? WHERE UserID=? AND NrCont=?";

			PreparedStatement statementDestinatie = connection
					.prepareStatement(updateDestinatie);
			statementDestinatie.setDouble(1, transferDetails.getSumaTransfer());
			statementDestinatie.setInt(2,
					transferDetails.getIdClientDestinatie());
			statementDestinatie
					.setInt(3, transferDetails.getNrContDestinatie());
			statementDestinatie.executeUpdate();

			connection.commit();
			return true;
		} catch (SQLException sqle) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		} finally {
			Database.closeConnection(connection);
		}

	}
}
