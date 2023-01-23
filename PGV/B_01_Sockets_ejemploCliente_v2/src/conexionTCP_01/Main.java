package conexionTCP_01;

public class Main {
	static final int PUERTO = 50000;
	public static void main(String[] args) {
		if (args.length == 0 || args.length == 1 & Character.toLowerCase(args[0].charAt(0)) != 's') {
			System.out.println("Sintaxis:");
			System.out.println("\tjava -jar comunicaciones.jar c <IP HOST>");
			System.out.println("\tjava -jar comunicaciones.jar s");
			System.exit(-1);
		}
		if (Character.toLowerCase(args[0].charAt(0)) == 's') {
			Servidor.iniciarServidor();
		} else {
			Cliente.iniciarCliente(args[1]);
		}
	}
}