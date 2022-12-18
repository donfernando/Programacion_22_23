package principal;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import concurrencia.LightSwitch;
import hilos.Escritor;
import hilos.Lector;

public class P {
	public static Semaphore lectoresNoPasan = new Semaphore(1);
	public static Semaphore escritoresNoPasan = new Semaphore(1);
	public static LightSwitch switchDeLectores = new LightSwitch();
	public static LightSwitch switchDeEscritores = new LightSwitch();
	public static StringBuffer memCompartida = new StringBuffer("DAT: ");
	public static int numCompartido = 0;

	public static void main(String[] args) throws Exception {
		ArrayList<Runnable> lectores_y_escritores = new ArrayList<>();
		ArrayBlockingQueue<Runnable> queue = 
		        new ArrayBlockingQueue<Runnable>(20);
		ThreadPoolExecutor threadPool = 
		        new ThreadPoolExecutor(12, 12,10, TimeUnit.SECONDS, queue);
		
		
		for (int i = 1; i <= 20; i++) {
			if(Math.random()>=.25)
				lectores_y_escritores.add(new Lector("Lector_" + i));
			else
				lectores_y_escritores.add(new Escritor("Escritor_" + i));
		}
		for (int i = 0; i < 20; i++) {
			threadPool.execute(lectores_y_escritores.get(i));
		}
		
		threadPool.shutdown();
	}

}