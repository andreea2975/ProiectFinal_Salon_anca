package FactoryLogin;

import Login.AdminLogin;
import Login.LoginGenerator;
import Login.UserLogin;

public class FactoryLogin {
	
String generator="";
	public String getGenerator() {
		return generator;
	}

	public FactoryLogin(String generator) {
		this.generator = generator;
	}
	public AdminLogin Report1() {
		if (generator.equals("admin")) {
			
		}
		return new AdminLogin();}
		
		public UserLogin Report() {
			if (generator.equals("user")) {
		}
			return new UserLogin();
	
	
	
	}
	
}
