package conexionTCP_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Logger;

public class Cliente {

	public static void iniciarCliente(String host) {
		try (Socket sCliente = new Socket(host, Main.PUERTO);
			 BufferedReader entrada = new BufferedReader(new InputStreamReader(sCliente.getInputStream()));
			 PrintWriter salida = new PrintWriter(sCliente.getOutputStream(), true, Charset.forName("UTF8"));) {
			Logger.getGlobal().info("-Cliente: Cliente accediendo al servidor " + host);
			Scanner sc = new Scanner(System.in);
			String linea;
			System.out.println("Ingresa números para el servidor");
			System.out.println("Retorno de carro (enter) para terminar.");

			while (!(linea = sc.nextLine().trim()).isEmpty()) {
				salida.println(linea);
				Logger.getGlobal().info("-Cliente: Enviando: " + linea);
				System.out.println("Ingresa más números... ");

			}
			salida.println();
			linea = entrada.readLine(); //
			System.out.println("Recibido: " + linea);
			sc.close();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			System.out.println("Debes estar conectado a un servidor o haber un servidor disponible para que el programa funcione.");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
