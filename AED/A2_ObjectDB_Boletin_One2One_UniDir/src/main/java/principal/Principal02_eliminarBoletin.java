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

public class Principal02_eliminarBoletin {
	public static void main(String[] args) {
		Boletin boletin;
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("boletinesDeNotas");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Boletin> queryBoletines = em.createQuery("SELECT b FROM Boletin b", Boletin.class);
		List<Boletin> boletines = queryBoletines.getResultList();

		boletin = boletines.get(0);
		em.getTransaction().begin();
		// Elimina el boletin y deja la BD inestable al dejar a una persona sin boletin
		em.remove(boletin);
		em.getTransaction().commit();

		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> results = query.getResultList();
		for (Persona p : results) {
			System.out.println(p);
		}

		em.close();
		emf.close();
	}
}