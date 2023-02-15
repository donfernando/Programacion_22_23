package entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Copia_Digital extends Copia {

	private int tamaño;

	// Constructores
	Copia_Digital() {
	}

	Copia_Digital(String ed) {
		super(ed);
	}

	Copia_Digital(String ed, int tam) {
		this(ed,null,tam);
	}


	Copia_Digital(String ed, Date publicado, int tam) {
		super(ed, publicado);
		tamaño = tam;
	}

	@Override
	public String toString() {
		return String.format("%s tam: %d Kb", super.toString(), tamaño);
	}
}
