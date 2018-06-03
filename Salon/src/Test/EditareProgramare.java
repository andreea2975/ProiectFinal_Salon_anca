package Test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import model.Programare;
import DAO.ProgramareDAO;

public class EditareProgramare {

	@Test
	public void test() {
		Date data=new Date(0);
		Programare pp=new Programare(9,data,15,13.6);
		pp.setBonus_points(18);
		ProgramareDAO.update(pp);
		assertEquals(18, pp.getBonus_points());
	}

}
