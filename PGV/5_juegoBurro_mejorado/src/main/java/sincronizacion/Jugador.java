package sincronizacion;

import baraja.Mano;
import principal.Main;

public class Jugador extends Thread {

	private Mano miMano;
	private CajaDeCarta cSoltar, cCoger;

	public Jugador(String name, Mano m, CajaDeCarta paraCogerCarta, CajaDeCarta paraDejarCarta) {
		super(name);
		miMano = m;
		cSoltar = paraDejarCarta;
		cCoger = paraCogerCarta;
	}

	@Override
	public void run() {
		try {
			while (Main.pilaDeManos.isEmpty() && !miMano.cuatroIguales()) {
				System.err.println(getName() + " [" + miMano + "]");
				Main.barrera.fase1();
				if (Main.pilaDeManos.isEmpty()) {
					cSoltar.setDato(miMano.soltarCarta());
					Main.barrera.fase2();
					miMano.reponerCarta(cCoger.getDato());
					// TODO Espera opcional.
					sleep(500);
				}
			}
			Main.pilaDeManos.push(this);
			Main.barrera.anularBarrera();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return getName();
	}

	public Mano getMiMano() {
		return miMano;
	}
}
