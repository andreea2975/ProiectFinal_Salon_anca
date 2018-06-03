package BLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Validators.AllValidator;
import DAO.ServiciiDAO;
import model.Servicii;
import Validators.ServiciiValidator;

public class ServiciiBLL {

	private List<AllValidator<Servicii>> valid;
	public ServiciiBLL() {
		valid = new ArrayList<AllValidator<Servicii>>();
		valid.add(new ServiciiValidator());
	}
	public int findServiciiIdByNume(String un) {
		return ServiciiDAO.findServiciiByNume(un);
	}

	public Servicii findServiciiById(int id) {
		return ServiciiDAO.findById(id);
	}

	public int insertServicii(Servicii Servicii) {
		try {
			for (AllValidator<Servicii> x : valid) {
				x.validate(Servicii);
			}
		
			return ServiciiDAO.insert(Servicii);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public void updateServicii(Servicii Servicii) {
		try {
			for (AllValidator<Servicii> x : valid) {
				x.validate(Servicii);
			}
			ServiciiDAO.update(Servicii);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void deleteServicii(int id) {
		if (ServiciiDAO.findById(id) != null) {
			ServiciiDAO.delete(id);
		}
		else{
			JOptionPane.showMessageDialog(null, "id invalid", "Output", JOptionPane.ERROR_MESSAGE);
		}

	}

	public ArrayList<Servicii> getAllServicii() {
		return (ArrayList<Servicii>) ServiciiDAO.findAll();
	}
}

