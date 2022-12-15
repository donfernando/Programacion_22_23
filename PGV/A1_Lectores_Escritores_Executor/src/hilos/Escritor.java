package hilos;

import principal.P;

public class Escritor implements Runnable {

	private String nombre;

	public Escritor(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		try {
			P.switchDeEscritores.lock(P.lectoresNoPasan);
			P.escritoresNoPasan.acquire();

			// Simulamos la escritura
			System.out.print(nombre + " está escribiendo    ");
			P.memCompartida.append(P.numCompartido++);
			Thread.sleep(1000);
			System.out.println(" y ya, termina de escribir");

			P.escritoresNoPasan.release();
			P.switchDeEscritores.unlock(P.lectoresNoPasan);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
