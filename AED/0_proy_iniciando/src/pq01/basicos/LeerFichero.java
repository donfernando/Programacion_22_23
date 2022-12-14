package pq01.basicos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeerFichero {

	public static void main(String[] args) throws IOException {
		Scanner sc;
		String linea;
		int x = 0, y = 0;
		ArrayList<Punto> coordenadas = new ArrayList<Punto>();

//		BufferedReader entrada = new BufferedReader(new FileReader("recursos/coordenadas.dat"));
		BufferedReader entrada = new BufferedReader(new FileReader(args[0]));
		linea = entrada.readLine();

		while (linea != null) {
			sc = new Scanner(linea);
			x = sc.nextInt();
			y = sc.nextInt();
			sc.close();
			Punto punto = new Punto(x, y);
			coordenadas.add(punto);
			linea = entrada.readLine();
		}
		entrada.close();
		System.out.println(coordenadas);
	}
}