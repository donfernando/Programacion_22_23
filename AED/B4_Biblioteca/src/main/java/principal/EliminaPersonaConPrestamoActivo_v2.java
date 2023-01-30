package principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.*;

public class EliminaPersonaConPrestamoActivo_v2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p = em.createQuery("SELECT p FROM Persona p WHERE p.dni = :x", Persona.class)
				.setParameter("x", "54063242V").getSingleResult();
		for (Copia c : p.getLibros()) {
			c.removePrestatario();
		}  
		em.remove(p);
		em.getTransaction().commit();

		Datos.mostrar(em);
		em.close();
		emf.close();
	}
}