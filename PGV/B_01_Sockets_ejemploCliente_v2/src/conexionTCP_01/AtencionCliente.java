package conexionTCP_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Logger;

public class AtencionCliente extends Thread {

	private Socket socket;
	public AtencionCliente(Socket sk) {
		socket = sk;
	}

	@Override
	public void run() {
		try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter salida = new PrintWriter(socket.getOutputStream(), true, Charset.forName("UTF8"));) {
			double total = 0.0;
			String linea = entrada.readLine();
			while (!linea.isEmpty()) { // linea != null si se cerrase el socket
				Logger.getGlobal().info("-Servidor: He recibido \"" + linea + "\".");
				Scanner sc = new Scanner(linea);
				String sItem=null;
				while (sc.hasNext()) { // si hay algo lo lee
					try {
						total += Double.parseDouble(sItem = sc.next());
					} catch (NumberFormatException e) {
						Logger.getGlobal().warning(sItem+" no es un número válido\n");
					}
				}
				sc.close();
				linea = entrada.readLine();
			}
			Logger.getGlobal().info("-Servidor: Fin de la llegada de datos.");

			// Retornar resultado
			Logger.getGlobal().info("-Servidor: Cálculos realizados, enviando: " + total);

			salida.printf("%.2f\n",total);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
