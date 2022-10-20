package ejemplo1;

import java.util.Random;

public class Productor extends Thread {

	public Productor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
//		Random r = new Random();
//		int veces = r.nextInt(100) + 1;
		int veces = 10;
		try {
			sleep(2); //Para que el consumidor llegue antes.
			synchronized (Main.o) {
				for(int i = 0; i < veces; i++) {
//					Main.caja = r.nextInt(21) - 10;
					Main.caja = 1;
					Main.o.notify();
					Main.o.wait();
				}
				Main.caja = 99;
				Main.o.notify();
			} 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

} 