package base;

public class Arc {
	
	private Noeud Destination;
	
	private Descripteur desc;

	private int longueur;

	public Noeud getDestination() {
		return Destination;
	}

	public int getLongueur() {

		return longueur;
	}

	public Descripteur getDesc() {
		return desc;
	}

	public Arc(Noeud destination, Descripteur desc, int longueur) {
		Destination = destination;
		this.desc = desc;
		this.longueur = longueur;
	}
}
