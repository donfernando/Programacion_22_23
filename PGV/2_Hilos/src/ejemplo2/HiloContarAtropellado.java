package ejemplo2;

import java.util.logging.Logger;

public class HiloContarAtropellado extends Thread {
	private static int valor = 0;
	private static Logger LOG = Logger.getGlobal();
	private static final int LIMITE = 10;

	public HiloContarAtropellado(String nombre) {
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
		new HiloContarAtropellado("Primero").start();
		new HiloContarAtropellado("Hilo-1").start();
		new HiloContarAtropellado("Hilo-2").start();
		new HiloContarAtropellado("Hilo-3").start();
		new HiloContarAtropellado("Hilo-4").start();
	}
}
