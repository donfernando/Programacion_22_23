package contenedores;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola<T> {

	private Lock cierre = new ReentrantLock();

	private Condition esperaHuecos = cierre.newCondition();
	private Condition esperaProductos = cierre.newCondition();

	private LinkedList<T> datos = new LinkedList<>();
	private final int TAM_MAX;

	public Cola(int tam) {
		TAM_MAX = tam;
	}

	public void add(T x) throws InterruptedException {
		try {
			cierre.lock();
			while (datos.size() == TAM_MAX) {
				esperaHuecos.await();
			}
			datos.add(x);
			esperaProductos.signal();
		} finally {
			cierre.unlock();
		}
	}

	public T poll() throws InterruptedException {
		T aux;
		try {
			cierre.lock();
			while (datos.size() == 0) {
				esperaProductos.await();
			}
			aux = datos.remove(0);
			esperaHuecos.signal();
		} finally {
			cierre.unlock();
		}
		return aux;
	}
}
