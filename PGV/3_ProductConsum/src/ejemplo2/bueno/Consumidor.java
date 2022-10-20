package ejemplo2.bueno;

public class Consumidor extends Thread {

	public Consumidor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		long sum = 0;
		int numero;
		try {
			numero = Main.caja.getDato();
			while (numero != 99) {
				sum += numero;
				numero = Main.caja.getDato();
			}
			System.out.println(sum);
		} catch (InterruptedException e) {
			Main.LOG.warning("Tarea interrumpida.\n"+e.getMessage());
		
		}
	}
}