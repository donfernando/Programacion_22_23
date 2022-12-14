package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Persona;

public class Principal01_creaDatos {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Desea eliminar los datos de anteriores ocasiones (S/N)? ");
    	boolean mantenerDatos = Character.toLowerCase(sc.nextLine().trim().charAt(0))=='n';

    	// Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("db/personas.odb");
        EntityManager em = emf.createEntityManager();
 
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();

        if(!mantenerDatos)
        	em.createQuery("DELETE FROM Persona").executeUpdate();
        
        for (int i = 1; i <= 5; i++) {
           Integer e = (int)(Math.random()*30)+10; // [10,39]
           String n = String.format("Persona %d",i);
            Persona p = new Persona(n,e);
            em.persist(p);
        }
        em.getTransaction().commit();
 
        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Persona p WHERE p.edad>=18");
        System.out.println("Total mayores de edad: " + q1.getSingleResult());
       
        Query q2 = em.createQuery("SELECT COUNT(p) FROM Persona p WHERE p.edad<18");
        System.out.println("Total menores de edad: " + q2.getSingleResult());
 
        // Find the average X value:
        Query q3 = em.createQuery("SELECT AVG(p.edad) FROM Persona p");
        System.out.println("Edad promedio: " + q3.getSingleResult());
 
        // Retrieve all the Point objects from the database:
        TypedQuery<Persona> query =
                em.createQuery("SELECT p FROM Persona p", Persona.class);
        List<Persona> results = query.getResultList();
        for (Persona p : results) {
            System.out.println(p);
        }
        em.close();
        emf.close();
    }
}