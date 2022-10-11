package ejemplo1;

public class HiloContarRunnable implements Runnable {
	private static int x = 0;
	private String nombre;
	public HiloContarRunnable(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public void run() {
		// Región crítica
		while (++x <= 50) {
			System.out.println(nombre + " " + x + " ");
		}
		// 
	}
	
	public static void main(String[] args) {
		HiloContarRunnable run1 = new HiloContarRunnable("Jozef");
		HiloContarRunnable run2 = new HiloContarRunnable("            David");
		
		Thread hilo1 = new Thread(run1);
		Thread hilo2 = new Thread(run2);
		
		hilo1.start();
		hilo2.start(); 		
	}
	
}