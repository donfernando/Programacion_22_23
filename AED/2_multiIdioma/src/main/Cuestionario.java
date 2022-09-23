package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Properties;

import idiomas.NProperties;

public class Cuestionario {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties idioma = new Properties();
		
		String clave;
		idioma.load(new FileReader("./src/idiomas/_ES.properties",Charset.forName("UTF8")));
		//idioma.load(new FileReader("./src/idiomas/_EN.properties"));
		
		System.out.println(idioma.getProperty("nombre",""));
	}

}