package principal;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import hilos.Escritor;
import hilos.Lector;

public class P {
	public static int readers = 0;
	public static Semaphore mutex = new Semaphore(1);
	public static Semaphore emptyRoom = new Semaphore(1);
					
	
	 public static void main(String[] args) throws Exception {
	        Lector lector = new Lector();
	        Escritor escritor = new Escritor();
	        ArrayList<Thread> lectores = new ArrayList<>(); 
	        ArrayList<Thread> escritores = new ArrayList<>();
	        for (int i = 1; i <= 10; i++) {
	        	lectores.add(new Thread(lector,"Lector_"+i));
	        }
	        for (int i = 1; i <= 10; i++) {
		        escritores.add(new Thread(escritor,"Escritor_"+i));
	        }
	        for (int i = 0; i < 10; i++) {
	        	lectores.get(i).start();
	        }
	        for (int i = 0; i < 10; i++) {
	        	escritores.get(i).start();
	        }
	    }

} 