package BLL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import Validators.AllValidator;
import model.User;
import DAO.UserDAO;
import Validators.EmailValidator;
import Validators.UserValidator;

public class UserBLL implements Observer{

	private List<AllValidator<User>> valid;


	public UserBLL() {
		valid = new ArrayList<AllValidator<User>>();
		valid.add(new EmailValidator());
		valid.add(new UserValidator());
	}

	public int findUserIdByUN(String un) {
		return UserDAO.findUserIdByUN(un);
	}
	public User findUserById(int id) {
		return UserDAO.findById(id);
	}

	public int insertUser(User x) {
		try {
			for (AllValidator<User> a : valid) {
				a.validate(x);
			}
			
			return UserDAO.insert(x);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public void updateUser(User user) {
		try {
			for (AllValidator<User> x : valid) {
				x.validate(user);
			}
			UserDAO.update(user);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void deleteUser(int id) {
		if (UserDAO.findById(id) != null) {
			UserDAO.delete(id);
		}
		else{
			JOptionPane.showMessageDialog(null, "id invalid", "Output", JOptionPane.ERROR_MESSAGE);
		}

	}

	public ArrayList<User> getAllUsers() {
		return (ArrayList<User>) UserDAO.findAll();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "S-a inserat o programare!");
	}

	
}

