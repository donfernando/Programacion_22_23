package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import logicaNegocio.Estancia;

public class EstanciaProperty{
	private IntegerProperty id;
	private StringProperty nombre;
	private StringProperty fechaInicio;
	private StringProperty fechaFin;
	private StringProperty numHabitacion;

	public EstanciaProperty(Estancia estancia) {
		this.id = new SimpleIntegerProperty(estancia.getId());
		this.nombre = new SimpleStringProperty(estancia.getNombre());
		this.fechaInicio = new SimpleStringProperty(estancia.getFechaInicio());
		this.fechaFin = new SimpleStringProperty(estancia.getFechaFin());
		this.numHabitacion = new SimpleStringProperty(estancia.getNumHabitacion());
	}

	public Estancia get() {
		return new Estancia(getId(), getNombre(), getFechaInicio(), getFechaFin(), getNumHabitacion());
	}

	public int getId() {
		return id.get();
	}
	public void setId(Integer id) {
		if(this.id==null && id != null)
			this.id.set(id);
		else
			throw new RuntimeException();
	}

	public String getNombre() {
		return nombre.get();
	}

	public String getFechaInicio() {
		return fechaInicio.get();
	}

	public String getFechaFin() {
		return fechaFin.get();
	}

	public String getNumHabitacion() {
		return numHabitacion.get();
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
	public StringProperty numHabitacionProperty() {
		return numHabitacion;
	}
	public IntegerProperty idProperty() {
		return id;
	}
	
	
	@Override
	public String toString() {
		return "Estancia (" + nombre + ", Entrada=" + fechaInicio + ", Salida=" + fechaFin + ", HAB: " + numHabitacion
				+ ")";
	}

}
