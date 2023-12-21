package main;

import java.time.LocalDate;

import javax.swing.filechooser.FileSystemView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.FileLoader;
import view.StartView;

public class Main extends Application{
	private static Stage primaryStage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		FileLoader.ucitajFajlove();
		primaryStage = arg0;
		primaryStage.setTitle("Podsjetnik by sijak0vic");
		primaryStage.setScene(new Scene(new StartView(),300,150));
		primaryStage.show();
	}
	public static void changeScene(Scene scena) {
		primaryStage.setScene(scena);
		primaryStage.centerOnScreen();
	}
}
