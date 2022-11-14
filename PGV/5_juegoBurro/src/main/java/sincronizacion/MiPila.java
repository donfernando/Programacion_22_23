package sincronizacion;

import java.util.Vector;

public class MiPila<T> extends Vector<T> {
	private static final long serialVersionUID = 1L;
	public synchronized void push(T dato) {
		if(!contains(dato))
			add(dato);
	}
	public T pop() {
		return remove(size()-1);
	}
}
