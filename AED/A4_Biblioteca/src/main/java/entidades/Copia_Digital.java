package entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Copia_Digital {
	@Id
	private String nombre_fichero;
	private String formato;
	
	@OneToOne
	private Copia original;
	
	// Constructores
	Copia_Digital(String fichero) {
		this(fichero,"");
	}
	
	Copia_Digital(String fichero, String formato) {
		nombre_fichero = fichero;
		this.formato = formato;
	}

	// Getters y Setters
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getNombre_fichero() {
		return nombre_fichero;
	}
	
	// Getters y Setters de Relaciones
	public Copia getOriginal() {
		return original;
	}

	public void setOriginal(Copia num_copia) {
		this.original = num_copia;
	}
	
	// toString y equals
	@Override
	public String toString() {
		return "Copia digital: "+nombre_fichero+" / "+formato;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copia_Digital other = (Copia_Digital) obj;
		if (!nombre_fichero.equals(other.getNombre_fichero()))
			return false;
		return true;
	}
}
