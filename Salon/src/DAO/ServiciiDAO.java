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
import model.Servicii;
public class ServiciiDAO {
	protected static final Logger LOGGER = Logger.getLogger(ServiciiDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO servicii (categorie,denumire,detalii,bonus_value,pret)" + " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM servicii where id = ?";
	private final static String updateStatementString = "UPDATE servicii SET categorie=?,denumire=?,detalii=?,bonus_value=?,pret=? WHERE id=?";
	private final static String deleteStatementString = "DELETE FROM servicii WHERE id=? ";
	private final static String findUserByUN = "SELECT * from servicii where categorie=?";
	private final static String findAllStatementString = "SELECT * FROM servicii";

	public static int findServiciiByNume(String nume) {
		int toReturn = 0;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement =dbConnection.prepareStatement(findUserByUN);
			findStatement.setString(1, nume);
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
	public static Servicii findById(int id) {
		Servicii toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				String detalii=rs.getString("detalii");
				Double pret = rs.getDouble("pret");
				String categorie = rs.getString("categorie");
				int bonus_value=rs.getInt("bonus_value");
				String denumire = rs.getString("denumire");
				toReturn = new Servicii(id,categorie,denumire,detalii,bonus_value,pret);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Servicii: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}


	public static int insert(Servicii Servicii) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, Servicii.getCategorie());
			insertStatement.setString(2, Servicii.getDenumire());
			insertStatement.setString(3, Servicii.getDetalii());
			insertStatement.setInt(4, Servicii.getBonus_value());
			insertStatement.setDouble(5, Servicii.getPret());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Servicii_insert: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static void update(Servicii Servicii) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, Servicii.getCategorie());
			updateStatement.setString(2, Servicii.getDenumire());
			updateStatement.setString(3, Servicii.getDetalii());
			updateStatement.setInt(4, Servicii.getBonus_value());
			updateStatement.setDouble(5, Servicii.getPret());
			updateStatement.setInt(6, Servicii.getId());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Servicii_update: " + e.getMessage());
		} finally {

			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	public static void delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setLong(1, id);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Delete-user: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void delete(Servicii Servicii) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setLong(1, Servicii.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Cos_cumparaturi_delete: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}

	}

	public static List<Servicii> findAll() {
		ArrayList<Servicii> total_Servicii = new ArrayList<Servicii>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;

		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next() && rs != null) {
				int id = rs.getInt("id");
				String detalii=rs.getString("detalii");
				Double pret = rs.getDouble("pret");
				String categorie = rs.getString("categorie");
				int bonus_value = rs.getInt("bonus_value");
				String denumire = rs.getString("denumire");
				Servicii toReturn = new Servicii(id,categorie,denumire,detalii,bonus_value,pret);
				total_Servicii.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Servicii_total: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}

		return total_Servicii;

	}
}
