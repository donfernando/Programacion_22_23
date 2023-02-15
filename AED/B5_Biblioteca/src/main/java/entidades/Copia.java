package entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Copia {

	@Id
	@GeneratedValue
	private int id_copia;
	private String editorial;
	private Date año_publicacion;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_DNI"))
	private Persona prestatario;

	// Constructores
	Copia() {
	}

	Copia(String ed) {
		this(ed, null);
	}

	Copia(String ed, Date publ) {
		editorial = ed;
		this.año_publicacion = publ;
	}

	public int getId_copia() {
		return id_copia;
	}

	public String getEditorial() {
		return editorial;
	}

	public Copia setEditorial(String editorial) {
		this.editorial = editorial;
		return this;
	}

	public Date getAño_publicacion() {
		return año_publicacion;
	}

	public Copia setAño_publicacion(Date año_publicacion) {
		this.año_publicacion = año_publicacion;
		return this;
	}

	// Getters y Setters de Relaciones
	public Persona getPrestatario() {
		return prestatario;
	}

	public Copia addPrestatario(Persona pres) {
		if (this.prestatario != null)
			throw new RuntimeException("Esta copia ya está prestada");
		this.prestatario = pres;
		pres.getLibros().add(this);
		return this;
	}

	public Copia removePrestatario() {
		if (this.prestatario == null)
			throw new RuntimeException("Esta copia no está prestada");
		this.prestatario.getLibros().remove(this);
		prestatario = null;
		return this;
	}

	// toString y equals
	@Override
	public String toString() {
		String fecha = "";
		if(año_publicacion!=null)
			fecha = String.format("(Publicado: %td-%tb-%tY) ",año_publicacion,año_publicacion,año_publicacion);
		String s = String.format("%sCopia [%d]", fecha, id_copia);
		s = s + (prestatario != null
				? " / prestado a " + prestatario.getApellidos() + " " + prestatario.getNombre() + " ["
						+ prestatario.getDni() + "]"
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
