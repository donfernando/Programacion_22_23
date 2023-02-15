package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.*;

public class EliminaUnaCopiaDeAlgunos {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		List<Object[]> pares = em.createQuery("SELECT l, COUNT(c.id_copia) as repet FROM"
												+ " Copia c JOIN Libro l ON c MEMBER OF l.copias GROUP BY l.isbn").getResultList();
		List<Libro> libros;

//		for (Object[] par : pares) {
//			System.out.printf("Libro %s : %d\n",((Libro)par[0]).getTitulo(),par[1]);
//		}

		// Máximo
		final int COPIAS = 1;
		final int LIBRO = 0;
		long max = -1;
		for (Object[] par : pares) {
			long nCopias = (long) par[COPIAS];
			if (nCopias > max)
				max = nCopias;
		}

		System.out.println("--------------------");
		em.getTransaction().begin();
		if (max > 1)
			for (Object[] par : pares) {
				long nCopias = (long) par[COPIAS];
				if (nCopias == max) {
					Libro lib = (Libro) par[LIBRO];
					Copia cop = lib.getCopias().get(0);
					System.out.printf("Eliminando la copia [%d] del libro %s\n", cop.getId_copia(), lib.getTitulo());
					lib.removeCopia(cop);
					if(cop.getPrestatario()!=null)
						cop.removePrestatario();
					em.remove(cop);
				}
			}
		System.out.println("--------------------");

		em.getTransaction().commit();

		Datos.mostrar(em);
		em.close();
		emf.close();
	}
}