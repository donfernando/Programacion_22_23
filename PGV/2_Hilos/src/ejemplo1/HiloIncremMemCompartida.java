package ejemplo1;

public class HiloIncremMemCompartida extends Thread {

	private static Integer x = 0;
	private final static int VECES = 30;

	private String nombre;
	private int incremento;

	public HiloIncremMemCompartida(int increm, String nombre) {
		if (increm == 0 || nombre == null || nombre.isBlank())
			throw new RuntimeException();
		this.nombre = nombre;
		incremento = increm;
	}

	@Override
	public void run() {
		for (int i = 0; i < VECES; i++) {
			System.out.println(nombre + ": " + (x += incremento));
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Thread h1, h2, h3, h4;
		(h1 = new HiloIncremMemCompartida(1, "Hilo-1")).start();
		(h2 = new HiloIncremMemCompartida(1, "   Hilo-2")).start();
		(h3 = new HiloIncremMemCompartida(-1, "             Hilo-3")).start();
		(h4 = new HiloIncremMemCompartida(-1, "                Hilo-4")).start();
		h1.join();
		h2.join();
		h3.join();
		h4.join();
		System.out.println("Valor final: " + x);
	}
}
