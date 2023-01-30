package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Copia;
import entidades.Libro;
import entidades.Persona;

public class ListadoPersona_y_Prestamos {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		List<Persona> personas = em.createQuery("SELECT p FROM Persona p",Persona.class).getResultList();
		for (Persona persona : personas) {
			System.out.println(persona);
			for (Copia prest : persona.getLibros()) {
				Libro l = em.createQuery("SELECT FROM Libro l WHERE :miCopia MEMBER l.copias",Libro.class).setParameter("miCopia", prest).getSingleResult();
				System.out.println("   - [copia " + prest.getId_copia() + "] Titulo: "+l.getTitulo());
			}
		}
		em.close();
		emf.close();
	}
}
