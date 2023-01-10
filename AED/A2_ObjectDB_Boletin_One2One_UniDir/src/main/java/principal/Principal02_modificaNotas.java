package principal;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Boletin;
import entidades.Persona;

public class Principal02_modificaNotas {
	public static void main(String[] args) {
		Boletin boletin;
		Set<String> modulos;
		Integer nota;
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("boletinesDeNotas");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Boletin> queryBoletines = em.createQuery("SELECT b FROM Boletin b", Boletin.class);
		List<Boletin> boletines = queryBoletines.getResultList();
		em.getTransaction().begin();
		
		for (Boletin b : boletines) {
			modulos = b.getModulos();
			for (String m : modulos) {
				nota = b.getCalificacion(m)+1;
				if(nota>10)
					nota = 10;
				b.setCalificacion(m, nota);
			}
		}

		em.getTransaction().commit();

		TypedQuery<Persona> queryPersonas = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> personas = queryPersonas.getResultList();
		for (Persona p : personas) {
			System.out.println(p);
		}

		// Retrieve all the Point objects from the database:
		em.close();
		emf.close();
	}
}