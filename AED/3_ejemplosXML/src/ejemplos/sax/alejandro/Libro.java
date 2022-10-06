package ejemplos.sax.alejandro;

import java.util.ArrayList;

public class Libro {

	String titulo;
	ArrayList<String> autores;

	public Libro() {
		autores = new ArrayList<String>();
	}

	@Override
	public String toString() {
		String sAutores = "";
		for (String i : autores) {
			sAutores += "<p>" + i + "</p>";
		}
		return "<tr>" + "<td>" + titulo + "</td>" + "<td>" + sAutores + "</td>" + "</tr>";
	}

}
