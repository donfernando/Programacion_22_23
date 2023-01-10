package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Integer idPersona;
	private String nombre;
	private Integer edad;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Boletin boletin;
	
	public Persona() {
		boletin = new Boletin(this);
	}

	public Persona(String n, Integer e) {
		this();
		nombre = n;
		edad = e;
	}

	
	public void setBoletin(Boletin b) {
		if(boletin != null) 
			boletin.setAlumno(null);
		boletin = b;
		b.setAlumno(this);
	}
	public Boletin removeBoletin() {
		Boletin b=boletin;
		if(boletin!=null) {
			boletin.setAlumno(null);
			boletin=null;
		}
		return b; 
	}
	
	
	
	
	@Override
	public String toString() {
		return String.format("[%d]Me llamo %s y tengo %d años.\n", idPersona, nombre, edad)+
				"Mis calificaciones son estas....\n"+((boletin!=null)?boletin:"<sin notas>");
	}
	
	public Integer getIdPersona() {
		return idPersona;
	}

	public void setNombre(String nuevoNombre) {
			nombre = nuevoNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setEdad(Integer nuevaEdad) {
		if (nuevaEdad >= 0 && nuevaEdad < 120)
			edad = nuevaEdad;
	}


	public int getEdad() {
		return edad;
	}

	public Boletin getBoletin() {
		return boletin;
	}

	public String getCalificaciones() {
		return boletin.toString();
	}

	public void putCalificacion(String modulo, Integer calificacion) {
		boletin.putCalificacion(modulo, calificacion);
	}
	public void removeCalificacion(String modulo) {
		boletin.removeCalificacion(modulo);
	}
}
