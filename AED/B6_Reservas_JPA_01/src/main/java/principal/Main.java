package principal;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Habitacion;
import entidades.HabitacionPK;
import entidades.Hotel;

public class Main {
	static EntityManagerFactory emf;
	static EntityManager em;

	public static void main(String[] args) {
		System.out.println("----------");
		em = (emf = Persistence.createEntityManagerFactory("reservas")).createEntityManager();
		
		em.getTransaction().begin();
		nuevoHotel();
		em.getTransaction().commit();
		
//		Habitacion habit = em.find(Habitacion.class, new HabitacionPK("301", "H10"));
		Habitacion habit = em.find(Habitacion.class, new HabitacionPK("101", "Sol3"));
		Hotel hotel = habit.getHotel();
		System.out.println(habit);

		System.out.println(hotel);

		for (Habitacion h : hotel.getHabitaciones()) {
			System.out.println("  -> " + h);
		}

		em.close();
		emf.close();
	}

	private static void nuevoHotel() {
		// Nuevo hotel
		Hotel altaHotel = new Hotel();
		altaHotel.setCodHotel("x2");
		altaHotel.setLocalidad("Buena Vista");
		altaHotel.setNombre("Rio Grande II");

		// Nueva Habitacioen para ese hotel
		
//		Habitacion hab2 = new Habitacion(altaHotel.getCodHotel(), "888");
		Habitacion hab = new Habitacion();
		hab.setPreciodia(250);
		// Clave para la habitacion
		HabitacionPK key = new HabitacionPK();
		key.setCodHotel(altaHotel.getCodHotel());
		key.setNumHabitacion("888");
		hab.setId(key);

		// relacion bidireccional
		altaHotel.setHabitaciones(Arrays.asList(hab));
		hab.setHotel(altaHotel);

//		em.persist(altaHotel);
		em.merge(altaHotel);
	}
}
