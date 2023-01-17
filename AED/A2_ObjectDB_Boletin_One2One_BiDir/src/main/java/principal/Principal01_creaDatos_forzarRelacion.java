package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Boletin;
import entidades.Persona;

public class Principal01_creaDatos_forzarRelacion {
	public static void main(String[] args) {
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("boletinesDeNotas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Persona").executeUpdate();
		em.createQuery("DELETE FROM Boletin").executeUpdate();

		Persona p1, p2;
		p1 = new Persona("Calixto", 90);
		p2 = new Persona("Fidela", 87);
		p1.putCalificacion("Computación", 9);
		p2.putCalificacion("Lógica Proposicional", 8);
		em.persist(p1);
		em.persist(p2);
		em.persist(p1.getBoletin());
		em.persist(p2.getBoletin());
		p1.setBoletin(p2.getBoletin());

		em.getTransaction().commit();

		List<Boletin> boletines = em.createQuery("SELECT b FROM Boletin b", Boletin.class).getResultList();
		for (Boletin b : boletines) {
			System.out.println(b);
			if (b.getAlumno() != null)
				System.out.println(b.getAlumno().getNombre());
			else
				System.out.println("<desconocido>");
		}

		System.out.println("\nPERSONAS");
		// Retrieve all the Point objects from the database:
		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> entidadesPersona = query.getResultList();
		for (Persona p : entidadesPersona) {
			System.out.println(p);
//			System.out.println(p.getNombre());
//			System.out.println(p.getCalificaciones());
		}

		em.close();
		emf.close();
	}
}