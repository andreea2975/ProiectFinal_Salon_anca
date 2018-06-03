package model;

public class Cos {
	private int id;
	private int id_servicii;
	private int id_programare;
	private double pret;
	public Cos(int id, int id_servicii, int id_programare, double pret) {
		this.id=id;
		this.id_programare=id_programare;
		this.id_servicii=id_servicii;
		this.pret=pret;
	}
	public Cos( int id_servicii, int id_programare, double pret) {
		this.id_programare=id_programare;
		this.id_servicii=id_servicii;
		this.pret=pret;
	}
	public Cos(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_servicii() {
		return id_servicii;
	}
	public void setId_servicii(int id_servicii) {
		this.id_servicii = id_servicii;
	}
	public int getId_programare() {
		return id_programare;
	}
	public void setId_programare(int id_programare) {
		this.id_programare = id_programare;
	}
	public double getPret() {
		return pret;
	}
	public void setPret(double pret) {
		this.pret = pret;
	}
	@Override
	public String toString() {
		return "Cos [id=" + id + ", id_servicii=" + id_servicii + ", id_programare=" + id_programare + ", pret=" + pret
				+ "]";
	}
	
	
	
}
