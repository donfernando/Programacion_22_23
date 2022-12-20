package hilos;

import principal.P;

public class Lector implements Runnable {

	private String nombre;

	public Lector(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		try {
			P.lectoresNoPasan.acquire();
			P.lectoresNoPasan.release();
			P.switchDeLectores.lock(P.escritoresNoPasan);

			// Simulamos la lectura
			System.out.println(nombre + " está leyendo: " + P.memCompartida);
			Thread.sleep(1000);
			System.out.println(nombre + " termina de leer");

			P.switchDeLectores.unlock(P.escritoresNoPasan);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
