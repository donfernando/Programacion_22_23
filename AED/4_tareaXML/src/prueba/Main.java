package prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	public static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static {
		try {
			FileHandler fh = new FileHandler("ActualizacionHabitaciones.log", 10485760, 2, false);
			fh.setFormatter(new SimpleFormatter());
			log.addHandler(fh);
		} catch (IOException e) {
			log.log(Level.SEVERE, "No se pueden crear los manejadores de Log.");
		}
	}

	public static void main(String[] args) {
		int nHabitacion;
		Document doc;
		Element habitacionElement;
		NodeList habitaciones, hijos, clientes;
		Double precioHabitacion;
		String codHotel;
		try {
			Main.log.log(Level.INFO, "Inicio de proceso");
//			Scanner teclado = new Scanner(System.in);
			DocumentBuilderFactory factoryDoc = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factoryDoc.newDocumentBuilder();
			doc = builder.parse(Main.class.getResourceAsStream("/resources/habitaciones.xml"));

			habitaciones = doc.getElementsByTagName("Habitacion");

			for (int i = 0; i < habitaciones.getLength(); i++) {
				habitacionElement = (Element) habitaciones.item(i);
				nHabitacion = Integer.parseInt(habitacionElement.getAttribute("numHabitacion"));
				precioHabitacion = Double.parseDouble(habitacionElement.getAttribute("preciodia"));

				hijos = habitacionElement.getChildNodes();
				codHotel = hijos.item(1).getTextContent();

				clientes = hijos.item(3).getChildNodes();
				// TODO

				System.out.printf("%s - Hab.%d - Precio/dia: %.2f €\n", codHotel, nHabitacion, precioHabitacion);

				for (int j = 1; j < clientes.getLength(); j++) {
					if (clientes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Element cliente = (Element) clientes.item(j);
						Cliente aux = new Cliente(cliente.getTextContent().trim(),
								cliente.getAttribute("fechaInicio"),
								cliente.getAttribute("fechaFin"));
						System.out.println("* "+ aux);
					}

//					System.out.println("- Pagado (Si/No): ");
//					String pagado = teclado.nextLine();
//
//					Element clienteElement = doc.createElement("Cliente");
//					clienteElement.setAttribute("fechaInicio", aux.fechaInicio);
//					clienteElement.setAttribute("fechaFin", aux.fechaFin);
//
//					if (pagado.equalsIgnoreCase("Si")) {
//
//						Main.log.log(Level.INFO, "Realizado Pago: " + aux.nombre);
//
//						System.out.println("- Archivar cliente (Si/No): ");
//						String archivado = teclado.nextLine();
//						clienteElement.setAttribute("pagado", "pagado");
//
//						if (archivado.equalsIgnoreCase("Si")) {
//
//							Main.log.log(Level.INFO, "Cliente Archivado");
//
//							archivadosElement.appendChild(clienteElement);
//						} else {
//							clientesElement.appendChild(clienteElement);
//						}
//					} else {
//						clientesElement.appendChild(clienteElement);
//					}
//				}
//
//				habitacionElement.appendChild(clientesElement);
//				habitacionElement.appendChild(archivadosElement);
//
//				rootElement.appendChild(habitacionElement);
//
				}

//			teclado.close();
//
//			FileOutputStream output = new FileOutputStream("Habitaciones.xml");
//
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			DOMSource source = new DOMSource(doc);
//			StreamResult result = new StreamResult(output);
//			transformer.transform(source, result);
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			Main.log.log(Level.SEVERE, "Error grave de análisis del fichero XML\n" + e.getMessage());
			e.printStackTrace();
//		} catch (TransformerConfigurationException e) {
//			Main.log.log(Level.SEVERE, "Error grave al transformar la configuracion fichero XML\n" + e.getMessage());
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			Main.log.log(Level.SEVERE, "Error grave al transformar\n" + e.getMessage());
//			e.printStackTrace();
		}
		Main.log.log(Level.INFO, " Actualización completada");
	}

}