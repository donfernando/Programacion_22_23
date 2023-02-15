package entidades;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Copia_Fisica extends Copia {
	public static final boolean DETERIORADO = true;
	public static final boolean COMO_NUEVO = false;

	private boolean deteriorado = COMO_NUEVO;

	Copia_Fisica() {
	}

	Copia_Fisica(String ed) {
		this(ed, COMO_NUEVO);
	}

	Copia_Fisica(String ed, boolean deteriorado) {
		this(ed, null, deteriorado);
	}

	Copia_Fisica(String ed, Date publicado, boolean deteriorado) {
		super(ed, publicado);
		this.deteriorado = deteriorado;
	}

	// Getters y Setters
	public boolean getDeteriorado() {
		return deteriorado;
	}

	public Copia_Fisica setEstado(boolean deteriorado) {
		this.deteriorado = deteriorado;
		return this;
	}

	@Override
	public String toString() {
		return String.format("%s estado: %s.", super.toString(), (deteriorado ? "deteriorado" : "como nuevo"));
	}

}
