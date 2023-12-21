package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends BorderPane{
	private ToggleButton nalogBtn = new ToggleButton("INFORMACIJE O NALOGU");
	private ToggleButton odjaviBtn = new ToggleButton("ODJAVI SE");
	private ToggleButton dodajObavezuBtn = new ToggleButton("DODAJ OBAVEZU");
	private ToggleButton obavezeBtn = new ToggleButton("OBAVEZE");
	private ToggleGroup toggle = new ToggleGroup();
	private HBox hbox1 = new HBox();
	public MainView() {
		init();
		actions();
	}
	private void init() {
		this.setPadding(new Insets(10));
		toggle.getToggles().addAll(obavezeBtn, dodajObavezuBtn, nalogBtn, odjaviBtn);
		hbox1.getChildren().addAll(obavezeBtn, dodajObavezuBtn, nalogBtn , odjaviBtn);
		nalogBtn.fire();
		toggle.selectToggle(obavezeBtn);
		this.setTop(hbox1);
		this.setCenter(new ObavezaView());
	}
	private void actions() {
		nalogBtn.setOnAction(e->{
			this.setCenter(new InformacijeView());
		});
		obavezeBtn.setOnAction(e->{
			this.setCenter(new ObavezaView());
		});
		dodajObavezuBtn.setOnAction(e->{
			this.setCenter(new DodajObavezuView(this));
		});
		odjaviBtn.setOnAction(e->{
			Stage stage = new Stage();
			stage.setScene(new Scene(new OdjaviSeView(stage), 500, 100));
			stage.centerOnScreen();
			stage.showAndWait();
		});
	}
	public ToggleButton getObavezeBtn() {
		return obavezeBtn;
	}
	public void setObavezeBtn(ToggleButton obavezeBtn) {
		this.obavezeBtn = obavezeBtn;
	}
	public ToggleGroup getToggle() {
		return toggle;
	}
	public void setToggle(ToggleGroup toggle) {
		this.toggle = toggle;
	}
	
}
