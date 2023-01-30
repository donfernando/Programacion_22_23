package principal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Copia;
import entidades.Libro;
import entidades.Persona;

public class ListadoPersona_y_Prestamos_manual {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		List<Persona> personas = em.createQuery("SELECT p FROM Persona p",Persona.class).getResultList();
		List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib", Libro.class).getResultList();

		for (Persona persona : personas) {
			System.out.println(persona);
			for (Copia copia : persona.getLibros()) {
				System.out.print("   - [copia " + copia.getId_copia() + "] Titulo: ");
				int i = 0;
				while(i<libros.size() && !libros.get(i).getCopias().contains(copia))
					i++;
				if(i<libros.size())
					System.out.println("\"" + libros.get(i).getTitulo() + "\"");
				else
					throw new RuntimeException("La copia prestada no se corresponde con ningún libro de la BD");
			}
		} 	
		
		em.close();
		emf.close();
	}
}
