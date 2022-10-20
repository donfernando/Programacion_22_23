package ejemplo3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	
	static Caja caja = new Caja();
	static Logger LOG = Logger.getLogger("global");
	static {
		try {
			FileHandler fh = new FileHandler ("registro.log",true);
			fh.setFormatter(new SimpleFormatter());
			LOG.addHandler(fh);
		} catch (SecurityException | IOException e) {
			LOG.warning("Error asociando file handle al LOG.\n"+e.getMessage());
		}
	}	
	public static void main(String[] args) throws InterruptedException {
		ArrayList<Thread> productores = new ArrayList<>();
		LOG.info("Arrancando hilos");
		
		productores.add(new Productor("Prod A  *******"));
		productores.add(new Productor("Prod B  *******"));
		productores.add(new Productor("Prod C  *******"));
		productores.add(new Productor("Prod D  *******"));
		new Consumidor("Consum 1  oooooooooo").start();
		new Consumidor("Consum 2  oooooooooo").start();
		for (Thread productor : productores) {
			productor.start();
		}
		for (Thread productor : productores) {
			productor.join();
		}
		Main.caja.setDato(99);
		Main.caja.setDato(99);
	}
} 
