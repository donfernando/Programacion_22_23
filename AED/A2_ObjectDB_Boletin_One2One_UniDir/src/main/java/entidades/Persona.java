package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Integer idPersona;
	private String nombre;
	private Integer edad;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Boletin calificaciones;
	
	public Persona() {
		calificaciones = new Boletin();
	}

	public Persona(String n, Integer e) {
		this();
		nombre = n;
		edad = e;
	}

	@Override
	public String toString() {
		return String.format("[%d]Me llamo %s y tengo %d años.\n", idPersona, nombre, edad)+
				"Mis calificaciones son estas....\n"+calificaciones;
	}
	
	public Integer getIdPersona() {
		return idPersona;
	}

	public void setNombre(String nuevoNombre) {
			nombre = nuevoNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setEdad(Integer nuevaEdad) {
		if (nuevaEdad >= 0 && nuevaEdad < 120)
			edad = nuevaEdad;
	}


	public int getEdad() {
		return edad;
	}

	// Cambiada para facilitar la operativa
	public Boletin getCalificaciones() {
		return calificaciones;
	}
	// ******************************
	
	public void putCalificacion(String modulo, Integer calificacion) {
		calificaciones.putCalificacion(modulo, calificacion);
	}
	public void removeCalificacion(String modulo) {
		calificaciones.removeCalificacion(modulo);
	}
	
}
