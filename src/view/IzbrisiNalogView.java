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
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.Main;
import model.Korisnik;
import utils.Database;

public class IzbrisiNalogView extends VBox{
	private Label lbl = new Label("Da li ste sigurni da želite obrisati nalog?");
	private Button daBtn = new Button("DA");
	private Button neBtn = new Button("NE");
	private Stage stage;
	public IzbrisiNalogView(Stage stage) {
		this.stage = stage;
		actions();
		HBox hbox1 = new HBox(10);
		hbox1.getChildren().addAll(daBtn, neBtn);
		this.getChildren().addAll(lbl, hbox1);
		this.setPadding(new Insets(10));
		lbl.setFont(new Font(20));
		hbox1.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
	}
	private void actions() {
		daBtn.setOnAction(e->{
			Database.getInstance().getKorisnici().remove(Database.getInstance().getTrenutniKorisnik());
			stage.close();
			Main.changeScene(new Scene(new StartView(),300,150));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("NALOG USPJEŠNO IZBRISAN");
			alert.show();
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
		neBtn.setOnAction(e->{
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
