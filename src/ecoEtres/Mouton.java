package ecoEtres;

public class Mouton extends Animal {
	
	public Mouton() {
		this(0, 0);
	}

	public Mouton(int _x, int _y) {
		super(_x, _y);
		
		// Position
		vitesse = 40.0f;
		vitesseRot = Math.PI * 2;
		
		// energie
		nourritures.add(Pondeuse.Espece.HERBE);
		seuilFaim = 90.0f;
		gainEnergie = 1.0f;
	}
}
