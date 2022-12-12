package hilos;

import principal.P;

public class Escritor extends Thread {

	public Escritor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		try {
			P.lock.lock();
			P.escritores++;
			while (P.lectores != 0 | P.escritorEnAccion)
				P.sePuedeEscribir.await();
			P.escritorEnAccion=true;
			
			// Simulamos la escritura
			System.out.print(getName() + " está escribiendo    ");
			P.memCompartida.append(P.numCompartido++);
			sleep(300);
			System.out.println(" y ya, termina de escribir");

			P.escritorEnAccion=false;
			P.escritores--;
			if(P.escritores!=0)
				P.sePuedeEscribir.signal();
			else
				P.sePuedeLeer.signalAll();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			P.lock.unlock();
		}
	}
}
