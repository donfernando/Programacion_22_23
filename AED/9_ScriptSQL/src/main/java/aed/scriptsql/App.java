package aed.scriptsql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FilenameUtils;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {

		File propiedadesFile;
		File scriptFile;
		ConexionDB conexion = new ConexionDB();

		/*
		 * Comprobación de la cantidad de parámetros que recibe el programa
		 */

		if (args.length != 2) {

			System.err.println("La cantidad de parámetros no coincide con la que necesita el programa.\n"
					+ "-- Necesarios dos (2) - Recibe: " + args.length);
			System.exit(-1);
		}

		/*
		 * Comprobación de las extensiones de los ficheros que recibe el programa.
		 * 
		 * Para poder garantizar que no se reciben fichero indevidos y que se reciben en
		 * el orden correcto
		 */

		if (!FilenameUtils.getExtension(args[0]).equals("prop")
				&& !FilenameUtils.getExtension(args[0]).equals("properties")) {
			System.err.println("El tipo de archivo recibido en el primer parámetro no coincide con le esperado.\n"
					+ "-- Esperado fichero de propiedades (.prop | .properties) - Rebicibido: ."
					+ FilenameUtils.getExtension(args[0]));
			System.exit(-1);
		} else if (!FilenameUtils.getExtension(args[1]).equals("sql")) {
			System.err.println("El tipo de archivo recibido en el primer parámetro no coincide con le esperado.\n"
					+ "-- Esperado fichero SQL (.sql) - Rebicibido: ." + FilenameUtils.getExtension(args[1]));
			throw new RuntimeException();
		}

		propiedadesFile = new File(args[0]);
		scriptFile = new File(args[1]);

		try(Scanner sc = new Scanner(scriptFile).useDelimiter(";");) {
			conexion.conectarBD(propiedadesFile);

			while (sc.hasNext()) {
				String comando = sc.next().trim();
				try {
					conexion.ejecutar(comando);
				} catch (SQLException e) {
					System.err.println("Error ejecutando el Comando: "+comando);
					System.err.println(e);
					System.err.println("");
				}
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
