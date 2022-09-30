package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;


public class Cuestionario {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties idioma = new Properties();
		FileReader fProp;
		
		fProp = new FileReader("./bin/recursos/_ES.properties",Charset.forName("UTF8"));
		// o bien   fProp = new FileReader("./src/recursos/_EN.properties",Charset.forName("UTF8"));

		idioma.load(fProp);
		System.out.println(idioma.getProperty("nombre",""));
	}
}
