package model;

import java.sql.Date;

public class Persoana {
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public Date getData_nasterii() {
		return data_nasterii;
	}
	public void setData_nasterii(Date data_nasterii) {
		this.data_nasterii = data_nasterii;
	}
	public int getVarsta() {
		return varsta;
	}
	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	private int id;
	private int user_id;
	private String nume;
	private String prenume;
	private String email;
	private Date data_nasterii;
	private int varsta;
	private String adresa;
	private String sex;

	public Persoana(int id, int user_id, String nume, String prenume, String email, Date data_nasterii, int varsta,
			String adresa, String sex) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.data_nasterii = data_nasterii;
		this.varsta = varsta;
		this.adresa = adresa;
		this.sex = sex;
	}
	public Persoana( int user_id, String nume, String prenume, String email, Date data_nasterii, int varsta,
			String adresa, String sex) {
		super();
		this.user_id = user_id;
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.data_nasterii = data_nasterii;
		this.varsta = varsta;
		this.adresa = adresa;
		this.sex = sex;
	}
	
	public Persoana(){}
	@Override
	public String toString() {
		return "Persoana [id=" + id + ", user_id=" + user_id + ", nume=" + nume + ", prenume=" + prenume + ", email="
				+ email + ", data_nasterii=" + data_nasterii + ", varsta=" + varsta + ", adresa=" + adresa + ", sex="
				+ sex + "]";
	}
	
	
}
