package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EliminaCopiaLibro {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.createQuery("DELETE FROM Copia c WHERE c.id_copia = :p").setParameter("p", 159).executeUpdate();
		em.getTransaction().commit();

		Datos.mostrar(em);
		em.close();
		emf.close();
	}
}
