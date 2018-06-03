package BLL;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Validators.AllValidator;
import model.Persoana;
import Validators.PersoanaValidator;
import DAO.PersoanaDAO;

public class PersoanaBLL {
	

	private List<AllValidator<Persoana>> valid;

	public PersoanaBLL() {
		valid = new ArrayList<AllValidator<Persoana>>();
		//valid.add(new PersoanaValidator());
	}

	public Persoana findCosById(int id) {
		return PersoanaDAO.findById(id);
	}
	public int findPersoanaByIdUser(int id) {
		return PersoanaDAO.findByIdUser(id);
	}

	public int insertPersoana(Persoana pers) {
		try {
			for (AllValidator<Persoana> x : valid) {
				x.validate(pers);
			}
		
			return PersoanaDAO.insert(pers);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public void updatePersoana(Persoana pers) {
		try {
			for (AllValidator<Persoana> x : valid) {
				x.validate(pers);
			}
			PersoanaDAO.update(pers);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void deletePersoana(int id) {
		if (PersoanaDAO.findById(id) != null) {
			PersoanaDAO.delete(id);
		}
		else{
			JOptionPane.showMessageDialog(null, "id invalid", "Output", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ArrayList<Persoana> getAllPersoane() {
		return (ArrayList<Persoana>) PersoanaDAO.findAll();
	}

}
