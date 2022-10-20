package ejemplo1;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Main {
	
	static int caja;
	static int total = 0;
	static Object o = new Object();
	
	static Logger LOG = Logger.getLogger("global");
	static {
		try {
			FileHandler fh = new FileHandler();
			LOG.addHandler(fh);
		} catch (SecurityException | IOException e) {
			LOG.warning("Error asociando file handle al LOG.\n"+e.getMessage());
		}
	}
	public static void main(String[] args) {
		Productor p = new Productor("Productor");
		Consumidor c = new Consumidor("Consumidor");
		
		p.start();
		c.start();
		
	}

} 
