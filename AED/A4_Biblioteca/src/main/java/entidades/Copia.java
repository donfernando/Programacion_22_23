package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Copia {

	@Id
	@GeneratedValue
	private int id_copia;
	private boolean deteriorado;
	
	@ManyToOne
	private Persona prestatario;

	// Constructores
	public Copia() {
		deteriorado = false;
	}

	public Copia(boolean deteriorado) {
		this.deteriorado = deteriorado;
	}

	// Getters y Setters
	public boolean isDeteriorado() {
		return deteriorado;
	}

	public void setDeteriorado(boolean deteriorado) {
		this.deteriorado = deteriorado;
	}

	public int getId_copia() {
		return id_copia;
	}
	
	// Getters y Setters de Relaciones
	public Persona getOwner() {
		return prestatario;
	}

	public void setOwner(Persona pres) {
		this.prestatario = pres;
	}
	
	// toString y equals
	@Override
	public String toString() {
		String deterioro = "Copia [" + id_copia + "]. Deterioro ";
		if (!deteriorado)
			deterioro.concat("no ");
		deterioro.concat("detectado.");
		return deterioro;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copia other = (Copia) obj;
		if (id_copia != other.getId_copia())
			return false;
		return true;
	}
}
