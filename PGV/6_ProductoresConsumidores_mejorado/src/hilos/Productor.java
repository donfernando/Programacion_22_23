package hilos;

import java.util.Random;

import contenedores.Cola;

public class Productor extends Thread {

	private Cola<Integer> buffer;
	private Random azar = new Random();
	private int totProducido;

	public Productor(String nombre, Cola<Integer> buffer) {
		super(nombre);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		int vueltas = 100;
		int num;
		try {
			for (int i = 1; i <= vueltas; i++) {
				num = azar.nextInt(1, 11);
				totProducido += num;
				buffer.add(num);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getTotal() {
		return totProducido;
	}
}
