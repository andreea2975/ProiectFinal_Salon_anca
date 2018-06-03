package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import Connection.ConnectionFactory;


public class UserDAO {
	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO user (username,password,nume,prenume,cnp,email,nr_tel,loyal)" + " VALUES (?,?,?,?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM user where id = ?";
	private final static String updateStatementString = "UPDATE user SET password=?,nume=?,prenume=?,cnp=?,email=?,nr_tel=?,loyal=? WHERE username=?";
	private final static String deleteStatementString = "DELETE FROM user WHERE id=? ";
	private final static String findAllStatementString = "SELECT * FROM user";
	private final static String findUserByUN = "SELECT * from user where username=?";
	/* CAUTA CLIENT */
	

	public static int findUserIdByUN(String username) {
		int toReturn = 0;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement =dbConnection.prepareStatement(findUserByUN);
			findStatement.setString(1, username);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				toReturn = id;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Find-user:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static User findById(int id) {
		User toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				String cnp = rs.getString("cnp");
				String email = rs.getString("email");
				String nr_tel = rs.getString("nr_tel");
				int loyal = rs.getInt("loyal");
				toReturn = new User(id, username,password,nume, prenume, cnp, email, nr_tel, loyal);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Find-user:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/* INTRODUCERE CLIENT */
	
	public static int insert(User user) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getUsername());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getNume());
			insertStatement.setString(4, user.getPrenume());
			insertStatement.setString(5, user.getCnp());
			insertStatement.setString(6, user.getEmail());
			insertStatement.setString(7, user.getNr_tel());
			insertStatement.setInt(8, user.getLoyal());
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Insert-user: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	
	/* UPDATE USER */

	public static void update(User user) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, user.getUsername());
			updateStatement.setString(2, user.getPassword());
			updateStatement.setString(3, user.getNume());
			updateStatement.setString(4, user.getPrenume());
			updateStatement.setString(5, user.getCnp());
			updateStatement.setString(6, user.getEmail());
			updateStatement.setString(7, user.getNr_tel());
			updateStatement.setInt(8, user.getLoyal());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Update-user:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}

	}	
	
	
	/* STERGERE DUPA ID */
	
	public static void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Delete-user: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/* TOTAL CLIENTI */
	
	public static List<User> findAll() {
		ArrayList<User> users = new ArrayList<User>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;

		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next() && rs != null) {
				int idUser = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String nume = rs.getString("nume");
				String prenume = rs.getString("prenume");
				String cnp = rs.getString("cnp");
				String email = rs.getString("email");
				String nr_tel = rs.getString("nr_tel");
				int loyal = rs.getInt("loyal");
				User toReturn = new User(idUser, username,password,nume, prenume, cnp, email, nr_tel, loyal);
				users.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "All-users: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return users;
	}
	



}
