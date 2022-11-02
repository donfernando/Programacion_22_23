package aed.mostrardatos;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller implements Initializable {

	// model

	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty fechaI = new SimpleStringProperty();
	private StringProperty fechaF = new SimpleStringProperty();
	private BooleanProperty cantBefore = new SimpleBooleanProperty(true);
	private BooleanProperty cantAfter = new SimpleBooleanProperty(true);

	private String codHotel;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet resultado;

	// view
	@FXML
	private Button anteriorButton;

	@FXML
	private TextField fechafinText;

	@FXML
	private TextField fechainicioText;

	@FXML
	private ComboBox<String> hotelesCombo;

	@FXML
	private TextField nombreText;

	@FXML
	private Button posteriorButton;

	@FXML
	private GridPane view;

	public Controller() throws IOException, ClassNotFoundException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {

		// bindings

		nombreText.textProperty().bindBidirectional(nombre);
		fechainicioText.textProperty().bind(fechaI);
		fechafinText.textProperty().bind(fechaF);

		posteriorButton.disableProperty().bind(cantAfter);
		anteriorButton.disableProperty().bind(cantBefore);

		// load data

		try {
			Properties prop = new Properties();

			prop.load(getClass().getResourceAsStream("/sql/configuracion.props"));
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password", "");

			Class.forName(prop.getProperty("driver", "com.mysql.cj.jdbc.Driver"));

			conn = DriverManager.getConnection(url, username, password);

			try {
				stmt = conn.createStatement();

				// consultas sql
				resultado = stmt.executeQuery("select distinct codHotel from Estancias");

				while (resultado.next()) {
					hotelesCombo.getItems().add(resultado.getString("codHotel"));
				}

			} catch (SQLException e) {
			}

		} catch (Exception exception) {
		}

	}

	@FXML
	void onAnteriorAction(ActionEvent event) {
		try {
			if (!(resultado.isBeforeFirst() || resultado.isAfterLast())){
				resultado.updateString("nombre", nombre.get());
				resultado.updateRow();
			}
			if (resultado.previous()) {
				resultado.refreshRow();
				nombre.set(resultado.getString("nombre"));
				fechaI.set(resultado.getDate("fechaInicio").toString());
				fechaF.set(resultado.getDate("fechaFin").toString());
				setDisable();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onPosteriorAction(ActionEvent event) throws SQLException {
		try {
			if (!(resultado.isBeforeFirst() || resultado.isAfterLast())){
				resultado.updateString("nombre", nombre.get());
				resultado.updateRow();
			}
			if (resultado.next()) {
				resultado.refreshRow();
				nombre.set(resultado.getString("nombre"));
				fechaI.set(resultado.getDate("fechaInicio").toString());
				fechaF.set(resultado.getDate("fechaFin").toString());
				setDisable();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onChangeAction(ActionEvent event) throws SQLException {
		codHotel = hotelesCombo.getSelectionModel().selectedItemProperty().getValue();
		pstmt = conn.prepareStatement("select distinct * from Estancias where codHotel = ?",
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

//		En MySQL, esto no funciona correctamente (bug reportado) 
//    	pstmt.setFetchSize(1);

		pstmt.setString(1, codHotel);
		nombre.set("");
		fechaI.set("");
		fechaF.set("");
		resultado = pstmt.executeQuery();
		setDisable();
	}

	private void setDisable() throws SQLException {
		if (resultado.isLast() || resultado.isAfterLast())
			cantAfter.set(true);
		else
			cantAfter.set(false);

		if (resultado.isFirst() || resultado.isBeforeFirst())
			cantBefore.set(true);
		else
			cantBefore.set(false);

	}

	public GridPane getView() {
		return view;
	}

	public void onCloseRequest(Stage stage) {
		stage.setOnCloseRequest(e -> {

			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (pstmt != null)
					pstmt.close();
				if (resultado != null)
					resultado.close();
				System.out.println("Cerrando conexiones...");
			} catch (SQLException e1) {
			}

		});

	}

}
