package principal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import baraja.Mano;
import baraja.Mazo;
import sincronizacion.BarreraCiclica;
import sincronizacion.CajaDeCarta;
import sincronizacion.Jugador;
import sincronizacion.MiPila;

public class Main {
	public static Semaphore queriendoGanar = new Semaphore(0);
	public static int nJugadores = -1;
	public static BarreraCiclica barrera;
	public static MiPila<Jugador> pilaDeManos;

	public static void main(String[] args) {
		Jugador jugador;
		Scanner in = new Scanner(System.in);
		ArrayList<Jugador> jugadores = new ArrayList<>();
		Mazo mazo;
		System.out.println("___JUEGO DEL BURRO___");
		System.out.print("Cuántos jugadores: ");
		nJugadores = in.nextInt();
		in.nextLine(); // Limpia el retorno del buffer.
		mazo = new Mazo(nJugadores);
		mazo.barajar();
		CajaDeCarta[] cajas = new CajaDeCarta[nJugadores];
		for (int i = 0; i < nJugadores; i++) {
			cajas[i] = new CajaDeCarta();
		}
		barrera = new BarreraCiclica(nJugadores);
		pilaDeManos = new MiPila<>();

		for (int i = 0; i < nJugadores; i++) {
			Mano mano = new Mano(mazo);
			System.out.print("Jugador "+(i+1)+": ");
			jugadores.add(new Jugador(in.nextLine(), mano, cajas[i], cajas[(i + 1) % nJugadores]));
		}
		in.close();
		for (int i = 0; i < nJugadores; i++) {
			jugadores.get(i).start();
		}
		try {
			for (int i = 0; i < nJugadores; i++) {
				jugadores.get(i).join();
			}
			
			//Final de la partida. Mostrando Resultados.
			jugador = pilaDeManos.pop();
			System.out.println("Perdedor: " + jugador + " [" + jugador.getMiMano() + "]");
			for (int i = 1; i < nJugadores - 1; i++) {
				jugador = pilaDeManos.pop();
				System.out.println("Salvados: " + jugador + " [" + jugador.getMiMano() + "]");
			}
			jugador = pilaDeManos.pop();
			System.out.println("Ganador:  " + jugador + " [" + jugador.getMiMano() + "]");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
