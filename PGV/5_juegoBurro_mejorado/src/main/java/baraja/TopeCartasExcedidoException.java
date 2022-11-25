package baraja;

public class TopeCartasExcedidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public TopeCartasExcedidoException() {
	}
	public TopeCartasExcedidoException(String message) {
		super(message);
	}
}
