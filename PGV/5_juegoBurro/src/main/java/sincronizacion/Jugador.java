package sincronizacion;

import baraja.Mano;
import principal.Main;

public class Jugador extends Thread {

	private Mano miMano;
	private CajaDeCarta cSoltar, cCoger;
	private BarreraCiclica aLaVez;
	private MiPila<Jugador> pilaDeManos;
	private Thread miOreja;

	public Jugador(String name, Mano m, CajaDeCarta paraCogerCarta, CajaDeCarta paraDejarCarta, BarreraCiclica aLaVez,
			MiPila<Jugador> pilaDeManos) {
		super(name);
		miMano = m;
		cSoltar = paraDejarCarta;
		cCoger = paraCogerCarta;
		this.aLaVez = aLaVez;
		this.pilaDeManos = pilaDeManos;
		(miOreja = new Thread() {
			@Override
			public void run() {
				try {
					Main.queriendoGanar.acquire();
					pilaDeManos.push(Jugador.this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

	public void esperoQueTermines() throws InterruptedException {
		miOreja.join();
	}

	@Override
	public void run() {
		try {
			while (pilaDeManos.isEmpty() && !miMano.cuatroIguales()) {
				System.err.println(getName() + " [" + miMano + "]");
				aLaVez.fase1();
				if (pilaDeManos.isEmpty()) {
					cSoltar.setDato(miMano.soltarCarta());
					aLaVez.fase2();
					miMano.reponerCarta(cCoger.getDato());
					// TODO Espera opcional.
					sleep(500);
				}
			}
			if (pilaDeManos.isEmpty()) {
				pilaDeManos.push(this);
				Main.queriendoGanar.release(Main.nJugadores);
			}
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
