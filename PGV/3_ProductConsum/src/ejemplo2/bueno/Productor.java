package ejemplo2.bueno;

import java.util.Random;

public class Productor extends Thread {

	public Productor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
//		 TODO pruebaSimple
//		Random r = new Random();
//		int veces = r.nextInt(100) + 1;
		int veces = 10;
		try {
			Main.LOG.info("Empiezo.");
			for(int i = 0; i < veces; i++) {
//				 TODO pruebaSimple
//				Main.caja.setDato(r.nextInt(21)-10);
				Main.caja.setDato(1);
				}
			Main.caja.setDato(99);
		} catch (InterruptedException e) {
			Main.LOG.warning("Tarea interrumpida.\n"+e.getMessage());
		}
	}

} 