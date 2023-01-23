package conexionTCP_01;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Servidor {
	public static void iniciarServidor() {
		try (ServerSocket skServidor = new ServerSocket(Main.PUERTO)) {
			System.out.println("-Servidor..."+
					" puerto: " + skServidor.getLocalPort()+
					" host: " + skServidor.getInetAddress());
			while (true) {
				Socket sCliente = skServidor.accept();
				Logger.getGlobal().info("-Servidor: Petición aceptada\n");
				new AtencionCliente(sCliente).start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		iniciarServidor();
	}
}
