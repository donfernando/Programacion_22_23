package ejemplos.sax.alejandro;


import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ListarLibros {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		ManejadorHTML miManejador = new ManejadorHTML();
		saxParser.parse(ListarLibros.class.getResourceAsStream("/recursos/libros.xml"), miManejador);
		System.out.println("------------------------");
		
	}
}
