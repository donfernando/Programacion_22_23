package baraja;

import java.util.ArrayList;
import java.util.Random;

public class Mazo {
	private ArrayList<Carta> mazo = new ArrayList<>();
	private static Random alea = new Random();
	public Mazo() {
		for (int num = 1; num <= 7; num++) {
			mazo.add(new Carta(num,Palo.PICA));
			mazo.add(new Carta(num,Palo.TREBOL));
			mazo.add(new Carta(num,Palo.DIAMANTE));
			mazo.add(new Carta(num,Palo.CORAZON));
		}
		for (int num = 10; num <= 12; num++) {
			mazo.add(new Carta(num,Palo.PICA));
			mazo.add(new Carta(num,Palo.TREBOL));
			mazo.add(new Carta(num,Palo.DIAMANTE));
			mazo.add(new Carta(num,Palo.CORAZON));
		}
	}
	public void baraja() {
		int posAzar;
		for (int num = 0; num < mazo.size(); num++) {
			Carta aux = mazo.get(num);
			posAzar = alea.nextInt(mazo.size());
			mazo.set(num, mazo.get(posAzar));
			mazo.set(posAzar, aux);			
		}
	}
	public int getNumeroCartas() {
		return mazo.size();
	}
	public Carta daCarta() {
		return mazo.remove(mazo.size()-1);
	}
}