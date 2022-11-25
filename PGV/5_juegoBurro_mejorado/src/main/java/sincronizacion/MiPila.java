package sincronizacion;

import java.util.ArrayList;

public class MiPila<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1L;
	public synchronized void push(T dato) {
		if(!contains(dato))
			add(dato);
	}
	public T pop() {
		return remove(size()-1);
	}
}
