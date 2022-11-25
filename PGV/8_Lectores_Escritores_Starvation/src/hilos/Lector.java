package hilos;

import principal.P;

public class Lector implements Runnable {
    @Override
    public void run() {
        try {
        	P.mutex.acquire();
        	P.readers++;
            if (P.readers == 1) {
            	P.emptyRoom.acquire();
            }
            P.mutex.release();

            // Simulamos la lectura
            System.out.println(Thread.currentThread().getName() + " está leyendo");
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + " Ha leído");

            P.mutex.acquire();
            P.readers--;
            if(P.readers == 0) {
            	P.emptyRoom.release();
            }
            P.mutex.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

