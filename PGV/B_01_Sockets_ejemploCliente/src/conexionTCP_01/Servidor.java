package conexionTCP_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Servidor {
	public static void main(String[] args) {
		String servidor = "localhost";
		int puerto = MiProtocolo.PUERTO;

		// puerto de daytime
		try {
			// Se reserva y se publica puerto de escucha
			ServerSocket server = new ServerSocket(puerto);

			// Se espera peticion
			Socket socket = server.accept();
			System.out.println("Socket Abierto.");
			// Se consigue el canal de entrada
			BufferedReader entrada = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			Writer salida = new OutputStreamWriter(
					socket.getOutputStream());
			
			// Lee datos
			System.out.print("Recibido: ");
			String linea = entrada.readLine();
			System.out.println(linea);
			
			// Procesa datos
			Scanner sc = new Scanner(linea);
			double total = 0.0;
			while (sc.hasNextDouble())
				total += sc.nextDouble();

			// Retorna resultado
			String resultado = String.format("%.2f", total);
			System.out.println("Enviando: " + resultado);
			salida.write(resultado+"\n");
			salida.flush();

			// Se cierran canales
			entrada.close();
			salida.close();
			socket.close();
		} catch (UnknownHostException e) {
			System.out.println(e);
			System.out.println("Debes estar conectado para que esto funcione bien.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
