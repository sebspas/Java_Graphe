package base;

import java.util.ArrayList;

public class Noeud {
	
	
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
  
    public int getNbSuccesseurs() {
    	return tabArc.size();
    }
    
    // constructeur de la classe mere 
	public Noeud(float longitude, float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		
		tabArc = new ArrayList<>();
	}
	
}
