package baraja;

import java.util.ArrayList;

public class Mano {
	protected Mazo mazo;
	protected ArrayList<Carta> cartas;
	private String nombre;
	private static final int TOPE = 4;
	public Mano(Mazo m, String jugador) {		
		this(m, jugador, 10);
	}
	public Mano(Mazo m, String jugador, int numCartas) {
		mazo = m;
		nombre = jugador;
		cartas = new ArrayList<>(numCartas);
	}
	public void descartaTodas() {
		cartas.clear();
	}
	public void robaCarta() {
		if(cartas.size()==TOPE)
			throw new TopeCartasExcedidoException("Se admiten un máximo de "+TOPE+" cartas en la mano");
		cartas.add(mazo.daCarta());
	}
	public int getNumeroCartas() {
		return cartas.size();
	}
	public double cuentaPuntos() {
		// Al no saber qué poner,
		// pongo esto por que algo hay que retornar.
		return -1.0;
	}

	public String toString() {
		return String.format("\nMano de %s:\n"+cartas,nombre);
	}
	
	public String getNombre() {
		return nombre;
	}
}
