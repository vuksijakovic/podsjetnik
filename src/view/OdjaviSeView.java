package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;

public class OdjaviSeView extends VBox{
		private Label lbl = new Label("Da li ste sigurni?");
		private Button daBtn = new Button("DA");
		private Button neBtn = new Button("NE");
		private Stage stage;
		public OdjaviSeView(Stage stage) {
			this.stage= stage;
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
				Main.changeScene(new Scene(new StartView(),300,150));
				stage.close();
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
