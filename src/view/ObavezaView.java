package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Obaveza;
import utils.Database;

public class ObavezaView extends VBox{
	private TableView<Obaveza> obavezeTv = new TableView<>();
	private TableColumn<Obaveza, String> obavezaTc = new TableColumn<>("Obaveza");
	private TableColumn<Obaveza, String> datumTc = new TableColumn<>("Datum");
	private TableColumn<Obaveza, String> danaTc = new TableColumn<>("Dana do");
	private ListView<Obaveza> istekleObaveze = new ListView<>();
	private Button ukloniObavezuBtn = new Button("Ukloni obaveze");
	private Label istekleLbl = new Label("Istekle obaveze");
	private HBox hbox1 = new HBox(5);
	public ObavezaView() {
		init();
		actions();
	}
	@SuppressWarnings("unchecked")
	private void init() {
		obavezaTc.setCellValueFactory(new PropertyValueFactory<>("ime"));
		datumTc.setCellValueFactory(new PropertyValueFactory<>("datum1"));
		danaTc.setCellValueFactory(new PropertyValueFactory<>("danaDo"));
		obavezeTv.getColumns().addAll(obavezaTc, datumTc, danaTc);
		hbox1.getChildren().add(ukloniObavezuBtn);
		obavezeTv.getItems().setAll(Database.getInstance().getTrenutniKorisnik().getObaveze());
		obavezeTv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		hbox1.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		this.getChildren().addAll(obavezeTv, hbox1, istekleLbl, istekleObaveze);
	}
	private void actions() {
		ukloniObavezuBtn.setOnAction(e->{
			Stage stage = new Stage();
			stage.setScene(new Scene(new UkloniObavezuView(stage, this), 500, 100));
			stage.centerOnScreen();
			stage.show();
		});
		ukloniObavezuBtn.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				ukloniObavezuBtn.fire();
			}
		});
	}
	public TableView<Obaveza> getObavezeTv() {
		return obavezeTv;
	}
	public void setObavezeTv(TableView<Obaveza> obavezeTv) {
		this.obavezeTv = obavezeTv;
	}
	
}
