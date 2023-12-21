package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import main.Main;
import model.Korisnik;
import utils.Database;

public class RegisterView extends GridPane{
	private Label imeLbl = new Label("Ime");
	private Label prezimeLbl = new Label("Prezime");
	private Label emailLbl = new Label("E-mail");
	private Label passwordLbl = new Label("Šifra");
	private Label ppasswordLbl = new Label("Ponovljena šifra");
	private TextField imeTf = new TextField();
	private TextField prezimeTf = new TextField();
	private TextField emailTf = new TextField();
	private PasswordField passwordTf = new PasswordField();
	private PasswordField ponovljenaPassTf = new PasswordField();
	private Button registrujSeBtn = new Button("Registracija");
	private Button nazadBtn = new Button("Nazad");
	private Label porukaLbl = new Label("");
	public RegisterView() {
		init();
		actions();
		keyActions();
	}
	private void init() {
		this.addRow(0, imeLbl, imeTf);
		this.addRow(1, prezimeLbl, prezimeTf);
		this.addRow(2, emailLbl, emailTf);
		this.addRow(3, passwordLbl, passwordTf);
		this.addRow(4, ppasswordLbl, ponovljenaPassTf);
		this.addRow(5, registrujSeBtn, nazadBtn);
		this.add(porukaLbl, 0, 6,2,1);
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
	}
	private void actions() {
		registrujSeBtn.setOnAction(e->{
			if(imeTf.getText().isEmpty() || prezimeTf.getText().isEmpty() || emailTf.getText().isEmpty() || passwordTf.getText().isEmpty() || ponovljenaPassTf.getText().isEmpty()) {
				porukaLbl.setText("Sva polja su obavezna");
				porukaLbl.setStyle("-fx-text-fill: #ff0000");
				return;
			}
			if(!emailTf.getText().matches("[a-z0-9]{3,}@([a-z0-9]{1,}\\.)+[a-z]{2,6}")) {
				porukaLbl.setText("Neispravan format e-maila");
				porukaLbl.setStyle("-fx-text-fill: #ff0000");
				return;
			}
			if(passwordTf.getText().length()<8) {
				porukaLbl.setText("Šifra mora imati više od 8 slova");
				porukaLbl.setStyle("-fx-text-fill: #ff0000");
				return;
			}
			if(!passwordTf.getText().equals(ponovljenaPassTf.getText())) {
				porukaLbl.setText("Šifre se ne poklapaju");
				porukaLbl.setStyle("-fx-text-fill: #ff0000");
				return;
			}
			for(Korisnik k : Database.getInstance().getKorisnici()) {
				if(k.getEmail().equals(emailTf.getText())) {
					porukaLbl.setText("E-mail je zauzet");
					porukaLbl.setStyle("-fx-text-fill: #ff0000");
					return;
				}
			}
			Database.getInstance().getKorisnici().add(new Korisnik(imeTf.getText(), prezimeTf.getText(), emailTf.getText(), passwordTf.getText()));
			try {
				FileOutputStream fileOut = new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+"/file.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				for(Korisnik k : Database.getInstance().getKorisnici()) {
					objectOut.writeObject(k);
				}
				objectOut.flush();
				objectOut.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Registracija uspješna");
			alert.show();
			Main.changeScene(new Scene(new StartView(),300,150));
		});
		nazadBtn.setOnAction(e->{
			Main.changeScene(new Scene(new StartView(),300,150));
		});
	}
	private void keyActions() {
		nazadBtn.setOnKeyPressed(e->{
			nazadBtn.fire();
		});
		registrujSeBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
		imeTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
		prezimeTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
		emailTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
		passwordTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
		ponovljenaPassTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				registrujSeBtn.fire();
			}
		});
	}
}
