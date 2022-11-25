package baraja;

import java.util.ArrayList;
import java.util.Random;

public class Mazo {
	private ArrayList<Carta> mazo = new ArrayList<>();
	private static Random alea = new Random();
	public Mazo(int jugadores) {
		if(jugadores<3 || jugadores>10)
			throw new NumJugadoresIlegalException("Los jugadores deben ser entre 3 y 10.");
		int num = 1;
		while(jugadores>0 & num<=7) {
			mazo.add(new Carta(num,Palo.PICA));
			mazo.add(new Carta(num,Palo.TREBOL));
			mazo.add(new Carta(num,Palo.DIAMANTE));
			mazo.add(new Carta(num,Palo.CORAZON));
			jugadores--;
			num++;
		}
		num=10;
		while(jugadores>0) {
			mazo.add(new Carta(num,Palo.PICA));
			mazo.add(new Carta(num,Palo.TREBOL));
			mazo.add(new Carta(num,Palo.DIAMANTE));
			mazo.add(new Carta(num,Palo.CORAZON));
			jugadores--;
			num++;
		}
	}
	public void barajar() {
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