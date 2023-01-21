package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Copia {

	public static final boolean DETERIORADO = true;
	public static final boolean COMO_NUEVO = false;
	
	@Id
	@GeneratedValue
	private int id_copia;
	private boolean deteriorado;

	@ManyToOne(cascade = {CascadeType.ALL})
	private Persona prestatario;

	// Constructores
	public Copia() {
		deteriorado = COMO_NUEVO;
	}

	public Copia(boolean deteriorado) {
		this.deteriorado = deteriorado;
	}

	// Getters y Setters
	public boolean getDeteriorado() {
		return deteriorado;
	}

	public void setDeteriorado(boolean deteriorado) {
		this.deteriorado = deteriorado;
	}

	public int getId_copia() {
		return id_copia;
	}

	// Getters y Setters de Relaciones
	public Persona getPrestatario() {
		return prestatario;
	}

	public void addPrestatario(Persona pres) {
		if (this.prestatario != null)
			throw new RuntimeException("Esta copia ya está prestada");
		this.prestatario = pres;
		pres.getLibros().add(this);
	}

	public void removePrestatario() {
		if (this.prestatario == null)
			throw new RuntimeException("Esta copia no está prestada");
		this.prestatario.getLibros().remove(this);
		prestatario = null;
	}

	// toString y equals
	@Override
	public String toString() {
		String s = String.format("Copia [%d]%s", id_copia, deteriorado ? " deteriorado" : " como nuevo");
		s = s + (prestatario != null?
				" / prestado a "+prestatario.getApellidos()+" "+prestatario.getNombre() +
				" [" + prestatario.getDni() + "]"
				: "");
		return s;
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
