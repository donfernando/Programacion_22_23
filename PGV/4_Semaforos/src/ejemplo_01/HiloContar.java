package ejemplo_01;

import java.util.concurrent.Semaphore;

public class HiloContar extends Thread {
	private static int valor = 0;
	private static final int LIMITE = 30;
	private static final int N_HILOS = 5;
	private static Semaphore mutex = new Semaphore(1);

	public HiloContar(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			while (valor<LIMITE) {
				mutex.acquire();
				if(valor<LIMITE)
					System.out.println(getName() + ": " + (++valor));
				mutex.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Thread[] hilos = new Thread[N_HILOS];
		hilos[0] = new HiloContar("Primero");
		for (int i = 1; i < N_HILOS; i++) 
			hilos[i]= new HiloContar(String.format("Hilo-%d",i));
		for (int i = 0; i < N_HILOS; i++) 
			hilos[i].start();

	}
}
