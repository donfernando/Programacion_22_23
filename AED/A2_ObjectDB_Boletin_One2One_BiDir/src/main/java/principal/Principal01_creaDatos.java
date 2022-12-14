package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Boletin;
import entidades.Persona;

public class Principal01_creaDatos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Desea eliminar los datos de anteriores ocasiones (S/N)? ");
		boolean mantenerDatos = Character.toLowerCase(sc.nextLine().trim().charAt(0)) == 'n';
		sc.close();
		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/personas.odb");
		EntityManager em = emf.createEntityManager();

		if (!mantenerDatos) {
			em.getTransaction().begin();
			em.createQuery("DELETE FROM Persona").executeUpdate();
			em.createQuery("DELETE FROM Boletin").executeUpdate();
			Persona p=null;
			for (int i = 1; i <= 5; i++) {
				Integer e = (int) (Math.random() * 30) + 10; // [10,39]
				String n = String.format("Persona %d", i);
				p = new Persona(n, e);
				p.putCalificacion("Programacion", 4+i);
				em.persist(p);
			}
			em.persist(new Boletin());

			Persona p1 = new Persona("Calixto",90);
			p1.removeBoletin();
			em.persist(p1);
			
//			em.flush();
//			em.remove(p.removeBoletin());
//
			
			em.getTransaction().commit();
		}
		Query q1 = em.createQuery("SELECT COUNT(p) FROM Persona p WHERE p.edad>=18");
		System.out.println("Total mayores de edad: " + q1.getSingleResult());

		Query q2 = em.createQuery("SELECT COUNT(p) FROM Persona p WHERE p.edad<18");
		System.out.println("Total menores de edad: " + q2.getSingleResult());

		Query q3 = em.createQuery("SELECT AVG(p.edad) FROM Persona p");
		System.out.println("Edad promedio: " + q3.getSingleResult());

		
		List<Boletin> boletines = em.createQuery("SELECT b FROM Boletin b", Boletin.class).getResultList();
		for (Boletin b : boletines) {
			System.out.println(b);
			if(b.getAlumno()!=null)
				System.out.println(b.getAlumno().getNombre());
			else
				System.out.println("<desconocido>");
		}
		
		System.out.println("\nPERSONAS");
		// Retrieve all the Point objects from the database:
		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> entidadesPersona = query.getResultList();
		for (Persona p : entidadesPersona) {
			System.out.println(p);
//			System.out.println(p.getNombre());
//			System.out.println(p.getCalificaciones());
		}
		
		em.close();
		emf.close();
	}
}