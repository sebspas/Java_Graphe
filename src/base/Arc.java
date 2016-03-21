package base;

public class Arc {
	
	private Noeud Destination;
	
	private Descripteur desc;

	public Arc(Noeud destination, Descripteur desc) {
		Destination = destination;
		this.desc = desc;
	}
}
