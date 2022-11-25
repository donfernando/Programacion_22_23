package contenedores;

import java.util.LinkedList;

public class Cola<T> {

	private LinkedList<T> datos=new LinkedList<>();
	private final int TAM_MAX;
	public Cola(int tam) {
		TAM_MAX = tam;
	}
	public void add(T x) {
		if(datos.size()==TAM_MAX)
			throw new RuntimeException("No caben más datos");
		datos.add(x);
	}
	public T poll() {
		T aux;
		if(datos.isEmpty())
			throw new RuntimeException("No hay ningún dato");
		aux = datos.remove(0);
		return aux;
	}
}
