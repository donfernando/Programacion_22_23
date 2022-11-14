package model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import logicaNegocio.Estancia;

public class DatosEstancias {
	
	private ListProperty<String> hoteles = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty hotelSelecionado = new SimpleStringProperty();

	private StringProperty habitaciones = new SimpleStringProperty();
	private ListProperty<String> listaHabitaciones = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	private ListProperty<Estancia> estancias = new SimpleListProperty<>(FXCollections.observableArrayList());

	private IntegerProperty estanciaActual = new SimpleIntegerProperty();

	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty fechaInicio = new SimpleStringProperty();
	private StringProperty fechaFin = new SimpleStringProperty();


	public ListProperty<String> hotelesProperty() {
		return this.hoteles;
	}
	public StringProperty hotelSelecionadoProperty() {
		return this.hotelSelecionado;
	}
	public StringProperty habitacionesProperty() {
		return this.habitaciones;
	}
	public ListProperty<String> listaHabitacionesProperty() {
		return this.listaHabitaciones;
	}
	public ListProperty<Estancia> estanciasProperty() {
		return this.estancias;
	}	
	public IntegerProperty estanciaActualProperty() {
		return this.estanciaActual;
	}
	
	public StringProperty nombreProperty() {
		return nombre;
	}
	public StringProperty fechaInicioProperty() {
		return fechaInicio;
	}
	public StringProperty fechaFinProperty() {
		return fechaFin;
	}
	
	
}
