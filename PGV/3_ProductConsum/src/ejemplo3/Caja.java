package ejemplo3;

public class Caja {
	private int dato;
	private boolean hayDato;

	public synchronized int getDato() throws InterruptedException {
		int x;
		while (!hayDato)
			wait();
		x = dato;
		Main.LOG.info(Thread.currentThread().getName() + " leyendo " + x);
		// System.out.println(Thread.currentThread().getName() + " leyendo " + x);
		hayDato = false;
		notifyAll();

		return x;
	}

	public synchronized void setDato(int dato) throws InterruptedException {
		while (hayDato)
			wait();
		this.dato = dato;
		Main.LOG.info(Thread.currentThread().getName() + " escribiendo " + dato);
		// System.out.println(Thread.currentThread().getName() + " escribiendo " +
		// dato);
		hayDato = true;
		notifyAll();
	}

}
