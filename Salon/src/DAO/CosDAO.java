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

import model.Cos;
import model.Cos;
import Connection.ConnectionFactory;


public class CosDAO {
	protected static final Logger LOGGER = Logger.getLogger(CosDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO cos (servicii_id,programare_id,pret)" + " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM cos where id = ?";
	private final static String updateStatementString = "UPDATE cos SET servicii_id=?,programare_id=?,pret=? WHERE id=?";
	private final static String deleteStatementString = "DELETE FROM cos WHERE id=? ";
	private final static String findAllStatementString = "SELECT * FROM cos";
	/* CAUTA CLIENT */
	

	
	public static Cos findById(int idCos) {
		Cos toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, idCos);
			rs = findStatement.executeQuery();
			if (rs.next()) {
				int servicii_id = rs.getInt("servicii_id");
				int programare_id = rs.getInt("programare_id");
				double pret = rs.getDouble("pret");
				toReturn = new Cos(servicii_id,programare_id,pret);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Find-Cos:" + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/* INTRODUCERE CLIENT */
	
	public static int insert(Cos Cos) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Cos.getId_servicii());
			insertStatement.setInt(2, Cos.getId_programare());
			insertStatement.setDouble(3, Cos.getPret());
		
			insertStatement.executeUpdate();
			
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Insert-Cos: " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	
	/* UPDATE Cos */

	public static void update(Cos Cos) {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		ResultSet rs = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, Cos.getId_servicii());
			updateStatement.setInt(2, Cos.getId_programare());
			updateStatement.setDouble(3, Cos.getPret());
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Update-Cos:" + e.getMessage());
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
			LOGGER.log(Level.WARNING, "Delete-Cos: " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/* TOTAL CLIENTI */
	
	public static List<Cos> findAll() {
		ArrayList<Cos> Cosuri = new ArrayList<Cos>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findAllStatement = null;
		ResultSet rs = null;

		try {
			findAllStatement = dbConnection.prepareStatement(findAllStatementString);
			rs = findAllStatement.executeQuery();

			while (rs.next() && rs != null) {
				int idCos = rs.getInt("id");
				int servicii_id = rs.getInt("servicii_id");
				int programare_id = rs.getInt("programare_id");
				double pret = rs.getDouble("pret");
				Cos toReturn = new Cos(idCos,servicii_id,programare_id,pret);
				Cosuri.add(toReturn);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "All-Coss: " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return Cosuri;
	}
	



}
