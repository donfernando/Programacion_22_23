package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Persona;

public class Principal_eliminaPrimerDatos {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	List<Persona> personas;
    	// Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("db/personas.odb");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
        personas = query.getResultList(); 
        for (Persona persona : personas) {
			System.out.println(" * "+persona);
		}
        		
        em.getTransaction().begin();
            em.remove(personas.get(0));
        em.getTransaction().commit();
 
        for (Persona p : personas) {
            System.out.println(" - "+p);
        }
        em.close();
        emf.close();
    }
}