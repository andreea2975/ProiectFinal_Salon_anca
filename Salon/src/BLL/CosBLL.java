package BLL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Validators.AllValidator;
import model.Cos;
import DAO.CosDAO;
import Validators.CosValidator;

public class CosBLL {

	private List<AllValidator<Cos>> valid;

	public CosBLL() {
		valid = new ArrayList<AllValidator<Cos>>();
		valid.add(new CosValidator());
	}

	public Cos findCosById(int id) {
		return CosDAO.findById(id);
	}
	public int insertCos(Cos cos) {
		try {
			for (AllValidator<Cos> x : valid) {
				x.validate(cos);
			}
		
			return CosDAO.insert(cos);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public void updateCos(Cos cos) {
		try {
			for (AllValidator<Cos> x : valid) {
				x.validate(cos);
			}
			CosDAO.update(cos);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void deleteCos(int id) {
		if (CosDAO.findById(id) != null) {
			CosDAO.delete(id);
		}
		else{
			JOptionPane.showMessageDialog(null, "id invalid", "Output", JOptionPane.ERROR_MESSAGE);
		}
	}


	public ArrayList<Cos> getAllCos() {
		// TODO Auto-generated method stub
		return (ArrayList<Cos>) CosDAO.findAll();
	}
}

