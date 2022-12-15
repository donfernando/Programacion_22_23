package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Integer id;
	private String nombre;
	private Integer edad;

	public Persona() {
	}

	public Persona(String n, Integer e) {
		nombre = n;
		edad = e;
	}

	@Override
	public String toString() {
		return String.format("[%d]Me llamo %s y tengo %d años.\n", id, nombre, edad);
	}

	public void setNombre(String nuevoNombre) {
			nombre = nuevoNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int nuevaEdad) {
		if (nuevaEdad >= 0 && nuevaEdad < 120)
			edad = nuevaEdad;
	}

	public void cumpleAnio() {
		edad++;
	}
}
