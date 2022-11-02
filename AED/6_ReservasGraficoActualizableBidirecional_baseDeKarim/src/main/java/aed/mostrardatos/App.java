package aed.mostrardatos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private Controller controller;
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new Controller();
		
		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("Mostrar datos");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
		
//		controller.buildOnCloseRequest(primaryStage);
		
	}
	
	@Override
	public void stop() throws Exception {
		controller.onCloseRequest(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
