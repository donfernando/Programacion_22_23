package principal;

import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Copia;
import entidades.Libro;
import entidades.Persona;

public class ListadoPersona_y_Prestamos_manual_v2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		List<Persona> personas = em.createQuery("SELECT p FROM Persona p",Persona.class).getResultList();
		List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib", Libro.class).getResultList();

		for (Persona persona : personas) {
			System.out.println(persona);
			for (Copia copia : persona.getLibros()) {
				System.out.print("   - [copia " + copia.getId_copia() + "] Titulo: ");
				Iterator<Libro> i = libros.iterator();
				Libro aux=null;
				while(i.hasNext() && !(aux=i.next()).getCopias().contains(copia))
					;
				if(aux!=null && aux.getCopias().contains(copia))
					System.out.println("\"" + aux.getTitulo() + "\"");
				else
					throw new RuntimeException("La copia prestada no se corresponde con ningún libro de la BD");
			}
		} 	
		
		em.close();
		emf.close();
	}
}
