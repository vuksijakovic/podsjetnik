package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Obaveza implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ime;
	private LocalDate datum;
	private int ranije;
	public Obaveza(String ime, LocalDate datum, int ranije) {
		super();
		this.ime = ime;
		this.ranije = ranije;
		this.datum = datum;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getDatum1() {
		return datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
	public String getDanaDo() {
		LocalDate danas = LocalDate.now();
		if(danas.getYear()!= datum.getYear()) return "365+";
		int dani=0;
		for(int i=danas.getMonthValue()+1; i<datum.getMonthValue(); i++) {
			if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12) {
				dani+=31;
			}
			else if (i==2) {
				dani+=28;
				if((datum.getYear()%4==0 && datum.getYear()%100!=0) || datum.getYear()%400 ==0) {
					dani++;
				}
			}
			else {
				dani+=30;
			}
		}
		
		if(danas.getMonth()!= datum.getMonth()) {
			int i = danas.getMonthValue();
			if(i==1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12) {
				dani+= 31 - danas.getDayOfMonth();
			}
			else if (i==2) {
				dani+=28 - danas.getDayOfMonth();
				if((datum.getYear()%4==0 && datum.getYear()%100!=0) || datum.getYear()%400 ==0) {
					dani++;
				}
			}
			else {
				dani+=30 - danas.getDayOfMonth();
			}
			dani += datum.getDayOfMonth();
		}
		else {
			dani = datum.getDayOfMonth() - danas.getDayOfMonth();
		}
		return dani+"";
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public int getRanije() {
		return ranije;
	}
	public void setRanije(int ranije) {
		this.ranije = ranije;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ime + " " + datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + ranije ;
	}
}
