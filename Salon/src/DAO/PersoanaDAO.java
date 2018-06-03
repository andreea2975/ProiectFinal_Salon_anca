package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;
import model.Persoana;

public class PersoanaDAO {
	
	protected static final Logger LOGGER = Logger.getLogger(PersoanaDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO persoana (id_user,nume,prenume,email,data_nasterii,varsta,adresa,sex)" + " VALUES (?,?,?,?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM persoana where id = ?";
	private final static String updateStatementString = "UPDATE persoana SET id_user=?,nume=?,prenume=?,email=?,data_nasterii=?,varsta=?,adresa=?,sex=? WHERE id=?";
	private final static String deleteStatementString = "DELETE FROM persoana WHERE id=? ";
	private final static String findAllStatementString = "SELECT * FROM persoana";
	private final static String findUserByUN = "SELECT * from persoana where id=?";

	public static Persoana findById(int id) {
		Persoana toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int idUser = rs.getInt("id_user");
				String  nume= rs.getString("nume");
				String prenume = rs.getString("prenume");
				String email = rs.getString("email");
				Date data_nasterii = rs.getDate("data_nasterii");
				int varsta = rs.getInt("varsta");
				String adresa = rs.getString("adresa");
				String sex = rs.getString("sex");
				//public Persoana (int id,int ui,String nume, String prenume, String email, Date data_nasterii,int varsta, String adresa, String sex)
				toReturn = new Persoana(id,idUser,nume,prenume,email,data_nasterii,varsta,adresa,sex);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	public static int findByIdUser(int id) {
		int toReturn = 0;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findUserByUN);
			findStatement.setLong(1, id);
			rs = findStatement.executeQuery();
			if (rs.next()) {

				int id1 = rs.getInt("id");
				toReturn = id1;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(Persoana det) {
		Connection dbConnection = ConnectionFactory.getConnection();
		
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, det.getUser_id());
			insertStatement.setString(2, det.getNume());
			insertStatement.setString(3, det.getPrenume());
			insertStatement.setString(4, det.getEmail());
			insertStatement.setDate(5, det.getData_nasterii());
			insertStatement.setInt(6, det.getVarsta());
			insertStatement.setString(7, det.getAdresa());
			insertStatement.setString(8, det.getSex());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana_insert: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static void update(Persoana det) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			//idUser=?,adresa_livrare=?,detalii_comanda=?,ora_plasarii=?,comanda_efectuata=?,metoda_plata=?,total_plata=? WHERE id=?"
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, det.getUser_id());
			updateStatement.setString(2, det.getNume());
			updateStatement.setString(3, det.getPrenume());
			updateStatement.setString(4, det.getEmail());
			updateStatement.setDate(5, det.getData_nasterii());
			updateStatement.setInt(6, det.getVarsta());
			updateStatement.setString(7, det.getAdresa());
			updateStatement.setString(8, det.getSex());
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana_update: " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "Delete-persoana: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	public static void delete(Persoana cos) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setLong(1, cos.getId());
			deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana_delete: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	public static List<Persoana> findAll() {
		ArrayList<Persoana> total_cosuri = new ArrayList<Persoana>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;
		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();
			while (rs.next() && rs != null) {
				int id = rs.getInt("id");
				int idUser = rs.getInt("id_user");
				String  nume= rs.getString("nume");
				String prenume = rs.getString("prenume");
				String email = rs.getString("email");
				Date data_nasterii = rs.getDate("data_nasterii");
				int varsta = rs.getInt("varsta");
				String adresa = rs.getString("adresa");
				String sex = rs.getString("sex");
				
				Persoana toReturn = new Persoana(id,idUser,nume,prenume,email,data_nasterii,varsta,adresa,sex);
				total_cosuri.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Persoana_total: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}

		return total_cosuri;

	}

}
