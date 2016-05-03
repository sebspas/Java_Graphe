package base;

public class LabelStar extends Label {
	
	private int coutEstime;

	public LabelStar(Noeud noeud) {
		super(noeud);
		this.coutEstime = Integer.MAX_VALUE;
	}
	
	public int getCoutEstime(){return this.coutEstime;}

}
