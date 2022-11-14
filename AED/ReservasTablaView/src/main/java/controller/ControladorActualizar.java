package controller;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.stage.Window;
import logicaNegocio.Estancia;

public class ControladorActualizar extends ControladorDialogo {
	public ControladorActualizar(Window padre, Estancia actual, ListProperty<String> habitaciones) {
		super(padre, actual, habitaciones);
	}

	@FXML
	public void initialize() {
		super.initialize();
		stage.setTitle("Formulario de Modificación de Estancias");// TODO
	}

}