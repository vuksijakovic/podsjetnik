package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Korisnik;
import model.Obaveza;
import utils.Database;

public class StartView extends GridPane{
	private Button prijaviSeBtn = new Button("Prijavi se");
	private Button registrujSeBtn = new Button("Registruj se");
	private Label emailLbl = new Label("Email");
	private Label passwordLbl = new Label("Šifra");
	private TextField emailTf = new TextField();
	private PasswordField passwordTf = new PasswordField();
	public StartView() {
		init();
		actions();
	}
	private void init() {
		this.addRow(0, emailLbl, emailTf);
		this.addRow(1, passwordLbl, passwordTf);
		this.addRow(2, prijaviSeBtn, registrujSeBtn);
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
	}
	
	private void actions() {
		registrujSeBtn.setOnAction(e->{
			Main.changeScene(new Scene(new RegisterView(), 300, 300));
		});
		registrujSeBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER))    Main.changeScene(new Scene(new RegisterView(), 300, 300));
		});
		prijaviSeBtn.setOnAction(e->{
			
			for(Korisnik k : Database.getInstance().getKorisnici()) {
				if(k.getLozinka().equals(passwordTf.getText()) && k.getEmail().equals(emailTf.getText())) {
					Database.getInstance().setTrenutniKorisnik(k);
					for (Obaveza o : Database.getInstance().getTrenutniKorisnik().getObaveze()) {
						if(!o.getDanaDo().equals("365+")) {
							if(Integer.parseInt(o.getDanaDo())<=o.getRanije()) {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setContentText(o.getIme() + " ističe za " + o.getDanaDo() +  " dan/a");
								alert.show();
							}
						}
					}
					Main.changeScene(new Scene(new MainView(), 500, 500));
					return;
				}
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Pogrešno uneseni podaci");
			alert.show();
		});
		prijaviSeBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				prijaviSeBtn.fire();
			}
		});
		emailTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				prijaviSeBtn.fire();
			}
		});
		passwordTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				prijaviSeBtn.fire();
			}
		});
	}
}
