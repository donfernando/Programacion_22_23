package prueba;

public class Cliente {
	private String nombre;
	private String fIni,fFin;
	public Cliente(String nombre, String fIni, String fFin) {
		this.nombre = nombre;
		this.fIni = fIni;
		this.fFin = fFin;
	}
	@Override
	public String toString() {
		return String.format("%s - Fecha inicio: %s - Fecha salida: %s",nombre,fIni,fFin);
	}
	
}
