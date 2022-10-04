package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class MiniShell {
	public static void main(String[] args) {
		Runtime builder = Runtime.getRuntime();
		String linea;
		String comando;
		Scanner teclado = new Scanner(System.in);
		try {
			System.out.print("Comando: ");
			comando = teclado.nextLine();
			while (!comando.equalsIgnoreCase("exit")) {
				Process proc = builder.exec(comando);
				BufferedReader salida = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				while ((linea = salida.readLine()) != null) {
					System.out.println(linea);
				}
				System.out.print("Comando: ");
				comando = teclado.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		teclado.close();
		System.out.println("bye,,,");
	}
}
