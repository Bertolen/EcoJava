package ecoEtres;

public class Herbe extends EtreBio {
	
	public Herbe() {
		this(0,0);
	}

	public Herbe(int _x, int _y) {
		super(_x,_y);

		// Position
		orientation = (float) Math.PI;
		vitesse = 0.0f;
		vitesseRot = 0.0f;
		especesEnCollision.add(Pondeuse.Espece.HERBE);
		
		// Reproduction		
		densite = 10;
		rayonEspacePerso = 30;
		gaugeRepro = 0;
		distanceMax = 30;
		distanceMin = 20;
		taille = 10;
	}

	@Override
	protected void Tick() {
		super.Tick();
		
		gaugeRepro += 5.0f;
	}

}