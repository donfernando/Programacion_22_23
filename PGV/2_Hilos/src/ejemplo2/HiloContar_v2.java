package ejemplo2;

import java.util.logging.Logger;

public class HiloContar_v2 extends Thread {
	private static int v = 0;
	private static Object o = new Object();
	private static Logger LOG = Logger.getGlobal();

	public HiloContar_v2(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			synchronized (o) {
				if (!getName().equals("Primero")) {
					LOG.info(getName()+" empieza esperando.");
					o.wait();
				}
				else
					o.wait(1000);
				while (v < 10) {
					System.out.println(getName() + ": " + (++v));
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
