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
		
		// Reproduction		
		densite = 10;
		rayonEspacePerso = 30;
		gaugeRepro = 0;
		distanceMax = 30;
		distanceMin = 20;
		taille = 10;
		
		// Pour l'instant on va rendre l'herbe immortelle
		perteEnergie = 0.0f;
	}

	@Override
	protected void Tick() {		
		gaugeRepro += calculRatioProgression(20.0f);
	}

}