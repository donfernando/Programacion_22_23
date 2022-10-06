package ejemplos.sax.alejandro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorHTML extends DefaultHandler {

	boolean enLibro, enTitulo, enAutor;
	String titulo = "";
	String autor = "";
	ArrayList<Libro> libros = new ArrayList<>();

	final File HTML_FILE = new File("libros.html");
	final String HTML_BEFORE = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Page Title</title>\r\n"
			+ "</head>\r\n" + "<body>\r\n" + "<table border=1>\r\n" + "<tr>\r\n" + "<th>Titulo</th>\r\n"
			+ "<th>Autores</th>\r\n" + "</tr>\r\n";
	final String HTML_AFTER = "</body>\r\n" + "</html>";

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("titulo") && enLibro) {
			enTitulo = true;
		}
		if (qName.equals("autor") && enLibro) {
			enAutor = true;
		} else if (qName.equals("libro")) {
			enLibro = true;
			libros.add(new Libro());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (enTitulo) {
			titulo  = new String(ch,start,length);
		} else if (enAutor) {
			autor  = new String(ch,start,length);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("titulo") & enTitulo) {
			enTitulo = false;
			libros.get(libros.size() - 1).titulo = titulo;
		}
		if (qName.equals("autor") & enAutor) {
			enAutor = false;
			libros.get(libros.size() - 1).autores.add(autor);
		} else if (qName.equals("libro")) {
			enLibro = false;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		writeHTML();
	}

	private void writeHTML() {
		try {
			FileWriter writter = new FileWriter(HTML_FILE);
			writter.write(HTML_BEFORE);
			for (Libro i : libros) {
				writter.write(i.toString());
			}
			writter.write(HTML_AFTER);

			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
