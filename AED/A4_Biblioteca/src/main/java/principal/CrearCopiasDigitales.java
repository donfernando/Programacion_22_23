package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Copia;
import entidades.Copia_Digital;
import entidades.Libro;

public class CrearCopiasDigitales {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		em.createQuery("DELETE FROM Copia_Digital").executeUpdate();
		
		List<Libro> libros = em.createQuery("SELECT FROM Libro",Libro.class).getResultList();
		for (Libro libro : libros) {
			String tit = libro.getTitulo().replace(' ', '_');
			Copia c = libro.getCopias().get(0);
			em.persist(c.crearCopiaDigital(tit+".pdf", "PDF"));
		}
		em.getTransaction().commit();
		
		List<Copia_Digital> copiasDigitales = em.createQuery("SELECT FROM Copia_Digital",Copia_Digital.class).getResultList();
		for (Copia_Digital cd : copiasDigitales) {
			System.out.println(".-> "+cd);
		}
		
		
		em.close();
		emf.close();
	}
}