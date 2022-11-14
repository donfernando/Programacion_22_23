package baraja;

public class Carta {
	private static String[] nombreCarta = {
			"AS","DOS","TRES","CUATRO","CINCO", "SEIS","SIETE","*","*","J","Q","K"};
	private int valor;
	private Palo palo;
	public Carta(int valor, Palo palo) {
		this.valor = valor;
		this.palo = palo;
	}

	public int getValor() {
		return valor;
	}

	public Palo getPalo() {
		return palo;
	}
	
	@Override
	public boolean equals(Object obj) {
		return getValor()==((Carta)obj).getValor();
	}
	
	
	@Override
	public String toString() {
		return nombreCarta[valor-1]+" de "+palo;
	}
}
