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
	public static int nJugadores=-1;
	
	
	public static void main(String[] args) {
		Jugador jugador;
		Scanner in = new Scanner(System.in);
		ArrayList<Jugador> jugadores = new ArrayList<>();
		Mazo mazo;
		System.out.println("___JUEGO DEL BURRO___");
		System.out.print("Cuántos jugadores: ");
		nJugadores = in.nextInt();
		mazo = new Mazo(nJugadores);
		mazo.barajar();
		CajaDeCarta[] cajas = new CajaDeCarta[nJugadores];
		for (int i = 0; i < nJugadores; i++) {
			cajas[i]= new CajaDeCarta();
		}
		BarreraCiclica barrera = new BarreraCiclica(nJugadores);
		MiPila<Jugador> pilaDeManos = new MiPila<>();
		
		for (int i = 0; i < nJugadores; i++) {
			Mano mano = new Mano(mazo);
			jugadores.add(new Jugador("Jug_"+i, mano, cajas[i], cajas[(i+1)%nJugadores], barrera, pilaDeManos));
		}
		for (int i = 0; i < nJugadores; i++) {
			jugadores.get(i).start();
		}
		try {
			for (int i = 0; i < nJugadores; i++) {
				jugadores.get(i).esperoQueTermines();
			}
			barrera.anularBarrera();
			

			jugador = pilaDeManos.pop();
			System.out.println("Perdedor:  "+jugador+" ["+jugador.getMiMano()+"]");
			for (int i = 1; i < nJugadores-1; i++) { 
				jugador = pilaDeManos.pop();
				System.out.println("Salvados:  "+jugador+" ["+jugador.getMiMano()+"]");
			}
			jugador = pilaDeManos.pop();
			System.out.println("Ganador:  "+jugador+" ["+jugador.getMiMano()+"]");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		in.close();
	}

}
