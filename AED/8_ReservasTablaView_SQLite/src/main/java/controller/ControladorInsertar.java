package controller;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.stage.Window;

public class ControladorInsertar extends ControladorDialogo {

	public ControladorInsertar(Window padre, ListProperty<String> habitaciones) {
		super(padre,null, habitaciones);
	}
	
	@FXML
	public void initialize() {
		super.initialize();
		stage.setTitle("Insertar datos de Nueva Estancia");// TODO
	}
	
}
