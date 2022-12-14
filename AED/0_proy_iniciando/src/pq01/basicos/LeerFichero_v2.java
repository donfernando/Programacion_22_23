package pq01.basicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerFichero_v2 {

	public static void main(String[] args) throws IOException {

		String linea;
		int x = 0, y = 0;
		ArrayList<Punto> coordenadas = new ArrayList<Punto>();
String[] datos;
		BufferedReader entrada = new BufferedReader(new FileReader(args[0]));
		linea = entrada.readLine();
		
		while (linea != null) {
			datos = linea.split(" ");
			x = Integer.parseInt(datos[0]);
			y = Integer.parseInt(datos[1]);
			Punto punto = new Punto(x, y);
			coordenadas.add(punto);
			linea = entrada.readLine();
		}
		entrada.close();
		System.out.println(coordenadas);
	}
}