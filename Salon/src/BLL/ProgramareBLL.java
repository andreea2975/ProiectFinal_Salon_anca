package BLL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

import javax.swing.JOptionPane;
import Validators.AllValidator;
import model.Programare;
import DAO.ProgramareDAO;
import DAO.UserDAO;
import Validators.ProgramareValidator;

public class ProgramareBLL extends Observable{

	private List<AllValidator<Programare>> valid;

	public ProgramareBLL() {
		valid = new ArrayList<AllValidator<Programare>>();
		valid.add(new ProgramareValidator());
	}

	public Programare findProgramareById(int id) {
		return ProgramareDAO.findById(id);
	}

	public int insertProgramare(Programare cmd) {
		try {
			for (AllValidator<Programare> x : valid) {
				x.validate(cmd);
			}
			setChanged();
	        notifyObservers();
			return ProgramareDAO.insert(cmd);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public void updateProgramare(Programare cmd) {
		try {
			for (AllValidator<Programare> x : valid) {
				x.validate(cmd);
			}
			ProgramareDAO.update(cmd);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void deleteProgramare(int id) {
		if (ProgramareDAO.findById(id) != null) {
			ProgramareDAO.delete(id);
		}
		else{
			JOptionPane.showMessageDialog(null, "id invalid", "Output", JOptionPane.ERROR_MESSAGE);
		}

	}


	public ArrayList<Programare> getAllProgramari() {
		// TODO Auto-generated method stub
		return (ArrayList<Programare>) ProgramareDAO.findAll();
	}
}

