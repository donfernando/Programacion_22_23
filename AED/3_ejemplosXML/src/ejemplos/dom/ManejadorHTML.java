package ejemplos.dom;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorHTML extends DefaultHandler {

	boolean dentroDeLibro;
	boolean dentroDeAutor;
	boolean dentroDeTitulo;


	Document doc;	
	Element tabla;
	Element libro,titulo,autor;
	Element autores;
	
	public ManejadorHTML() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			doc = builder.parse(ListarLibros_ejemploBase.class.getResourceAsStream("/recursos/librosFormatoBase.html"));
			tabla = (Element)doc.getElementsByTagName("table").item(0);
		} catch (SAXException | IOException e) {
			ListarLibros.log.log(Level.SEVERE,"Error grave de enálisis del fichero XML\n"+e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		try {
			// Serialización del documento
			TransformerFactory xformFactory = TransformerFactory.newInstance();
			Transformer idTransform = xformFactory.newTransformer();
			Source input = new DOMSource(doc);
			FileWriter out =  new FileWriter("librosDOM.html");
			Result output = new StreamResult(out);
			idTransform.transform(input, output);
			out.close();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "libro":
			dentroDeLibro = true;
			libro = doc.createElement("tr");
			titulo = doc.createElement("td");			
			autores = doc.createElement("td");
			libro.appendChild(titulo);
			libro.appendChild(autores);
			tabla.appendChild(libro);
			break;
		case "autor":
			if (dentroDeLibro)
				dentroDeAutor = true;
			break;
		case "titulo":
			if (dentroDeLibro)
				dentroDeTitulo = true;
			break;
		}

	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String datos;
		datos = new String(ch, start, length).trim();
		if (datos.length() != 0)
			if (dentroDeTitulo) {				
				titulo.appendChild(doc.createTextNode(datos));
			} else if (dentroDeAutor) {
				autor = doc.createElement("p");
				autor.appendChild(doc.createTextNode(datos));
				autores.appendChild(autor);
				
			}
	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "libro":
			dentroDeLibro = false;
			break;
		case "autor":
			dentroDeAutor = false;
			break;
		case "titulo":
			dentroDeTitulo = false;
			break;
		}
	}

}