package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Persona;

public class Principal02_modificaUnObjeto {
    public static void main(String[] args) {

    	// Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("db/personas.odb");
        EntityManager em = emf.createEntityManager();
 
 
        // Retrieve all the Point objects from the database:
        TypedQuery<Persona> query =
                em.createQuery("SELECT p FROM Persona p", Persona.class);
        List<Persona> results = query.getResultList();
        for (Persona p : results) {
            System.out.print(p);
        }

        em.getTransaction().begin();
        results.get(0).setNombre("Sonia");
        results.get(0).setEdad(28);
        em.getTransaction().commit();
        
//        System.out.println(results.get(0));
        
        for (Persona p : results) {
            System.out.print(p);
        }
        
        
        em.close();
        emf.close();
    }
}