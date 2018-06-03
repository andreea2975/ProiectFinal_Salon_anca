package model;

public class Servicii {
	private int id;
	private String denumire;
	private String categorie;
	private String detalii;
	private int bonus_value;
	private Double pret;
	public Servicii(int id, String categorie, String denumire, String detalii, int bonus_value, Double pret) {
		this.id=id;
		this.denumire=denumire;
		this.categorie=categorie;
		this.bonus_value=bonus_value;
		this.detalii=detalii;
		this.pret=pret;
	}
	public Servicii(String categorie, String denumire, String detalii, int bonus_value, Double pret) {
		this.denumire=denumire;
		this.categorie=categorie;
		this.bonus_value=bonus_value;
		this.detalii=detalii;
		this.pret=pret;
	}
	
	public int getBonus_value() {
		return bonus_value;
	}
	public void setBonus_value(int bonus_value) {
		this.bonus_value = bonus_value;
	}
	public Servicii() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getDetalii() {
		return detalii;
	}
	public void setDetalii(String detalii) {
		this.detalii = detalii;
	}
	public Double getPret() {
		return pret;
	}
	public void setPret(Double pret) {
		this.pret = pret;
	}
	@Override
	public String toString() {
		return "Servicii [id=" + id + ", denumire=" + denumire + ", categorie=" + categorie + ", detalii=" + detalii
				+ ", bonus_value=" + bonus_value + ", pret=" + pret + "]";
	}
	
}
