package hilos;

import principal.P;

public class Escritor implements Runnable {
    @Override
    public void run() {
        try {
        	P.emptyRoom.acquire();

            // Simulamos la escritura
        	System.out.println("\n"+Thread.currentThread().getName() + " está escribiendo");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " termina de escribir");
            P.emptyRoom.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
