package ejemplo3;

import java.util.Random;

public class Productor extends Thread {

	public Productor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		Random r = new Random();
		int veces = 5;
		try {
			Main.LOG.info("Empiezo.");
			for(int i = 0; i < veces; i++) {
//				Main.caja.setDato(r.nextInt(21)-10);
				Main.caja.setDato(i+1);
				// Simulando caso mas real los productores son irregulares en su produccion.
				// El efecto es que los consumidores responden de manera más variable y realista.				
				sleep(r.nextInt(21)+1);
				}
		} catch (InterruptedException e) {
			Main.LOG.warning("Tarea interrumpida.\n"+e.getMessage());
		}
	}

} 