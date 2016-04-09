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
        origine.setMarquage(false);
        origine.setPere(null);
        // initalisation tas
        BinaryHeap<Label> tas = new BinaryHeap<>();
        tas.insert(origine); // on met l'origine dans le tas
        // initialisation des sommets parcouru
        ArrayList<Label> sommet = new ArrayList<>();

        // algo
        // tant qu'il existe des sommets non marqué
        while(!tas.isEmpty()) {
            // x <- ExtractMin(tas)
            Label x = tas.findMin();
            // on marque le sommet
            x.setMarquage(true);
            // on l'ajoute au sommet visité
            sommet.add(x);
            // on le sort du tas
            tas.deleteMin();

            // on récupére les arcs qui partent de ce sommet
            ArrayList<Arc> tabArcx = x.getNoeud().getTabArc();

            // pour tout les successeurs de x
            for (Noeud n: x.getNoeud().getSuccesseurs()) {
                // on crée un label pour le sommet
                Label y = new Label(n);
                y.setMarquage(false); // on le definit comme non marqué
                y.setPere(x.getNoeud()); // on met a jour le pere
                y.setCout(Integer.MAX_VALUE); // cout a l'infini par défaut
                // si le successeurs n'est pas déjà marqué
                boolean marque = false;
                boolean exist = false;
                for (Label l: sommet) {
                    if (l.getNoeud() == n ) {
                        exist = true; // on indique que le label exist déjà dans le tas
                        if (l.getNoeud() == n && l.isMarquage()) {
                            marque = true; // on précise qu'il est déjà traité
                            y = l; // on affecte alors le label déjà existant
                            break; // on sort de la boucle
                        }
                    }
                }
                // si le sommet n'est pas déjà marqué
                if(!marque) {
                    // on parcours toute les distances
                    for (Arc a: tabArcx) {
                        // si l'arc va vers notre sommet et que
                        if(a.getDestination() == n && y.getCout() > (x.getCout()+a.getLongueur()) ) {
                            y.setCout(x.getCout()+a.getLongueur());

                            // on met a jour le tas
                            // si il exist on met a jour le tas
                            if (exist) {
                                tas.update(y);
                            } else {
                                // sinon on l'ajoute au tas
                                exist = true; // maintenant il est dans le tas
                                tas.insert(y);
                            }
                        }
                    }

                }
            }

            if (x.getNoeud().getNumero() == this.destination) {
                System.out.println(x.getCout());
                break;
            }
        }

        /*
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
        }*/


    }

}
