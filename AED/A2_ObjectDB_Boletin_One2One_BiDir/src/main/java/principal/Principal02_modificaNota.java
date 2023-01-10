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

public class Principal02_modificaNota {
	public static void main(String[] args) {
		Boletin boletin;
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/personas.odb");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> results = query.getResultList();
		for (Persona p : results) {
			System.out.println(p);
		}

//		boletin = em.find(Boletin.class, 50);
		em.getTransaction().begin();
//		boletin.putCalificacion("PGV", 3);
//		boletin.setCalificacion("Programacion", 5);
		em.find(Persona.class, 46).setNombre("Juana");
		em.getTransaction().commit();

		for (Persona p : results) {
			System.out.println(p);
		}

		// Retrieve all the Point objects from the database:
		em.close();
		emf.close();
	}
}