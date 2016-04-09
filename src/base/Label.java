package base;

import java.util.Comparator;

public class Label implements Comparable<Label> {

    private boolean marquage; // marqué si la valeur est fixé

    private int cout; // valeur courante du plus court chemin

    private Noeud pere; // sommet precedant sur le chemin correspondant au plus court chemin

    private Noeud noeud;  // sommet courant

    public Label(Noeud noeud) {
        this.marquage = false;
        this.cout = Integer.MAX_VALUE;
        this.noeud = noeud;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public Noeud getPere() {
        return pere;
    }

    public void setPere(Noeud pere) {
        this.pere = pere;
    }

    public boolean isMarquage() {
        return marquage;
    }

    public void setMarquage(boolean marquage) {
        this.marquage = marquage;
    }

    public Noeud getNoeud() {
        return noeud;
    }

    @Override
    public int compareTo(Label label) {
        if(this.cout > label.cout) {
            return 1;
        } else if (this.cout == label.cout) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Label{" +
                "marquage=" + marquage +
                ", cout=" + cout +
                ", pere=" + pere +
                ", noeud=" + noeud +
                '}';
    }
}
