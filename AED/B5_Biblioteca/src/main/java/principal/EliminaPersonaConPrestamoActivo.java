package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EliminaPersonaConPrestamoActivo {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.createQuery("DELETE FROM Persona p WHERE p.dni = :x").setParameter("x", "54063242V").executeUpdate();

		em.getTransaction().commit();

		Datos.mostrar(em);
		em.close();
		emf.close();
	}
}
