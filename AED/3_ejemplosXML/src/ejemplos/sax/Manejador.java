package ejemplos.sax;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
	String prefijo ="";
   @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	   System.out.println(prefijo+qName);
	   prefijo += "  ";
  }

//   @Override
//   public void characters(char[] ch, int start, int length) throws SAXException {
//	   String s=new String(ch,start,length).trim();
//	   if(s.length()!=0)
//		   System.out.println(prefijo+s);
//   }

   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
	   prefijo = prefijo.substring(2);
   }

}