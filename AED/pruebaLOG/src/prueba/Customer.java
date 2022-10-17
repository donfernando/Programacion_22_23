package prueba;

import java.io.IOException;
import java.util.logging.*;

class EdurekaLogger {
	private final static Logger LOGGER = Logger.getLogger(EdurekaLogger.class.getName());
	private static FileHandler fh;
	static {
	       try {
	    	   fh = new FileHandler("registro%g.log", //pattern
			           10485760, //limit
			           2, // count
			           false); //append
			fh.setLevel(Level.ALL); // level
//			fh.setFormatter(new SimpleFormatter()); 
		} catch (IOException e) {
			LOGGER.warning("No se pudo crear el fichero de regostro de este log");
		}
		
	}
	
	
	public void sampleLog() {
		//LOGGER.log(Level.INFO, "Welcome to Edureka!");
		LOGGER.addHandler(fh);
		LOGGER.info("Welcome to Edureka!");
	}
}

public class Customer {
	public static void main(String[] args) throws SecurityException, IOException {
        FileHandler fh = new FileHandler("analizadorLibros_v%g.log", //pattern
            10485760, //limit
            2, // count
            false); //append
        fh.setLevel(Level.ALL); // level
       // fh.setFormatter(new XMLFormatter()); //formatter
//		LogManager slg = LogManager.getLogManager();
//		Logger log = slg.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		log.addHandler(fh);

        
        
        
		EdurekaLogger obj = new EdurekaLogger();
		obj.sampleLog();
		
		log.log(Level.WARNING, "Hi! Welcome from Edureka");
		Logger.getLogger(EdurekaLogger.class.getName()).info("ADIOS");
	}
}
