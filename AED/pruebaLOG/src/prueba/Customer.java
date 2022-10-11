package prueba;

import java.io.IOException;
import java.util.logging.*;

class EdurekaLogger {
	private final static Logger LOGGER = Logger.getLogger("miClase");

	public void sampleLog() {
		// LOGGER.log(Level.WARNING, "Welcome to Edureka!");
		LOGGER.warning("Welcome to Edureka!");
	}
}

public class Customer {
	public static void main(String[] args) throws SecurityException, IOException {
        FileHandler fh = new FileHandler("analizador%gLibros.log", //pattern
            10485760, //limit
            2, // count
            false); //append
        fh.setLevel(Level.ALL); // level
       // fh.setFormatter(new XMLFormatter()); //formatter
		
		
		
		EdurekaLogger obj = new EdurekaLogger();
		obj.sampleLog();
		
		LogManager slg = LogManager.getLogManager();
		Logger log = slg.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.addHandler(fh);
		log.log(Level.WARNING, "Hi! Welcome from Edureka");
	}
}
