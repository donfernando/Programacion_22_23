package contenedores;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Cola<T> {
	private Semaphore mutex, items, espacios;
	private LinkedList<T> datos=new LinkedList<>();
	public Cola(int tamMax) {
		mutex = new Semaphore(1);
		items = new Semaphore(0);
		espacios = new Semaphore(tamMax);		
	}
	public void add(T x) throws InterruptedException {
		espacios.acquire();
		mutex.acquire();
			datos.add(x);
		mutex.release();
		items.release();
		
	}
	public T poll() throws InterruptedException {
		T aux;
		items.acquire();
		mutex.acquire();		
			aux = datos.remove(0);
		mutex.release();
		espacios.release();		
		return aux;
	}
}
