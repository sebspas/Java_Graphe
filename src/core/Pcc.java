package core ;

import java.io.* ;
import java.util.ArrayList;

import base.Arc;
import base.Label;
import base.Noeud;
import base.Readarg ;

public class Pcc extends Algo {

    // Numero des sommets origine et destination
    protected int zoneOrigine ;
    protected int origine ;

    protected int zoneDestination ;
    protected int destination ;

    public Pcc(Graphe gr, PrintStream sortie, Readarg readarg) {
        super(gr, sortie, readarg) ;

        this.zoneOrigine = gr.getZone () ;
        this.origine = readarg.lireInt ("Numero du sommet d'origine ? ") ;

        // Demander la zone et le sommet destination.
        this.zoneOrigine = gr.getZone () ;
        this.destination = readarg.lireInt ("Numero du sommet destination ? ");
    }

    public void run() {

        System.out.println("Run PCC de " + zoneOrigine + ":" + origine + " vers " + zoneDestination + ":" + destination) ;

        // A vous d'implementer la recherche de plus court chemin.

        // Initialisation
        Label origine = new Label(this.graphe.getNoeudGraphe(this.origine));
        origine.setCout(0);
        origine.setPere(null);

        BinaryHeap<Label> tas = new BinaryHeap<>();
        tas.insert(origine);

        // Tableau sommet déjà traité
        ArrayList<Label> tabLabelTraite = new ArrayList<>();

        while(!tas.isEmpty()) {

            // on récupère le plus petit label
            Label current = tas.findMin();
            //System.out.println(current.toString());
            // on récupére les successeurs du plus petit label
            ArrayList<Noeud> tabSuccesseurs = current.getNoeud().getSuccesseurs();

            // on récupére tout les arc du sommet en cours
            ArrayList<Arc> tabArcn = current.getNoeud().getTabArc();

            // on l'enlève de la pile
            tas.deleteMin();

            for (Noeud n: tabSuccesseurs) {

                Label newLabel = new Label(n);

                boolean marque = false;
                boolean exist = false;
                for (Label l: tabLabelTraite) {
                    if (l.getNoeud() == n && l.isMarquage()) {
                        marque = true;
                    }
                    if (l.getNoeud() == n) {
                        exist = true;
                        newLabel = l;
                    }
                }

                // si le label n'a pas déjà été traité
                if (!marque) {
                    if (exist) {
                        // on definit le pere
                        newLabel.setPere(current.getNoeud());

                        // on parcours les arc en cherchant la plus courte distance vers le sommet que l'on traite (sucesseur)
                        int distance = newLabel.getCout();

                        for(Arc a: tabArcn) {
                            // noeud uniquelent vers arc sortant
                            if (a.getDestination() == n &&  (a.getLongueur()+current.getCout()) < newLabel.getCout()) {
                                    distance = a.getLongueur()+current.getCout();
                            }
                        }
                        newLabel.setCout(distance);

                    } else {
                        // on definit le pere
                        newLabel.setPere(current.getNoeud());

                        // on parcours les arc en cherchant la plus courte distance vers le sommet que l'on traite (sucesseur)
                        int distance = Integer.MAX_VALUE;

                        for(Arc a: tabArcn) {
                            // noeud uniquelent vers arc sortant
                            if (a.getDestination() == n &&  (a.getLongueur()+current.getCout()) < newLabel.getCout()) {
                                    distance = (a.getLongueur()+current.getCout());
                            }
                        }
                        newLabel.setCout(distance);
                        // on l'ajoute au tas
                        tas.insert(newLabel);
                    }
                }
            }

            // on marque le noeud
            current.setMarquage(true);

            // on ajoute le sommet traité
            tabLabelTraite.add(current);

            if (current.getNoeud().getNumero() == this.destination) {
                System.out.println(current.getCout());
                break;
            }
        }


    }

}
