package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Libro;
import entidades.Persona;

public class Principal02_ListarDatos {
	public static void main(String[] args) {

		// Open a database connection
		// (create a new database if it doesn't exist yet):
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/biblioteca.odb");
		EntityManager em = emf.createEntityManager();

		// Retrieve objects from the database:
		TypedQuery<Persona> qPersonas = em.createQuery("SELECT p FROM Persona p", Persona.class);
		List<Persona> lectores = qPersonas.getResultList();
		TypedQuery<Libro> qLibros = em.createQuery("SELECT l FROM Libro l", Libro.class);
		List<Libro> libros = qLibros.getResultList();
		em.getTransaction().begin();
			int i=0;
			while(i<libros.size() && libros.get(i).getPrestadoA()!=null)
				i++;
			if(i<libros.size())
				libros.get(i).setPrestadoA(lectores.get(0));
		em.getTransaction().commit();
		for (Persona p : lectores) {
			System.out.println(p);
		}
		for (Libro l : libros) {
			if(l.getPrestadoA()==null)
				System.out.println(l);
		}

		em.close();
		emf.close();
	}
}