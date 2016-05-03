package core ;

import java.awt.*;
import java.io.* ;
import java.util.ArrayList;
import java.util.HashMap;

import base.*;
import base.Label;

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
    
    public int compte(Label l,Arc a ){
    	return l.getCout()+a.getLongueur();
    }

    public void run() {
    	this.sortie.println("**********************");
        this.sortie.println("Fichier de test de PCC");
        this.sortie.println("**********************");
        this.sortie.println("");
        this.sortie.println("Fichier utilise : " + this.graphe.getNom());
        this.sortie.println("Nombre de sommets lus : " + this.graphe.getNb_node_aff());
        this.sortie.println("Nombre d'aretes lues : " + this.graphe.getArete_aff());
        this.sortie.println("Nombre de descripteurs lus : " + this.graphe.getDescrip_aff());
        this.sortie.println("");
    	this.sortie.println("Numero de sommet d'origine : "+origine);
    	this.sortie.println("Numero de sommet de destination : " + destination);
        this.sortie.println("");
        System.out.println("Run PCC de " + zoneOrigine + ":" + origine + " vers " + zoneDestination + ":" + destination) ;

        // A vous d'implementer la recherche de plus court chemin.
        // lancement timer
        long debut = System.currentTimeMillis();

        // Initialisation
        Label origine = new Label(this.graphe.getNoeudGraphe(this.origine));
        origine.setCout(0);
        origine.setMarquage(false);
        origine.setPere(null);

        // initalisation tas
        BinaryHeap<Label> tas = new BinaryHeap<>();
        tas.insert(origine); // on met l'origine dans le tas
        // Compteur tas
        int nbMaxTas = 1;
        // compteur sommet explorés
        int nbExplo = 1;
        // compteur sommet marqué
        int nbMarque = 0;

        // initialisation des sommets parcouru
        ArrayList<Label> sommet = new ArrayList<>();

        // hasmap sommet label
        HashMap<Noeud, Label> tabCorresp = new HashMap<>();
        tabCorresp.put(origine.getNoeud(),origine); // on ajoute l'origine
        this.graphe.getDessin().setColor(Color.green);

        ArrayList<Arc> tabArcx = new ArrayList<>();
        // algo
        // tant qu'il existe des sommets non marqué
        while(!tas.isEmpty()) {
            // x <- ExtractMin(tas)
            Label x = tas.findMin();
            // on marque le sommet
            x.setMarquage(true);
            nbMarque++;

            //this.graphe.getDessin().drawPoint(x.getNoeud().getLongitude(), x.getNoeud().getLatitude(), 3);
            if(x.getPere() != null)this.graphe.getDessin().drawLine(x.getNoeud().getLongitude(),x.getNoeud().getLatitude(),x.getPere().getLongitude(),x.getPere().getLatitude());

            // on l'ajoute au sommet visité
            sommet.add(x);
            // on le sort du tas
            tas.deleteMin();

            // on récupére les arcs qui partent de ce sommet
            tabArcx = x.getNoeud().getTabArc();

            // pour tout les successeurs de x
            for (Arc a: tabArcx) {
                // on crée un label pour le sommet
                Noeud n = a.getDestination();

                Label y = new Label(n);
                y.setMarquage(false); // on le definit comme non marqué
                y.setPere(x.getNoeud()); // on met a jour le pere
                y.setCout(Integer.MAX_VALUE); // cout a l'infini par défaut
                // si le successeurs n'est pas déjà marqué
                boolean marque = false;
                boolean exist = false;

                if (tabCorresp.containsKey(n)) {
                    exist = true;
                    y = tabCorresp.get(n);
                    if (tabCorresp.get(n).isMarquage()) {
                        marque = true;
                    }
                }
                // si le sommet n'est pas déjà marqué
                if(!marque) {
                    // si l'arc va vers notre sommet et que
                    if(y.getCout() > (this.compte(x, a)) ) {
                        y.setCout(this.compte(x, a));

                        // on met a jour le tas
                        // si il exist on met a jour le tas
                        if (exist) {
                            tas.update(y);
                        } else {
                            // sinon on l'ajoute au tas
                            exist = true; // maintenant il est dans le tas
                            tas.insert(y);
                            nbExplo++;
                            // on l'ajoute a la table de correspondance
                            tabCorresp.put(y.getNoeud(), y);
                        }
                    }
                }
            }

            // on compare la taille courante et la taille max
            if (tas.size() > nbMaxTas) {
                nbMaxTas = tas.size();
            }

            if (x.getNoeud().getNumero() == this.destination) {
                long duree = System.currentTimeMillis()-debut;

                System.out.println("Durée d'execution : " + duree + " ms");
                this.sortie.println("Durée d'execution : " + duree + " ms");
                System.out.println("Coût du trajet : " + x.getCout());
                this.sortie.println("Coût du trajet : " + x.getCout() + " metres");
                System.out.println("Nombre element max dans le tas : " + nbMaxTas);
                this.sortie.println("Nombre element max dans le tas : " + nbMaxTas);
                System.out.println("Nombre sommet marque : " + nbMarque);
                this.sortie.println("Nombre sommet marque : " + nbMarque);
                System.out.println("Nombre sommet explores : " + nbExplo);
                this.sortie.println("Nombre sommet explores : " + nbExplo);
                this.sortie.println("");

                // on récupére le chemin dans la structure chemin
                // on part de l'arrivé
                Label l = x; // on set a l'arrivé
                // on set le tableau du chemin
                ArrayList<Noeud> tabChemin = new ArrayList<>();

                while(x.getPere() != null) {
                    tabChemin.add(x.getNoeud());

                    x = tabCorresp.get(x.getPere());
                }

                Chemin cheminDijkstra = new Chemin(tabChemin);

                cheminDijkstra.dessinerChemin(this.graphe.getDessin());
                break;
            }
        }

    }

}
