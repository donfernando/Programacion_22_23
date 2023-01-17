package conexionTCP_01;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		String servidor = "localhost";
		int puerto = MiProtocolo.PUERTO;
		// puerto de daytime
		try {
			// Se abre un socket
			Socket socket = new Socket(servidor, puerto);
			System.out.println("Socket Abierto.");
			// Se consigue el canal de entrada
			BufferedReader entrada = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			Writer salida = new OutputStreamWriter(
					socket.getOutputStream());

			String linea = "3 5,5";
			System.out.println("Enviando: " + linea);
			salida.write(linea+"\n");
			salida.flush();
			
			// Lee datos
			linea = entrada.readLine();
			System.out.print("Recibido: ");
			System.out.println(linea);
			
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
