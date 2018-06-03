package model;

public class Admin {
	private int id;
	private String username;
	private String password;
	private int log;

	public Admin(int id,String un, String pw, int log) {
		this.id=id;
		this.username=un;
		this.password=pw;
		this.log=log;
	}
	public Admin(String un, String pw) {
		this.username=un;
		this.password=pw;
		this.log=0;
	}
	public Admin(String un, String pw,int log) {
		this.username=un;
		this.password=pw;
		this.log=log;
	}
	public Admin(int id,String un, String pw) {
		this.username=un;
		this.password=pw;
		this.id=id;
	}
	public Admin() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLog() {
		return log;
	}
	public void setLog(int log) {
		this.log = log;
	}
	
}
