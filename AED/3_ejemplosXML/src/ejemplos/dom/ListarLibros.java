package ejemplos.dom;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ListarLibros {
	public static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static {
		try {
			FileHandler fh = new FileHandler("parserLibrosSAX_%g.log", 10485760, 2, false);
			FileHandler fhXML = new FileHandler("parserLibrosSAX_%g.log.xml", 10485760, 2, false);
			fh.setFormatter(new SimpleFormatter());
			log.addHandler(fh);
			log.addHandler(fhXML);
		} catch (IOException e) {
			log.log(Level.SEVERE,"No se pueden crear los manejadores de Log.");
		}
	}

	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			ManejadorHTML miManejador = new ManejadorHTML();
			saxParser.parse(ListarLibros.class.getResourceAsStream("/recursos/libros.xml"), miManejador);
			System.out.println("Finalizado el parser...");
		} catch (SAXException | IOException | ParserConfigurationException e) {
			log.log(Level.WARNING, e.getMessage());
		}

	}
}
