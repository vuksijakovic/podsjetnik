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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Korisnik;
import model.Obaveza;
import utils.Database;

public class UkloniObavezuView extends VBox{
	private Label lbl = new Label("Da li ste sigurni?");
	private Button daBtn = new Button("DA");
	private Button neBtn = new Button("NE");
	private Stage stage;
	private ObavezaView ov;
	public UkloniObavezuView(Stage stage, ObavezaView ov) {
		this.stage= stage;
		this.ov=ov;
		HBox hbox1 = new HBox(5);
		hbox1.getChildren().addAll(daBtn, neBtn);
		hbox1.setAlignment(Pos.CENTER);
		this.getChildren().addAll(lbl, hbox1);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(15);
		actions();
	}
	private void actions() {
		daBtn.setOnAction(e->{
			for(Obaveza o : ov.getObavezeTv().getSelectionModel().getSelectedItems()) {
				Database.getInstance().getTrenutniKorisnik().getObaveze().remove(o);
			}
			ov.getObavezeTv().getItems().setAll(Database.getInstance().getTrenutniKorisnik().getObaveze());
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
		daBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				daBtn.fire();
			}
		});
		neBtn.setOnAction(e->{
			stage.close();
		});
		neBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				neBtn.fire();
			}
		});
	}
}
