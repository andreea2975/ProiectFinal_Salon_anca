package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO.AdminDAO;
import model.Admin;
import model.User;

public class CreareAdmin {

	@Test
	public void test() {
		Admin a= new Admin("anca","anca",1);
		assertEquals("anca", a.getUsername());
	}

}
