package controller;

import java.io.IOException;

import javafx.beans.property.ListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import logicaNegocio.Estancia;
import model.EstanciaProperty;

public class ControladorDialogo {

	// modelo
	// ------
	protected boolean confirmarAccion;
	protected EstanciaProperty datosProp;
	protected ListProperty<String> habitaciones;
	// view
	// ----
	protected Stage stage;
	protected Window padre;

	@FXML
	protected VBox view;
	@FXML
	protected TextField tfNombre, tfFechaInicio, tfFechaFin;
	@FXML
	protected  ComboBox<String> cbHab;
	
	@FXML
	protected Label lbMensaje;

	public Parent getView() {
		return view;
	}

	public String getTfNombre() {
		return tfNombre.textProperty().get();
	}

	public String getTfFechaInicio() {
		return tfFechaInicio.textProperty().get();
	}

	public String getTfFechaFin() {
		return tfFechaFin.textProperty().get();
	}

	public String getCbHab() {
		return cbHab.getSelectionModel().getSelectedItem();
	}
	
	public Estancia getEstancia(){
		return datosProp.get();
	}

	private final static String TITULO = "Formulario de Datos";


	public ControladorDialogo(Window padre, Estancia datos, ListProperty<String> habitaciones ) {
		if(datos == null)
			datos = new Estancia("", "", "", "");
		this.datosProp = new EstanciaProperty(datos);
		this.habitaciones = habitaciones;
		stage = new Stage();
		this.padre = padre;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/dialogoFormulario.fxml"));
			loader.setController(this);
			Parent root = loader.load(); // Antes de terminar invoca a Initializer 
			Scene scene = new Scene(root);
			stage.setTitle(TITULO);
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(padre);
			stage.showAndWait();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		stage.setResizable(false);
		tfNombre.textProperty().bindBidirectional(datosProp.nombreProperty());
		tfFechaInicio.textProperty().bindBidirectional(datosProp.fechaInicioProperty());
		tfFechaFin.textProperty().bindBidirectional(datosProp.fechaFinProperty());
		
		cbHab.itemsProperty().bind(habitaciones);
		cbHab.getSelectionModel().select(datosProp.numHabitacionProperty().get());
		datosProp.numHabitacionProperty().bind(cbHab.getSelectionModel().selectedItemProperty());
	}
	

	public boolean seConfirma() {
		return confirmarAccion;
	}

	@FXML
	private void onAceptar(ActionEvent e) {
		if (formularioCompletado()) {
			confirmarAccion = true;
			stage.close();
		} else {
			lbMensaje.setVisible(true);
		}
		
	}

	private boolean formularioCompletado() {
		return !tfNombre.getText().isEmpty() && !tfFechaInicio.getText().isEmpty() && !tfFechaFin.getText().isEmpty()
				&& !cbHab.getSelectionModel().isEmpty();
	}

	@FXML
	private void onCancelar(ActionEvent e) {
		confirmarAccion = false;
		stage.close();
	}
}
