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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Korisnik;
import utils.Database;

public class PromjeneView extends VBox{
	private Label lbl = new Label("Da li ste sigurni da želite sačuvati promjene?");
	private Button daBtn = new Button("DA");
	private Button neBtn = new Button("NE");
	private InformacijeView iv;
	private Stage stage;
	public PromjeneView(InformacijeView iv, Stage stage) {
		this.iv = iv;
		this.stage = stage;
		HBox hbox1 = new HBox(10);
		hbox1.getChildren().addAll(daBtn, neBtn);
		this.getChildren().addAll(lbl, hbox1);
		this.setPadding(new Insets(10));
		lbl.setFont(new Font(20));
		hbox1.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
		actions();
	}
	private void actions() {
		neBtn.setOnAction(e->{
			stage.close();
		});
		daBtn.setOnAction(e->{
			if(iv.getEmailTf().getText().matches("[a-z0-9]{3,}@([a-z0-9]{1,}\\.)+[a-z]{2,6}")) {
				Database.getInstance().getTrenutniKorisnik().setIme(iv.getImeTf().getText());
				Database.getInstance().getTrenutniKorisnik().setPrezime(iv.getPrezimeTf().getText());
				Database.getInstance().getTrenutniKorisnik().setEmail(iv.getEmailTf().getText());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Informacije su uspješno sačuvane");
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
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Pogrešan format email-a");
				alert.show();
			}
			stage.close();
		});
		daBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				daBtn.fire();
			}
		});
		neBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				neBtn.fire();
			}
		});
	}
}
