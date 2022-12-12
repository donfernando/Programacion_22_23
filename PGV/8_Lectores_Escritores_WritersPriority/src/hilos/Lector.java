package hilos;

import principal.P;

public class Lector extends Thread {

	public Lector(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			P.lectoresNoPasan.acquire();
			P.lectoresNoPasan.release();
			P.switchDeLectores.lock(P.escritoresNoPasan);

			// Simulamos la lectura
			System.out.println(getName() + " está leyendo: " + P.memCompartida);
			sleep(10);
			System.out.println(getName() + " termina de leer");

			P.switchDeLectores.unlock(P.escritoresNoPasan);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
