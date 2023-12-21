package utils;

import java.util.ArrayList;
import java.util.List;

import model.Korisnik;

public class Database {
	private static Database instance = new Database();
	private List<Korisnik> korisnici = new ArrayList<>();
	private Korisnik trenutniKorisnik;
	private Database() {
	}
	public static Database getInstance() {
		return instance;
	}
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}
	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	public Korisnik getTrenutniKorisnik() {
		return trenutniKorisnik;
	}
	public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
		this.trenutniKorisnik = trenutniKorisnik;
	}
}
