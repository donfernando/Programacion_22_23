package ejemplo_01;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class HiloContar extends Thread {
	private static int valor = 0;
	private static Logger LOG = Logger.getGlobal();
	private static final int LIMITE = 10;
	private static Semaphore sem = new Semaphore(1);
	private static Semaphore lim = new Semaphore(LIMITE);

	public HiloContar(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		
		try {
			while () {
				sem.acquire();
				String texto = getName() + ": " + (++valor);
				sem.release();
				lim.acquire();
				System.out.println(texto);
//			LOG.info(texto);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
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
