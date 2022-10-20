package ejemplo2.a.malo;

public class Caja {
	private int dato;

	public int getDato() {
		int x;
		synchronized (this) {
			x=dato;
		}
		return x;
	}

	public void setDato(int dato) {
		synchronized (this) {
			this.dato = dato;
		}
	}
	
}
