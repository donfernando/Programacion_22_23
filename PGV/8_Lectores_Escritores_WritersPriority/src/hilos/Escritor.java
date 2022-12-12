package hilos;

import principal.P;

public class Escritor extends Thread {

	public Escritor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			P.switchDeEscritores.lock(P.lectoresNoPasan);
			P.escritoresNoPasan.acquire();

			// Simulamos la escritura
			System.out.print(getName() + " está escribiendo    ");
			P.memCompartida.append(P.numCompartido++);
			sleep(300);
			System.out.println(" y ya, termina de escribir");

			P.escritoresNoPasan.release();
			P.switchDeEscritores.unlock(P.lectoresNoPasan);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
