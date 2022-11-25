package hilos;

import java.util.concurrent.Semaphore;

import contenedores.Cola;

public class Consumidor extends Thread {

	private Semaphore mutex, items, espacios;
	private Cola<Integer> buffer;
	private int total = 0;
	
	public Consumidor(String nombre, Semaphore mutex, Semaphore items, Semaphore espacios, Cola<Integer> buffer) {
		super(nombre);
		this.mutex = mutex;
		this.items = items;
		this.espacios = espacios;
		this.buffer = buffer;
	}

	@Override
	public void run() {	
		try {
			while (!isInterrupted()) {
				items.acquire();
				mutex.acquire();
				total += buffer.poll();
				mutex.release();
				espacios.release();
			}			
		} catch (InterruptedException e) {
			System.out.println("Fin consum...");
		}
	}

	public int getTotal() {
		return total;
		
	}
}
