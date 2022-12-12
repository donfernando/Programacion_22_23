package principal;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import hilos.Escritor;
import hilos.Lector;

public class P {
	public static Lock lock = new ReentrantLock();
	public static Condition sePuedeLeer = lock.newCondition();
	public static Condition sePuedeEscribir = lock.newCondition();
	public static int lectores = 0;
	public static int escritores = 0;

	public static StringBuffer memCompartida = new StringBuffer("DAT: ");
	public static int numCompartido = 0;
	public static boolean escritorEnAccion=false;

	public static void main(String[] args) throws Exception {
		ArrayList<Thread> lectores_y_escritores = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			if(Math.random()>=.35)
				lectores_y_escritores.add(new Lector("Lector_" + i));
			else
				lectores_y_escritores.add(new Escritor("Escritor_" + i));
		}
		for (int i = 0; i < 20; i++) {
			lectores_y_escritores.get(i).start();
		}
	}

}