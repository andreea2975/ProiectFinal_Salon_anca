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

import Connection.ConnectionFactory;
import model.Admin;

public class AdminDAO {
	protected static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());
	private final static String updateStatementString = "UPDATE admin SET logged=? WHERE username=?";
	private static final String insertStatementString = "INSERT INTO admin (username,password,logged)" + " VALUES (?,?,?)";
	private final static String findAdminByUN = "SELECT * from admin where username=?";
	private final static String findStatementString = "SELECT * FROM admin where id = ?";
	private final static String findAllStatementString = "SELECT * FROM admin";
	public static int findAdminIdByUN(String username) {
		int toReturn = 0;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement =dbConnection.prepareStatement(findAdminByUN);
			findStatement.setString(1, username);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				toReturn = id;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Find-admin:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static Admin findById(int id) {
		Admin toReturn = null;

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
				int log=rs.getInt("logged");
				toReturn = new Admin(id, username,password,log);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Find-admin:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static void login(Admin admin) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			admin.setLog(1);
			updateStatement.setInt(1, admin.getLog());
			updateStatement.setString(2, admin.getUsername());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}

	}

	public static void logout() {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(3, 0);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Insert-user: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	public static int insert(Admin admin) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, admin.getUsername());
			insertStatement.setString(2, admin.getPassword());
			insertStatement.setInt(3, admin.getLog());
		
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Insert-admin: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	public static List<Admin> findAll() {
		ArrayList<Admin> admins = new ArrayList<Admin>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;

		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next() && rs != null) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				int id=rs.getInt("id");
				int log=rs.getInt("logged");
				Admin toReturn = new Admin(id, username, password, log);
				admins.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "All-admins: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return admins;
	}
}

