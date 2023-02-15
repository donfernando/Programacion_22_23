package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Habitaciones database table.
 * 
 */
@Embeddable
public class HabitacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String numHabitacion;

	private String codHotel;

	public HabitacionPK() {
	}
	public HabitacionPK(String numHabitacion, String codHotel) {
		this.numHabitacion = numHabitacion;
		this.codHotel = codHotel;
	}
	public String getNumHabitacion() {
		return this.numHabitacion;
	}
	public void setNumHabitacion(String numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	public String getCodHotel() {
		return this.codHotel;
	}
	public void setCodHotel(String codHotel) {
		this.codHotel = codHotel;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HabitacionPK)) {
			return false;
		}
		HabitacionPK castOther = (HabitacionPK)other;
		return 
			this.numHabitacion.equals(castOther.numHabitacion)
			&& this.codHotel.equals(castOther.codHotel);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numHabitacion.hashCode();
		hash = hash * prime + this.codHotel.hashCode();
		
		return hash;
	}
}