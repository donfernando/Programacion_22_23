package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Integer id;
	private String nombre;
	private Integer edad;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Libro> librosEnPrestamo = new ArrayList<Libro>();
	
	public Persona() {
	}

	public Persona(Integer id) {
		this.id = id;
	}

	public Persona(String n, Integer e) {
		nombre = n;
		edad = e;
	}
	public Persona(Integer id, String n, Integer e) {
		this.id = id;		
		nombre = n;
		edad = e;
	}

	@Override
	public String toString() {
		String aux;
		aux = String.format("[%d]Me llamo %s y tengo %d años.\n", id, nombre, edad);
		if(librosEnPrestamo.size()!=0) {
//			aux += "Libros en prestamo = "+librosEnPrestamo.size()+"\n";
			aux += "  Libros en prestamo\n";
			for (Libro libro : librosEnPrestamo) {
				aux += "  - "+libro+"\n";
			}
		}
		return aux;
	}

	
	public List<Libro> getLibrosEnPrestamo() {
		return librosEnPrestamo;
	}

	public void addLibrosEnPrestamo(Libro libro) {
		librosEnPrestamo.add(libro);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nuevoNombre) {
		if (nombre == null)
			nombre = nuevoNombre.toUpperCase();
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer nuevaEdad) {
		if (nuevaEdad >= 0 && nuevaEdad < 120)
			edad = nuevaEdad;
	}

	public void cumpleAnio() {
		edad++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Persona other = (Persona) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
