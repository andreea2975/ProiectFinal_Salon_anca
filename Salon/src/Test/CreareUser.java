package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class CreareUser {

	public void test() {
		User u=new User("andu","andu","Iacob","Anca","123","iacobanca01@yahoo.com","1",075);
		assertEquals("Iacob", u.getNume());
	}
}
