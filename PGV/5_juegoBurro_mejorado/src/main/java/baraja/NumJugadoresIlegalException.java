package baraja;

public class NumJugadoresIlegalException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NumJugadoresIlegalException() {
	}
	public NumJugadoresIlegalException(String message) {
		super(message);
	}
}
