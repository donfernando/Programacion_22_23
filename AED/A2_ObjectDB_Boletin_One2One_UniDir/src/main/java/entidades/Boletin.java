package entidades;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Boletin {

	@Id
	@GeneratedValue
	private int id;
	private HashMap<String, Integer> calificaciones;

	public Boletin() {
		calificaciones = new HashMap<String, Integer>();
	}

	public Boletin(HashMap<String, Integer> calificaciones) {
		this();
		calificaciones.putAll(calificaciones);
	}

	// Nuevas para facilitar la operativa
	public Set<String> getModulos() {
		return calificaciones.keySet();
	}
	public Integer getCalificacion(String modulo) {
		return calificaciones.get(modulo);
	}
	// **********************************
	
	
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

	@Override
	public String toString() {
		return "Boletin [" + id + "] " + calificaciones;
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
