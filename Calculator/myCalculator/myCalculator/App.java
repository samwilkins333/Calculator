package myCalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer(this, stage);
		Scene scene = new Scene(organizer.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
		stage.setScene(scene); 
		stage.setResizable(false);
		stage.setTitle("My Calculator");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}