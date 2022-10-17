package ejemplo2;

import java.util.logging.Logger;

public class HiloContar extends Thread {
	private static int valor = 0;
	private static Object o = new Object();
	private static Logger LOG = Logger.getGlobal();

	public HiloContar(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			if (getName().equals("Primero"))
				sleep(1000);
			synchronized (o) {
				if (!getName().equals("Primero")) {
					LOG.info(getName()+" empieza esperando.");
					o.wait();
				}
				while (valor < 10) {
					System.out.println(getName() + ": " + (++valor));
//					LOG.info(getName()+" notifica.");					
					o.notify();
//					LOG.info(getName()+" espera.");					
					o.wait();
				}
				o.notifyAll();
			}
		} catch (InterruptedException e) {
			LOG.warning(getName()+" ha sido interumpido.");
		}
	}

	public static void main(String[] args) {
		new HiloContar("Primero").start();
		new HiloContar("Hilo-1").start();
		new HiloContar("Hilo-2").start();
	}
}
