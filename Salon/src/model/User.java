package model;


public class User {
	private int idUser;
	private String username;
	private String password;
	private String nume;
	private String prenume;
	private String email;
	private String cnp;
	private String nr_tel;
	private int loyal;
	public User (int id, String un, String pw, String nume, String prenume, String cnp, String email, String nr_tel, int loyal) {
		this.idUser=id;
		this.username=un;
		this.password=pw;
		this.nume=nume;
		this.prenume=prenume;
		this.cnp=cnp;
		this.email=email;
		this.loyal=loyal;
		this.nr_tel=nr_tel;
	}

	public User (String un, String pw, String nume, String prenume, String cnp, String email, String nr_tel, int loyal) {
		this.username=un;
		this.password=pw;
		this.nume=nume;
		this.prenume=prenume;
		this.cnp=cnp;
		this.email=email;
		this.loyal=loyal;
		this.nr_tel=nr_tel;
	}
	
	public User() {
		this.idUser=1;
		this.username="";
		this.password="";
		this.nume="";
		this.prenume="";
		this.cnp="";
		this.email="";
		this.loyal=0;
		this.nr_tel="";
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getNr_tel() {
		return nr_tel;
	}

	public void setNr_tel(String nr_tel) {
		this.nr_tel = nr_tel;
	}

	public int getLoyal() {
		return loyal;
	}

	public void setLoyal(int loyal) {
		this.loyal = loyal;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + ", nume=" + nume
				+ ", prenume=" + prenume + ", email=" + email + ", cnp=" + cnp + ", nr_tel=" + nr_tel + ", loyal="
				+ loyal + "]";
	}
	
	
	
}
