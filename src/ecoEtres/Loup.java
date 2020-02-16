package ecoEtres;

public class Loup extends Animal {

	public Loup() {
		this(0, 0);
	}

	public Loup(int _x, int _y) {
		super(_x, _y);
		
		// Position
		vitesse = 40.0f;
		vitesseRot = Math.PI * 2;
		
		// energie
		nourritures.add(Pondeuse.Espece.MOUTON);
		seuilFaim = 70.0f;
		gainEnergie = 40.0f;
	}

}
