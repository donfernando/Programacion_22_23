package principal;
import java.util.Arrays;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.*;

public class CrearDatos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Desea eliminar los datos de anteriores ocasiones (S/N)? ");
		boolean mantenerDatos = Character.toLowerCase(sc.nextLine().trim().charAt(0)) == 'n';
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();

		if (!mantenerDatos) {
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
			Libro cerebro = new Libro("222222", "Científica", "Estudio del cerebro humano", 1890,Arrays.asList(autor3,autor4));
			Libro programacion = new Libro("999999", "IES Domingo Perez Minik", "Introducción a la Programación", 2020,autor2);
			
			
			Copia laz1 = new Copia(true);
			Copia laz2 = new Copia(false);
			Copia prog1 = new Copia(false);
			Copia prog2 = new Copia(false);
			Copia cer1 = new Copia(true);
			Copia cer2 = new Copia(false);
			
			lazarillo.addCopia(true);
			programacion.addCopia(true);
			cerebro.addCopia(false);
			
//			Copia_Digital laz1_dig = new Copia_Digital("lazarillo","pdf");
//			Copia_Digital laz2_dig = new Copia_Digital("LazDeTor","pdf");
//			Copia_Digital prog1_dig = new Copia_Digital("PRO","pdf");
//			Copia_Digital prog2_dig = new Copia_Digital("progarmacion","txt");
//			Copia_Digital cer1_dig = new Copia_Digital("Cerebro_RyC","pdf");
//			Copia_Digital cer2_dig = new Copia_Digital("Ramon-Cajal-Cerebro", "prc");
			
//			laz1_dig.setOriginal(laz1);
//			laz2_dig.setOriginal(laz2);
//			prog1_dig.setOriginal(prog1);
//			prog2_dig.setOriginal(prog2);
//			cer1_dig.setOriginal(cer1);
//			cer2_dig.setOriginal(cer2);
			
			Persona p1 = new Persona("54063242V", "Eduardo", "Guerra Rodríguez", "C/ El Edén, 10", "+34 618295409", "eduardo.guerra.rguez@gmail.com");
			Persona p2 = new Persona("63524172L", "Paco", "Porras Padilla", "C/ Sin Nombre, 7", "+34 623121234", "papopa@gmail.com");
			Persona p3 = new Persona("81920372K", "Fracisco", "Fernández Fariña", "C/ Con Nombre, 12", "+34 697386221", "Fra_Fer_Far@gmail.com");
			
			p1.addLibro(laz2);
			p1.addLibro(prog1);
			p2.addLibro(cer1);
			p3.addLibro(cer2);
			
			em.persist(autor1);
			em.persist(autor2);
			em.persist(autor3);
			em.persist(autor4);
			em.persist(lazarillo);
			em.persist(cerebro);
			em.persist(programacion);
			em.persist(laz1);
			em.persist(laz2);
			em.persist(cer1);
			em.persist(cer2);
			em.persist(prog1);
			em.persist(prog2);
			em.persist(laz1_dig);
			em.persist(laz2_dig);
			em.persist(cer1_dig);
			em.persist(cer2_dig);
			em.persist(prog1_dig);
			em.persist(prog2_dig);
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);
			
			em.getTransaction().commit();
		}
		
		em.close();
		emf.close();
		sc.close();
	}
}
