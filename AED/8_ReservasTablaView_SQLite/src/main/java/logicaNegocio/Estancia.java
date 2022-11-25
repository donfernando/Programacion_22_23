package logicaNegocio;

public class Estancia {
	private int id;
	private String nombre;
	private String fechaInicio;
	private String fechaFin;
	private String numHabitacion;

	public Estancia(String nombre, String fInicio, String fFin, String numHabitacion) {
		this(-1 , nombre, fInicio, fFin, numHabitacion);
	}

	public Estancia(int id, String nombre, String fInicio, String fFin, String numHabitacion) {
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fInicio;
		this.fechaFin = fFin;
		this.numHabitacion = numHabitacion;
	}

	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		if(this.id==-1 && id != null)
			this.id = id;
		else
			throw new RuntimeException();
	}

	public String getNombre() {
		return nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public String getNumHabitacion() {
		return numHabitacion;
	}
	
	@Override
	public String toString() {
		return "Estancia (" + nombre + ", Entrada=" + fechaInicio + ", Salida=" + fechaFin + ", HAB: " + numHabitacion
				+ ")";
	}

}
