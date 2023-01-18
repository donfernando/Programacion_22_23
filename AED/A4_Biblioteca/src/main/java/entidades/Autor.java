package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Autor {
	@Id
	@GeneratedValue
	private int id_autor;
	@Column(unique = true)
	private String nombre;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Libro> obras = new ArrayList<Libro>();
	
	// Constructores
	
	public Autor(String n) {
		nombre = n;
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public int getId_autor() {
		return id_autor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// Getters y Setters de Relaciones
	
	public List<Libro> getObras() {
		return obras;
	}

	// toString y equals
	@Override
	public String toString() {
		return "Autor [" + id_autor + "]: " + nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor otro = (Autor) obj;
		if (id_autor != otro.getId_autor())
			return false;
		return true;
	}
}
