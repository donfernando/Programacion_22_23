package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Persona {
	@Id
	private String dni;
	private String nombre, apellidos, direccion, telefono, email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "prestatario")
	private List<Copia> librosPrestados = new ArrayList<Copia>();
	
	// Constructores
	public Persona(String DNI) {
		this(DNI,"","","","","");
	}
	
	public Persona(String DNI, String nom, String apel, String dir, String tel, String mail) {
		dni = DNI;
		nombre = nom;
		apellidos = apel;
		direccion = dir;
		telefono = tel;
		email = mail;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	
	// Getters y Setters de Relaciones
	public List<Copia> getLibros() {
		return librosPrestados;
	}
		
	// toString y equals
	@Override
	public String toString() {
		return nombre +" " + apellidos + ", DNI " + dni;
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
		if (!dni.equals(other.getDni()))
			return false;
		return true;
	}
}