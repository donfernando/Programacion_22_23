package ejemplo2.a.malo;

public class Consumidor extends Thread {

	public Consumidor(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		long sum = 0;
		int numero;
		numero = Main.caja.getDato();
		while (numero != 99) {
			sum += numero;
			numero = Main.caja.getDato();
		}
		System.out.println(sum);
	}
}