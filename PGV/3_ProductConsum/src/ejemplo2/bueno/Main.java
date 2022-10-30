package ejemplo2.bueno;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
	
	static Caja caja = new Caja();
	static Logger LOG = Logger.getLogger("global");
	static {
		try {
			FileHandler fh = new FileHandler("registro.log",true);
			LOG.addHandler(fh);
		} catch (SecurityException | IOException e) {
			LOG.warning("Error asociando file handle al LOG.\n"+e.getMessage());
		}
	}	
	public static void main(String[] args) {
		Productor p = new Productor("Productor");
		Consumidor c = new Consumidor("Consumidor");
		LOG.info("Arrancando hilos");
		p.start();
		c.start();
		
		
		
		
		try {
			p.join();
			c.join();
			LOG.info("Terminados hilos");
		} catch (InterruptedException e) {
			LOG.warning("Uff... no pude esperar.");			
		}
		
	}

} 
