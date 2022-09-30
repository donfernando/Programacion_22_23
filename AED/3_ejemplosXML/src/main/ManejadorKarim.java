package main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorKarim extends DefaultHandler {

	private boolean esPrecio = false;
	private boolean esNombre = false;
	private boolean esProducto = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("productos"))
			System.out.println("Productos:");
		else if (qName.equals("producto"))
			esProducto = true;
		else if (qName.equals("precio"))
			esPrecio = true;
		else if (qName.equals("nombre") && esProducto)
			esNombre = true;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (esPrecio || esNombre) {
			System.out.print(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (esNombre) {
			System.out.print(" = ");
			esNombre = false;
		} else if (esPrecio) {
			System.out.println("€.");
			esPrecio = false;
		} else if (esProducto) {
			esProducto = false;
		}
	}

}
