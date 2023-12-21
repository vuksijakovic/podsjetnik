package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Korisnik implements Serializable{

	private static final long serialVersionUID = 1L;

	private String ime;
	private String prezime;
	private String email;
	private String lozinka;
	private List<Obaveza> obaveze = new ArrayList<>();
	public Korisnik(String ime, String prezime, String email,  String lozinka) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public List<Obaveza> getObaveze() {
		return obaveze;
	}
	public void setObaveze(List<Obaveza> obaveze) {
		this.obaveze = obaveze;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(email, other.email);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return email + " " + lozinka;
	}
}
