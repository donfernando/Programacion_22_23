package principal;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Libro;
import entidades.Persona;

public class Principal01_creaDatos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Desea eliminar los datos de anteriores ocasiones (S/N)? ");
		boolean mantenerDatos = Character.toLowerCase(sc.nextLine().trim().charAt(0)) == 'n';

		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/biblioteca.odb");
		EntityManager em = emf.createEntityManager();

		// Store 1000 Point objects in the database:
		em.getTransaction().begin();

		if (!mantenerDatos) {
			em.createQuery("DELETE FROM Libro").executeUpdate();
			em.createQuery("DELETE FROM Persona").executeUpdate();
		}

		for (int i = 1; i <= 5; i++) {
			System.out.print(i + ". Nombre: ");
			String nombre = sc.nextLine();
			Integer edad = (int) (Math.random() * 30) + 10; // [10,39]
			Persona p = new Persona(nombre, edad);
			System.out.println("...registrando a \"" + p + '"');
			em.persist(p);
		}
		for (int i = 1; i <= 3; i++) {
			System.out.print(i + ") Titulo: ");
			String titulo = sc.nextLine();
			Integer precio = (int) (Math.random() * 40) + 10; // [10,49]
			Libro lib = new Libro("ISBN" + System.currentTimeMillis() % 10000, titulo, "anónimo", null, precio);
			System.out.println("...registrando \"" + lib + '"');
			em.persist(lib);
		}
		sc.close();
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
		TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> results = query.getResultList();
		for (Persona p : results) {
			System.out.println(p);
		}
		em.close();
		emf.close();
	}
}