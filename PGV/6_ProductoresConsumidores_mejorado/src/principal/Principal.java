package principal;

import java.util.ArrayList;

import contenedores.Cola;
import hilos.Consumidor;
import hilos.Productor;

public class Principal {

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Productor> productores = new ArrayList<>();
		ArrayList<Consumidor> consumidores = new ArrayList<>();
		final int TAM=15;

		Cola<Integer> buffer = new Cola<>(TAM);
		
		productores.add(new Productor("Prod A",buffer));
		productores.add(new Productor("Prod B",buffer));
		productores.add(new Productor("Prod C",buffer));
		productores.add(new Productor("Prod D",buffer));
		
		consumidores.add(new Consumidor("Consum 1",buffer));
		consumidores.add(new Consumidor("Consum 2",buffer));
		
		for (Thread productor : productores) {
			productor.start();
		}
		for (Thread consumidor : consumidores) {
			consumidor.start();
		}
		for (Thread productor : productores) {
			productor.join();
		}
		for (Consumidor consumidor : consumidores) {
			consumidor.interrupt();
		}
		for (Consumidor consumidor : consumidores) {
			consumidor.join();
		}
		mostrarResultados(productores,consumidores);
	}

	private static void mostrarResultados(ArrayList<Productor> productores, ArrayList<Consumidor> consumidores) {
		int tot,total=0;
		System.out.println("*************************");
		for (Consumidor c : consumidores) {
			tot=c.getTotal();
			System.out.println(c.getName()+" consumió "+tot);
			total += tot;
		}
		System.out.println("TOTAL consumido: "+total);		
		total=0;
		System.out.println("*************************");
		for (Productor p : productores) {
			tot=p.getTotal();
			System.out.println(p.getName()+" produjo "+tot);
			total += tot;
		}		
		System.out.println("TOTAL producido: "+total);		
	}
}
