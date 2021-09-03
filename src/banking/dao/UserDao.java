package banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import banking.db.Database;
import banking.model.InfoUser;

public class UserDao {

	public boolean checkUser(String username, String password) {

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String queryString = "SELECT 1 " + "FROM clienti " + "WHERE "
					+ "username = ? " + "AND " + "parola = ?";
			PreparedStatement statement = connection
					.prepareStatement(queryString);
			statement.setString(1, username);
			statement.setString(2, password);

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

	public InfoUser getInfoUser(String username) {

		Connection connection = null;
		try {
			connection = Database.getConnection();

			String queryString = "SELECT * " + "FROM clienti "
					+ "WHERE username = ? ";
			PreparedStatement statement = connection
					.prepareStatement(queryString);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String firstname = resultSet.getString("nume");
				String lastname = resultSet.getString("prenume");
				InfoUser user = new InfoUser();
				user.setId(id);
				user.setNume(firstname);
				user.setPrenume(lastname);
				return user;
			} else {
				return null;
			}

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}
	public List<InfoUser> getInfoUser() {

		Connection connection = null;
		try {
			connection = Database.getConnection();
			
			String querySrting = ""
					+ "SELECT Id,Nume,Prenume "
					+ "FROM clienti ";
			PreparedStatement statement = connection.prepareStatement(querySrting);
			ResultSet resultSet = statement.executeQuery();
			
			List<InfoUser> listaClienti = new LinkedList<InfoUser>();
			while (resultSet.next()) {
				
				InfoUser infoUser = new InfoUser();
				
				infoUser.setId(resultSet.getInt("Id"));
				infoUser.setNume(resultSet.getString("Nume"));
				infoUser.setPrenume(resultSet.getString("Prenume")); 
				listaClienti.add(infoUser);	
			}
			return listaClienti;
			
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			Database.closeConnection(connection);
		}
	}


}