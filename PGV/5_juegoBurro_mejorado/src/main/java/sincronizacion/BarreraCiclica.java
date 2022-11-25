package sincronizacion;

import java.util.concurrent.Semaphore;

public class BarreraCiclica {
	private int N;
	private int cont;
	private Semaphore mutex = new Semaphore(1);
	private Semaphore barrera1 = new Semaphore(0);
	private Semaphore barrera2 = new Semaphore(0);

	public BarreraCiclica(int nHilos) {
		N = nHilos;
	}
	public void fase1() throws InterruptedException {
		mutex.acquire();
		if (++cont == N) {
			barrera1.release(N);
// TODO eliminar			
System.err.println();			
		}
		mutex.release();
		barrera1.acquire();
	}

	public void fase2() throws InterruptedException {
		mutex.acquire();
		if (--cont == 0)
			barrera2.release(N);
		mutex.release();
		barrera2.acquire();
	}
	public void espera() throws InterruptedException {
		fase1();
		fase2();
	}


	public void anularBarrera() {
		barrera1.release(N);
		barrera2.release(N);
	}

}
