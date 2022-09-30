package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
//import java.nio.charset.Charset;
import java.util.Properties;


public class Cuestionario_getResourceAsStream {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		Properties idioma = new Properties();

//		InputStream fProp = Cuestionario_getResourceAsStream.class.getResourceAsStream("/recursos/_ES.properties");
		Reader fProp = new InputStreamReader(Cuestionario_getResourceAsStream.class.getResourceAsStream("/recursos/_ES.properties"),Charset.forName("UTF8"));

		idioma.load(fProp);
		System.out.println(idioma.getProperty("nombre",""));
	}
}
