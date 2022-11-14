package principal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.ControladorPrincipal;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private ControladorPrincipal controlador;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Consulta de Reservas");
    	controlador = new ControladorPrincipal();
        scene = new Scene(controlador.getView());
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    
    
    
}