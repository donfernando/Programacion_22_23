package hilos;

import principal.P;

public class Lector extends Thread {

	public Lector(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			try {
				P.lock.lock();
				while (P.escritores != 0)
					P.sePuedeLeer.await();
				P.lectores++;
			} finally {
				P.lock.unlock();
			}

			// Simulamos la lectura
			System.out.println(getName() + " está leyendo: " + P.memCompartida);
			sleep(3);
			System.out.println(getName() + " termina de leer");

			try {
				P.lock.lock();
				P.lectores--;
				if (P.lectores == 0)
					P.sePuedeEscribir.signal();
			} finally {
				P.lock.unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
