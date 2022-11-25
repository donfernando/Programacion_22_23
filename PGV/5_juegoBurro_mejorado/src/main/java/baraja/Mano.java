package baraja;

import java.util.ArrayList;

public class Mano {
	protected ArrayList<Carta> cartas = new ArrayList<>();
	private static final int CARTAS_POR_JUGADOR = 4;

	public Mano(Mazo m) {
		for (int i = 0; i < CARTAS_POR_JUGADOR; i++) {
			cartas.add(m.daCarta());
		}
	}

	public int getNumeroCartas() {
		return cartas.size();
	}

	public String toString() {
		String r = "";
		for (int i = 0; i < cartas.size(); i++) {
			r += cartas.get(i) + "\t ";
		}
		return r;
	}

	public boolean cuatroIguales() {
		return vecesRepetida(cartas.get(0))==4;
	}

	public void reponerCarta(Carta carta) {
		if (cartas.size() == CARTAS_POR_JUGADOR)
			throw new TopeCartasExcedidoException("Solo se admiten " + CARTAS_POR_JUGADOR + " cartas en la mano");
		cartas.add(carta);
	}

	public Carta soltarCarta() {
		int posCarta = CARTAS_POR_JUGADOR - 1;
		while (posCarta >= 0 && vecesRepetida(cartas.get(posCarta)) != 1)
			posCarta--;
		if (posCarta == -1) {
			// ninguna carta sin repetir (2+2)
			posCarta = 0;
		}
		return cartas.remove(posCarta);
	}

	private int vecesRepetida(Carta buscada) {
		int veces = 0;
		for (Carta carta : cartas) {
			if (carta.equals(buscada))
				veces++;
		}
		return veces;
	}
}
