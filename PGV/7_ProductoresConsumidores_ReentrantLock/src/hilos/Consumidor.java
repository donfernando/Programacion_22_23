package hilos;

import contenedores.Cola;

public class Consumidor extends Thread {

	private Cola<Integer> buffer;
	private int total = 0;
	
	public Consumidor(String nombre, Cola<Integer> buffer) {
		super(nombre);
		this.buffer = buffer;
	}

	@Override
	public void run() {	
		try {
			while (!isInterrupted()) {
				total += buffer.poll();
			}			
		} catch (InterruptedException e) {
		}
	}

	public int getTotal() {
		return total;
		
	}
}
