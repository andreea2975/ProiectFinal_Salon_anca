package model;

import java.sql.Date;

public class Programare {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBonus_points() {
		return bonus_points;
	}
	public void setBonus_points(int bonus_points) {
		this.bonus_points = bonus_points;
	}
	public int getId_pers() {
		return id_pers;
	}
	public void setId_pers(int id_pers) {
		this.id_pers = id_pers;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getData_programarii() {
		return data_programarii;
	}
	public void setData_programarii(Date data_programarii) {
		this.data_programarii = data_programarii;
	}


	private int id;
	private int bonus_points;

	private int id_pers;
	private double total;
	private Date data_programarii;
	
	public Programare(int id, int id_pers, Date data_programarii, int bonus_points, double total) {
		this.id=id;
		this.data_programarii=data_programarii;
		this.id_pers=id_pers;
		this.bonus_points=bonus_points;
		this.total=total;
	}
	public Programare(int id_pers, Date data_programarii, int bonus_points, double total) {
		this.data_programarii=data_programarii;
		this.id_pers=id_pers;
		this.bonus_points=bonus_points;
		this.total=total;
	}
	
	public Programare(){}
	
	
	@Override
	public String toString() {
		return "Programare [id=" + id + ", bonus_points=" + bonus_points + ", id_detalii=" + id_pers + ", total="
				+ total + ", data_programarii=" + data_programarii + "]";
	}

	
	
}
