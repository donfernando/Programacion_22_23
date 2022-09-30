package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Properties;


public class Cuestionario_getResource {

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		Properties idioma = new Properties();
		FileReader fProp;
		File file;
		
		URI uri = Cuestionario_getResource.class.getResource("/recursos/_ES.properties").toURI();
		
		// TODO ver que valor tiene la URI
		System.out.println(uri);
		file = new File(uri);
		// o bien file = new File(Cuestionario_getResource.class.getResource("/recursos/_EN.properties").toURI());

		fProp = new FileReader(file,Charset.forName("UTF8"));		

		idioma.load(fProp);
		System.out.println(idioma.getProperty("nombre",""));
	}
}
