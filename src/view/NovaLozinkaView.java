package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Korisnik;
import utils.Database;

public class NovaLozinkaView extends GridPane{
	private Label novaLozinka = new Label("Nova lozinka");
	private Label ponovljenaLozinka = new Label("Ponovljena lozinka");
	private PasswordField novaLozinkaTf = new PasswordField();
	private PasswordField ponovljenaLozinkaTf = new PasswordField();
	private Button sacuvajBtn = new Button("Sačuvaj lozinku");
	private Button ponistiBtn = new Button("Poništi");
	private Stage stage = new Stage();
	public NovaLozinkaView(Stage stage) {
		this.stage= stage;
		this.addRow(0, novaLozinka, novaLozinkaTf);
		this.addRow(1,ponovljenaLozinka, ponovljenaLozinkaTf);
		this.addRow(2,sacuvajBtn, ponistiBtn);
		this.setHgap(5);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		actions();
	}
	private void actions() {
		ponistiBtn.setOnAction(e->{
			stage.close();
		});
		sacuvajBtn.setOnAction(e->{
			if(novaLozinkaTf.getText().length()<8) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Nova lozinka mora imati više od 8 karaktera");
				alert.show();
				return;
			}
			if(!novaLozinkaTf.getText().equals(ponovljenaLozinkaTf.getText())) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Lozinke se ne poklapaju");
				alert.show();
				return;
			}
			Database.getInstance().getTrenutniKorisnik().setLozinka(novaLozinkaTf.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Lozinka uspješno sačuvana");
			alert.show();
			stage.close();
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
			
		});
		sacuvajBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				sacuvajBtn.fire();
			}
		});
		novaLozinkaTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				sacuvajBtn.fire();
			}
		});
		ponovljenaLozinkaTf.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				sacuvajBtn.fire();
			}
		});
		ponistiBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				ponistiBtn.fire();
			}
		});
	}
}
