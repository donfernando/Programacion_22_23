package principal;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import contenedores.Cola;
import hilos.Consumidor;
import hilos.Productor;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Productor> p = new ArrayList<>();
		ArrayList<Consumidor> c = new ArrayList<>();
		final int TAM=5;

		Semaphore mutex = new Semaphore(1);
		Semaphore items = new Semaphore(0);
		Semaphore espacios = new Semaphore(TAM);
		Cola<Integer> buffer = new Cola<>(TAM);
		
		p.add(new Productor("Prod A  *******",mutex, items, espacios,buffer));
		p.add(new Productor("Prod B  *******",mutex, items, espacios,buffer));
		p.add(new Productor("Prod C  *******",mutex, items, espacios,buffer));
		p.add(new Productor("Prod D  *******",mutex, items, espacios,buffer));
		
		c.add(new Consumidor("Consum 1 ------",mutex, items, espacios,buffer));
		c.add(new Consumidor("Consum 2 ------",mutex, items, espacios,buffer));
		
		for (Thread productor : p) {
			productor.start();
		}
		for (Thread consumidor : c) {
			consumidor.start();
		}
		for (Thread productor : p) {
			productor.join();
		}
		for (Consumidor consumidor : c) {
			consumidor.interrupt();
		}
		for (Consumidor consumidor : c) {
			consumidor.join();
		}
		System.out.println("Consum *************************");
		for (Consumidor consumidor : c) {
			System.out.println(consumidor.getTotal());
		}
		System.out.println("Product *************************");
		for (Productor productor : p) {
			System.out.println(productor.getTotProducido());
		}
		
	}

}
