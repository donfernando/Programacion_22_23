package concurrencia;

import java.util.concurrent.Semaphore;

public class LightSwitch {

	private Semaphore mutex = new Semaphore(1);
	private int counter = 0;

	public void lock(Semaphore semaforo) throws InterruptedException {
		mutex.acquire();
		counter += 1;
		if (counter == 1) {
			semaforo.acquire();
		}
		mutex.release();
	}

	public void unlock(Semaphore semaforo) throws InterruptedException {
		mutex.acquire();
		counter -= 1;
		if (counter == 0) {
			semaforo.release();
		}
		mutex.release();
	}
}