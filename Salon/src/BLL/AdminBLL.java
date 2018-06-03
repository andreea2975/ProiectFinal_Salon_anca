package BLL;
import java.util.ArrayList;
import java.util.List;
import Validators.AdminValidator;
import Validators.AllValidator;
import model.Admin;
import DAO.AdminDAO;



public class AdminBLL {

	private List<AllValidator<Admin>> valid;

	public AdminBLL() {
		valid = new ArrayList<AllValidator<Admin>>();
		//valid.add(new EmailValidator());
		valid.add(new AdminValidator());
	}

	public int findAdminIdByUN(String un) {
		return AdminDAO.findAdminIdByUN(un);
	}
	public Admin findAdminById(int id) {
		return AdminDAO.findById(id);
	}

	public void login(Admin user) {
		try {
			for (AllValidator<Admin> x : valid) {
				x.validate(user);
			}
			AdminDAO.login(user);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	public int insertUser(Admin admin) {
		try {
			for (AllValidator<Admin> x : valid) {
				x.validate(admin);
			}
		
			return AdminDAO.insert(admin);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return 0;
		}
	}

	public ArrayList<Admin> getAllAdmins() {
		return (ArrayList<Admin>) AdminDAO.findAll();
	}
}

