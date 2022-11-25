package hilos;

import java.util.Random;
import java.util.concurrent.Semaphore;

import contenedores.Cola;

public class Productor extends Thread {

	private Semaphore mutex, items, espacios;
	private Cola<Integer> buffer;
	private Random azar = new Random();
	private int totProducido;

	public Productor(String nombre, Semaphore mutex, Semaphore items, Semaphore espacios, Cola<Integer> buffer) {
		super(nombre);
		this.mutex = mutex;
		this.items = items;
		this.espacios = espacios;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		int vueltas = 10;
		int num;
		try {
			for (int i = 1; i <= vueltas; i++) {
				num = azar.nextInt(1, 11);
				totProducido += num;
				espacios.acquire();
				mutex.acquire();
					buffer.add(num);
				mutex.release();
				items.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getTotProducido() {
		return totProducido;
	}
}
