package ejemplo2.a.malo;

import java.util.Random;

public class Productor extends Thread {

	public Productor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		Random r = new Random();
		// TODO pruebaSimple
//		int veces = r.nextInt(100) + 1;
		int veces = 10;
		for (int i = 0; i < veces; i++) {
			// TODO pruebaSimple
//			Main.caja.setDato(r.nextInt(21)-10);
			Main.caja.setDato(1);
		}
		Main.caja.setDato(99);
	}
}