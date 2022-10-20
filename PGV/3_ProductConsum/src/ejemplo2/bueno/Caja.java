package ejemplo2.bueno;

public class Caja {
	private int dato;
	private boolean hayDato ;

	public int getDato() throws InterruptedException {
		int x;
		synchronized (this) {
			while(!hayDato)
				wait();
			x=dato;
			hayDato = false;
			notify();
		}
		return x;
	}

	public void setDato(int dato) throws InterruptedException {
		synchronized (this) {
			while (hayDato)
				wait();
			this.dato = dato;
			hayDato = true;
			notify();
		}
	}
	
}
