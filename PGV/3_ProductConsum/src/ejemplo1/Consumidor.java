package ejemplo1;

public class Consumidor extends Thread {
	
	public Consumidor(String nombre) {
		super(nombre);
	}
	
	@Override
	public void run() {
		long total = 0;
		try {
			synchronized(Main.o) {
				Main.o.wait(); //Para que espere hasta que se le notifique
				while(Main.caja != 99) {
					total += Main.caja;
					Main.LOG.info("consumo el "+Main.caja);					
					Main.o.notify();
					Main.o.wait();
				}
			}
			System.out.println(total);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
	}

} 