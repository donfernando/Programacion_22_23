package ejemplos.dom;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.SAXException;

public class ListarLibros {
	public static void main(String[] args)
			throws TransformerException, ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(ListarLibros.class.getResourceAsStream("/recursos/librosFormatoBase.html"));
		FileWriter out; 
		
		Element html = doc.getDocumentElement();
		
		NodeList nodos = html.getElementsByTagName("table");
		Element tabla = (Element) nodos.item(0);
		
		Element filaLibro = doc.createElement("tr");
		Element celdaTitulo = doc.createElement("td"); 
		Element celdaAutores = doc.createElement("td");
		
		celdaTitulo.appendChild(doc.createTextNode("bla bla bla"));	
		Element autor = doc.createElement("p");
		autor.appendChild(doc.createTextNode("tururú"));
		celdaAutores.appendChild(autor);
		autor = doc.createElement("p");
		autor.appendChild(doc.createTextNode("tararí"));
		celdaAutores.appendChild(autor);

		filaLibro.appendChild(celdaTitulo);
		filaLibro.appendChild(celdaAutores);
		
		tabla.appendChild(filaLibro);


		// Serialización del documento
		TransformerFactory xformFactory = TransformerFactory.newInstance();
		Transformer idTransform = xformFactory.newTransformer();
		Source input = new DOMSource(doc);
		out =  new FileWriter("librosDOM.html");
		Result output = new StreamResult(out);
		idTransform.transform(input, output);
		out.close();
	}
}
