package principal;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import entidades.Autor;
import entidades.Copia;
import entidades.Libro;
import entidades.Persona;
import static entidades.Copia.*;

public class Datos {
	public static void crear(EntityManager em) {
		em.getTransaction().begin();

		em.createQuery("DELETE FROM Autor").executeUpdate();
		em.createQuery("DELETE FROM Libro").executeUpdate();
		em.createQuery("DELETE FROM Copia").executeUpdate();
		em.createQuery("DELETE FROM Copia_Digital").executeUpdate();
		em.createQuery("DELETE FROM Persona").executeUpdate();

		Autor autor2 = new Autor("Fernando");
		Autor autor3 = new Autor("Ramón");
		Autor autor4 = new Autor("Cajal");
		Libro lazarillo = new Libro("000000", "Popular", "La Vida de Lazarillo de Tormes", 1554);
		Libro cerebro = new Libro("222222", "Científica", "Estudio del cerebro humano", 1890,
				Arrays.asList(autor3, autor4));
		Libro programacion = new Libro("999999", "IES Domingo Perez Minik", "Introducción a la Programación", 2020,
				autor2);

		lazarillo.addCopia(DETERIORADO);
		programacion.addCopia(DETERIORADO);
		cerebro.addCopia(COMO_NUEVO);
		cerebro.addCopia(DETERIORADO);

		Persona p1 = new Persona("54063242V", "Esteban", "Plaza Mayor", "C/ Turuta, 10", "+34 44488341",
				"esteban.coesta.ocupado@gmail.com");
		Persona p2 = new Persona("63524172L", "Paco", "Porras Padilla", "C/ Sin Nombre, 7", "+34 623121234",
				"papopa@gmail.com");
		Persona p3 = new Persona("81920372K", "Fracisco", "Fernández Fariña", "C/ Con Nombre, 12", "+34 697386221",
				"Fra_Fer_Far@gmail.com");

		programacion.getCopias().get(0).addPrestatario(p3);
		lazarillo.getCopias().get(0).addPrestatario(p3);
		programacion.getCopias().get(1).addPrestatario(p1);
		cerebro.getCopias().get(0).addPrestatario(p1);
		cerebro.getCopias().get(2).addPrestatario(p3);

		em.persist(lazarillo);
		em.persist(cerebro);
		em.persist(programacion);

		em.persist(p1);
		em.persist(p2);
		em.persist(p3);

		System.out.println(autor2);
		em.getTransaction().commit();

		System.out.println(autor2);
		System.out.println(programacion);
		for (Copia cp : programacion.getCopias()) {
			System.out.println("\t" + cp);
		}
	}

	public static void mostrar(EntityManager em) {
		List<Libro> libros = em.createQuery("SELECT lib FROM Libro lib", Libro.class).getResultList();
		for (Libro l : libros) {
			System.out.println(l);
			for (Copia cp : l.getCopias()) {
				System.out.println("\t" + cp);
			}
		}
		
		System.out.println("\n***********************\n");
		System.out.println("Socios y prestamos actuales");
		List<Persona> personas = em.createQuery("SELECT per FROM Persona per", Persona.class).getResultList();
		for (Persona p : personas) {
			System.out.println();
			System.out.println(p);
			for (Copia cp : p.getLibros()) {
				System.out.println("\t" + cp);
			}
		}
		
		
		
	}
}
