package base;

import java.awt.*;
import java.util.ArrayList;

public class Chemin {

    // tab des noeuds de passage
    ArrayList<Noeud> tabNoeud;

    public Chemin(ArrayList<Noeud> tabNoeud) {

        this.tabNoeud = tabNoeud;
    }

    public int distanceTot() {
        int distance = 0;
        for (int i = 0; i < tabNoeud.size()-1  ; i++) {
            // on set le noeud courant
            Noeud n = tabNoeud.get(i);

            // on set le noeud suivant si pas la fin
            Noeud nsuiv = tabNoeud.get(i+1);

            int minLongueur = Integer.MAX_VALUE;

            // on parcours tout les arcs possible
            for (Arc a: n.getTabArc()) {

                // noeud uniquelent vers arc sortant
                if (a.getDestination() == nsuiv &&  a.getLongueur() < minLongueur) {
                    minLongueur = a.getLongueur();
                }
            }
            distance += minLongueur;
        }

        return distance;
    } // distanceTot()

    public float tempsTotal() {
        float tmpstotal = 0;
        for (int i = 0; i < tabNoeud.size()-1  ; i++) {
            // on set le noeud courant
            Noeud n = tabNoeud.get(i);

            // on set le noeud suivant si pas la fin
            Noeud nsuiv = tabNoeud.get(i+1);

            float mintmps = Float.MAX_VALUE;

            // on parcours tout les arcs possible
            for (Arc a: n.getTabArc()) {

                if (a.getDestination() == nsuiv ) {
                    int distance = a.getLongueur();
                    int vitesse = a.getDesc().vitesseMax();

                    // calcul du temps pour l'arc
                    float tmp = (distance * 3.6f / vitesse);


                    if (tmp < mintmps) {
                        mintmps = tmp;
                    }
                }
            }
            tmpstotal += mintmps;
        }

        return (tmpstotal/60);
    }

    public void dessinerChemin(Dessin dessin) {

        Couleur.set(dessin, 'b') ;

        for (int i = 0; i < tabNoeud.size()-1  ; i++) {
            // on set le noeud courant
            Noeud n = tabNoeud.get(i);

            // on set le noeud suivant si pas la fin
            Noeud nsuiv = tabNoeud.get(i+1);

            dessin.drawLine(n.getLongitude(), n.getLatitude(), nsuiv.getLongitude(), nsuiv.getLatitude()) ;
        }

    }

    //chemin_0x100_2_139
}
