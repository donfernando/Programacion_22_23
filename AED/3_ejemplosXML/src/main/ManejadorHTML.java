package main;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorHTML extends DefaultHandler {
//	StringWriter sw = new StringWriter();
//	PrintWriter out = new PrintWriter(sw);
	// TODO prueba
	PrintStream out = System.out;
	
	boolean dentroDeLibro;
	boolean dentroDeAutor;
	boolean dentroDeTitulo;
	// numero de autores ya leidos de cada libro.
	int numAutores;
	
	@Override
	public void startDocument() throws SAXException {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("	<META http-equiv=Content-Type content=\"text/html; " + "charset=iso-8859-1\">");
		out.println("		<title>Documento HTML</title>");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<table>");
		out.println("		   <tr><th style='text-align: center'>TITULO</th>");
		out.println("		       <th style='text-align: center'>AUTOR</th></tr>");
	}
	@Override
	public void endDocument() throws SAXException {
		out.println("		</table>");
		out.println("	</body>");
		out.println("</html>");
		out.close();
		//sw.toString();
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "libro":
			dentroDeLibro=true;
			numAutores=0;
			out.println("<tr>");
			break;
		case "autor":
			numAutores++;
			dentroDeAutor=true;
			if(numAutores==1)
				out.println("<td>");
			break;
		case "titulo":
			dentroDeTitulo=true;
			break;
		}
		
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String datos;
		if(dentroDeLibro) {
			datos = new String(ch, start, length);
			if(dentroDeTitulo) {
				out.println("<td>"+datos+"</td>");
			}
			else if(dentroDeAutor) {
				out.println("<p>"+datos+"</p>");	
			}
		}
	}

	public void endElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "libro":
			dentroDeLibro=false;
			out.println("</td></tr>");			
			break;
		case "autor":
			dentroDeAutor=false;
			break;
		case "titulo":
			dentroDeTitulo=false;			
			break;
		}
	}

}