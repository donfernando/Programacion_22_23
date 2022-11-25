package baraja;

public class Palo {
	public final static Palo PICA=new Palo();
	public final static Palo TREBOL=new Palo();
	public final static Palo DIAMANTE=new Palo();
	public final static Palo CORAZON=new Palo();
	
	private Palo() {}
	
	@Override
	public String toString() {
		String s;
		if(this==PICA)
			s="PICA";
		else if (this==TREBOL)
			s="TREBOL";
		else if (this==DIAMANTE)
			s="DIAMANTE";
		else
			s="CORAZON";
		return s;
	}
}

