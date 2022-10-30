package ejemplo_01;

import java.util.logging.Logger;

public class HiloContar extends Thread {
	private static int valor = 0;
	private static Logger LOG = Logger.getGlobal();
	private static final int LIMITE = 10;

	public HiloContar(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		while (valor < LIMITE) {
			String texto = getName() + ": " + (++valor);
			System.out.println(texto);
//			LOG.info(texto);
		}
	}

	public static void main(String[] args) {
		new HiloContar("Primero").start();
		new HiloContar("Hilo-1").start();
		new HiloContar("Hilo-2").start();
		new HiloContar("Hilo-3").start();
		new HiloContar("Hilo-4").start();
	}
}
