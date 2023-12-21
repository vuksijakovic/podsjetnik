package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.filechooser.FileSystemView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Korisnik;
import model.Obaveza;
import utils.Database;

public class DodajObavezuView extends GridPane{
	private Label obavezaLbl = new Label("Obaveza");
	private TextField obavezaTf = new TextField();
	private Label datumLbl = new Label("Datum");
	private ComboBox<Integer> mjesec = new ComboBox<>();
	private ComboBox<Integer> dan = new ComboBox<>();
	private ComboBox<Integer> godina = new ComboBox<>();
	private ComboBox<Integer> ranije = new ComboBox<>();
	private Label ranijeLbl = new Label("Koliko dana ranije da obavijestim");
	private Button dodajBtn = new Button("Dodaj");
	private MainView mv = new MainView();
	public DodajObavezuView(MainView mv) {
		this.mv = mv;
		for(int i=1; i<=31; i++) {
			Integer a = i;
			dan.getItems().add(a);
		}
		dan.setValue(1);
		for(int i=1; i<=12; i++) {
			Integer a = i;
			mjesec.getItems().add(a);
			ranije.getItems().add(a);
		}
		ranije.setValue(1);
		mjesec.setValue(1);
		for(int i=2022; i<=2099; i++) {
			Integer a = i;
			godina.getItems().add(a);
		}
		
		godina.setValue(2022);
		HBox hbox1 = new HBox(5);
		hbox1.getChildren().addAll(godina, mjesec, dan);
		this.addRow(0, obavezaLbl, obavezaTf);
		this.addRow(1, datumLbl, hbox1);
		this.addRow(2, ranijeLbl, ranije);
		HBox hbox2 = new HBox(5);
		HBox hbox3 = new HBox(5);
		hbox2.getChildren().addAll(dodajBtn);
		this.addRow(3, hbox3, hbox2);
		this.setVgap(10);
		this.setHgap(10);
		this.setAlignment(Pos.CENTER);
		actions();
	}
	private void actions() {
		dodajBtn.setOnAction(e->{
			if(obavezaTf.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("UNESITE NAZIV OBAVEZE");
				alert.show();
				return;
			}
			else {
				if(LocalDate.of(godina.getValue(), mjesec.getValue(), dan.getValue()).compareTo(LocalDate.now())>0) {
					Database.getInstance().getTrenutniKorisnik().getObaveze().add(new Obaveza(obavezaTf.getText(), LocalDate.of(godina.getValue(), mjesec.getValue(), dan.getValue()), ranije.getValue()));
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
					alert.setContentText("Obaveza dodata");
					alert.show();
					mv.getToggle().selectToggle(mv.getObavezeBtn());
					mv.getObavezeBtn().fire();
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("DATUM JE PROŠAO");
					alert.show();
					return;
				}
			}
		});
		dodajBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				dodajBtn.fire();
			}
		});
		mjesec.getSelectionModel().selectedItemProperty().addListener((p,o,n)->{
			if(n==1 || n==3 || n==5 || n==7 || n==8 || n==10 || n==12) {
				dan.getItems().clear();
				for(int i=1; i<=31; i++) {
					Integer a = i;
					dan.getItems().addAll(a);
				}
				dan.setValue(1);
			}
			else if(n==2) {
				dan.getItems().clear();
				for(int i=1; i<=28; i++) {
					Integer a = i;
					dan.getItems().addAll(a);
				}
				if((godina.getValue()%4==0 && godina.getValue()%100!=0) || godina.getValue()%400 ==0) {
					dan.getItems().add(29);
				}
				dan.setValue(1);
			}
			else {
				dan.getItems().clear();
				for(int i=1; i<=30; i++) {
					Integer a = i;
					dan.getItems().addAll(a);
				}
				dan.setValue(1);
			}
		});
	}
}
