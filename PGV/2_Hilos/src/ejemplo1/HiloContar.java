package ejemplo1;

public class HiloContar extends Thread {
	private int valor;
	private String nombre;
	public HiloContar(int n, String nombre) {
		if(n<0 || nombre == null || nombre.isBlank())
			throw new RuntimeException();
		valor = n;
		this.nombre = nombre;
	}
	@Override
	public void run() {
		for (int i = 0; i < valor; i++) {
			System.out.println(nombre+ ": "+i);
		}
	}
	
	public static void main(String[] args) {
		HiloContar h1,h2;
		(h1 = new HiloContar(100, "Hilo-1")).start();
		(h2 = new HiloContar(150, "Hilo-2")).start();
	}
}
