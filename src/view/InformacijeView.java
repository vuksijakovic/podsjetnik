package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Database;

public class InformacijeView extends GridPane{
	private Label imeLbl = new Label("Ime");
	private TextField imeTf = new TextField();
	private Label prezimeLbl = new Label("Prezime");
	private TextField prezimeTf = new TextField();
	private Label emailLbl = new Label("Email");
	private TextField emailTf = new TextField();
	private Button sacuvajBtn = new Button("Sačuvaj promjene");
	private Button novaLozinkaBtn = new Button("Nova lozinka");
	private Button izbrisiNalogBtn = new Button("  Izbriši nalog  ");
	public InformacijeView() {
		init();
		actions();
		keyActions();
	}
	private void actions() {
		izbrisiNalogBtn.setOnAction(e->{
			Stage stage = new Stage();
			stage.setScene(new Scene(new IzbrisiNalogView(stage), 500, 100));
			stage.centerOnScreen();
			stage.showAndWait();
		});
		sacuvajBtn.setOnAction(e->{
			Stage stage = new Stage();
			stage.setScene(new Scene(new PromjeneView(this, stage), 500, 100));
			stage.centerOnScreen();
			stage.showAndWait();
		});
		novaLozinkaBtn.setOnAction(e->{
			Stage stage = new Stage();
			stage.setScene(new Scene(new NovaLozinkaView(stage), 500, 100));
			stage.centerOnScreen();
			stage.showAndWait();
		});
	}
	private void keyActions() {
		izbrisiNalogBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER) ) {
				izbrisiNalogBtn.fire();
			}
		});
		novaLozinkaBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				novaLozinkaBtn.fire();
			}
		});
		sacuvajBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				sacuvajBtn.fire();
			}
		});
	}
	private void init() {
		imeTf.setText(Database.getInstance().getTrenutniKorisnik().getIme());
		prezimeTf.setText(Database.getInstance().getTrenutniKorisnik().getPrezime());
		emailTf.setText(Database.getInstance().getTrenutniKorisnik().getEmail());
		emailTf.setFont(new Font(15));
		imeTf.setFont(new Font(15));
		prezimeTf.setFont(new Font(15));
		imeLbl.setFont(new Font(20));
		prezimeLbl.setFont(new Font(20));
		emailLbl.setFont(new Font(20));
		HBox hbox1 = new HBox(10);
		this.addRow(0, imeLbl, imeTf);
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
		this.addRow(1, prezimeLbl, prezimeTf);
		this.addRow(2,  emailLbl, emailTf);
		hbox1.getChildren().addAll(novaLozinkaBtn, izbrisiNalogBtn);
		this.addRow(3,  sacuvajBtn, hbox1);
		this.setHgap(10);
		this.setVgap(10);
	}
	public TextField getImeTf() {
		return imeTf;
	}
	public TextField getPrezimeTf() {
		return prezimeTf;
	}
	public TextField getEmailTf() {
		return emailTf;
	}
}
