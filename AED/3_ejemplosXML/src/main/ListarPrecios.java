package main;


import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ListarPrecios {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		Manejador miManejador = new Manejador();
		saxParser.parse("src/recursos/productos.xml", miManejador);
		System.out.println("------------------------");
		ManejadorKarim otroManejador = new ManejadorKarim();
		saxParser.parse("src/recursos/productos.xml", otroManejador);
		
	}
}
