package principal;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.*;

public class EliminaUnaCopiaDeAlgunos_v2 {
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		
		// Cuál es el número de copias del libro/s con más copias
		long maxCopias = em
				.createQuery(
						"SELECT COUNT(c.id_copia) as rep FROM"
								+ " Copia c JOIN Libro l ON c MEMBER OF l.copias GROUP BY l.isbn ORDER BY rep desc",
						Long.class)
				.setMaxResults(1).getSingleResult();
		Logger.getGlobal().info("El Libro que más copias tiene, tiene " + maxCopias + " copias.");

		// Qué libro/s tiene el mayor número de copias.
		List<Libro> librosElegidos = em.createQuery("SELECT l FROM"
				+ " Copia c JOIN Libro l ON c MEMBER OF l.copias  GROUP BY l.isbn HAVING :copias = COUNT(c.id_copia)",
				Libro.class).setParameter("copias", maxCopias).getResultList();

		System.out.println("--------------------");
		em.getTransaction().begin();
		if (maxCopias > 1)
			for (Libro libro : librosElegidos) {
				Copia cop = libro.getCopias().get(0);
				System.out.printf("Eliminando la copia [%d] del libro %s\n", cop.getId_copia(), libro.getTitulo());
				libro.removeCopia(cop);
				if (cop.getPrestatario() != null)
					cop.removePrestatario();
				em.remove(cop);
			}
		System.out.println("--------------------");

		em.getTransaction().commit();

		Datos.mostrar(em);
		em.close();
		emf.close();
	}
}