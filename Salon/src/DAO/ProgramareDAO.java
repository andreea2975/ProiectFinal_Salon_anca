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
import java.sql.Date;
import Connection.ConnectionFactory;
import model.Programare;
public class ProgramareDAO {
	protected static final Logger LOGGER = Logger.getLogger(ProgramareDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO programare (id_pers,data_programarii,bonus_points,total)" + " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM programare where id = ?";
	private final static String updateStatementString = "UPDATE programare SET id_pers=?,data_programarii=?,bonus_points=?, total=? WHERE id=?";
	private final static String deleteStatementString = "DELETE FROM programare WHERE id=? ";
	private final static String findAllStatementString = "SELECT * FROM programare";

	public static Programare findById(int id) {
		Programare toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				Date data_programarii = rs.getDate("data_programarii");
				int id_pers = rs.getInt("id_pers");
				int bonus_points = rs.getInt("bonus_points");
				double total = rs.getDouble("total");
				toReturn = new Programare( id,  id_pers,  data_programarii,  bonus_points,  total);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Programare: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Programare cmd) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, cmd.getId_pers());
			insertStatement.setDate(2,cmd.getData_programarii());
			insertStatement.setInt(3, cmd.getBonus_points());
			insertStatement.setDouble(4, cmd.getTotal());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Programare_insert: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static void update(Programare cmd) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, cmd.getId_pers());
			updateStatement.setDate(2, cmd.getData_programarii());
			updateStatement.setInt(3, cmd.getBonus_points());
			updateStatement.setDouble(4, cmd.getTotal());
			updateStatement.setInt(5, cmd.getId());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Programare_update: " + e.getMessage());
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

	public static void delete(Programare cmd) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setLong(1, cmd.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Programare_delete: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}

	}

	public static List<Programare> findAll() {
		ArrayList<Programare> total_comenzi = new ArrayList<Programare>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;

		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next() && rs != null) {
				int id = rs.getInt("id");
				Date data_programarii = rs.getDate("data_programarii");
				int id_pers = rs.getInt("id_pers");
				int bonus_points = rs.getInt("bonus_points");
				int pret_total = rs.getInt("total");
				Programare toReturn = new Programare(id,id_pers,data_programarii,bonus_points,pret_total);
				total_comenzi.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Comenzi_total: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}

		return total_comenzi;

	}
}
