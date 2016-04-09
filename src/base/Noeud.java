package base;

import java.util.ArrayList;

public class Noeud {
	
	// numéro noeud
    private int numero;

	// longitude 
    private float longitude;
	
	// latitude
    private float latitude;
    
    public float getLongitude() {
		return longitude;
	}
    
	public float getLatitude() {
		return latitude;
	}

	// Arc des successeurs
    private ArrayList<Arc> tabArc;

    // ajout d'un arc 
    public void addArc(Arc a) {
    	
    	tabArc.add(a);
    	
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Arc> getTabArc() {
		return tabArc;
	}

	public int getNbSuccesseurs() {
    	return tabArc.size();
    }

	public ArrayList<Noeud> getSuccesseurs() {
		ArrayList<Noeud> tabSuccesseurs = new ArrayList<>();

		for (Arc a: tabArc) {

			tabSuccesseurs.add(a.getDestination());

		}

		return tabSuccesseurs;
	}

    // constructeur de la classe mere 
	public Noeud(float longitude, float latitude, int numero) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.numero = numero;

		tabArc = new ArrayList<>();
	}

    @Override
    public String toString() {
        return "Noeud{" +
                "Num : " + this.numero +
                '}';
    }
}
