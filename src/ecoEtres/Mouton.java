package ecoEtres;

public class Mouton extends Animal {
	
	public Mouton() {
		this(0, 0);
	}

	public Mouton(int _x, int _y) {
		super(_x, _y);
		
		// Position
		orientation = (float) Math.PI;
		vitesse = 30.0f;
		vitesseRot = Math.PI;
		
		// Reproduction
		densite = 0;
		rayonEspacePerso = 30;
		gaugeRepro = 0;
		distanceMax = 30;
		distanceMin = 20;
		taille = 20;
		
		// comportement initial
		comportement = Comportement.DEAMBULE;
		
		// energie
		nourritures.add(Pondeuse.Espece.HERBE);
		seuilFaim = 90.0f;
	}
}
