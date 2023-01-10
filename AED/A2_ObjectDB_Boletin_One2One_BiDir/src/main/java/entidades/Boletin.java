package entidades;

import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Boletin {

	@Id
	@GeneratedValue
	private int id;
	private HashMap<String, Integer> calificaciones;
	// mappedBy identifica la referencia (implícita/no existente) del otro extremo
	@OneToOne
	private Persona alumno;
	
	public Boletin() {
		calificaciones = new HashMap<String, Integer>();
	}

	public Boletin(Persona alumno) {
		this();
		this.alumno = alumno;
	}

	public Boletin(HashMap<String, Integer> calificaciones) {
		this();
		calificaciones.putAll(calificaciones);
	}

	public void putCalificacion(String modulo, Integer calificacion) {
		calificaciones.put(modulo, calificacion);
	}

	public void setCalificacion(String modulo, Integer calificacion) {
		if (!calificaciones.containsKey(modulo))
			throw new RuntimeException("Error al calificar módulo desconocido.");
		calificaciones.put(modulo, calificacion);
	}

	public void removeCalificacion(String modulo) {
		this.calificaciones.remove(modulo);
	}

	public int getId() {
		return id;
	}

	
	
	
	public void setAlumno(Persona p) {
		alumno=p;
	}
	public Persona getAlumno() {
		return alumno;
	}

	@Override
	public String toString() {
		return  String.format("Boletin de %s [%d] %s", (alumno!=null)?alumno.getNombre():"<desconocido>" , id, calificaciones);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boletin other = (Boletin) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
