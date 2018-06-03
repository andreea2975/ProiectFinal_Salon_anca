package Validators;

import model.Admin;


public class AdminValidator implements AllValidator<Admin> {
	@Override
	public void validate(Admin t) throws Exception {
		if (t.getUsername().length()==0) {
			throw new Exception("Empty field");
		}
		if (t.getPassword().length()==0) {
			throw new Exception("Empty field");
		}
		
	}

}
