package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Habitaciones database table.
 * 
 */
@Entity
@Table(name="Habitaciones")
@NamedQuery(name="Habitacion.findAll", query="SELECT h FROM Habitacion h")
public class Habitacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HabitacionPK id;

	private int preciodia;

	//bi-directional many-to-one association to Estancia
	@OneToMany(mappedBy="habitacion")
	private List<Estancia> estancias;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="codHotel")
	private Hotel hotel;

	public Habitacion() {
	}

	public HabitacionPK getId() {
		return this.id;
	}

	public void setId(HabitacionPK id) {
		this.id = id;
	}

	public int getPreciodia() {
		return this.preciodia;
	}

	public void setPreciodia(int preciodia) {
		this.preciodia = preciodia;
	}

	public List<Estancia> getEstancias() {
		return this.estancias;
	}

	public void setEstancias(List<Estancia> estancias) {
		this.estancias = estancias;
	}

	public Estancia addEstancia(Estancia estancia) {
		getEstancias().add(estancia);
		estancia.setHabitacion(this);

		return estancia;
	}

	public Estancia removeEstancia(Estancia estancia) {
		getEstancias().remove(estancia);
		estancia.setHabitacion(null);

		return estancia;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}