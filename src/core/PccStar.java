package core ;

import java.io.* ;

import base.Arc;
import base.Label;
import base.Readarg ;

public class PccStar extends Pcc {

    public PccStar(Graphe gr, PrintStream sortie, Readarg readarg) {
	super(gr, sortie, readarg) ;
    }

    public void run() {

	System.out.println("Run PCC-Star de " + zoneOrigine + ":" + origine + " vers " + zoneDestination + ":" + destination) ;

	// A vous d'implementer la recherche de plus court chemin A*
    }

    public int compte(Label l,Arc a ){
    	return l.getCout()+a.getLongueur();
    }
}
