package ecoEtres;

public class Mouton extends EtreBio {

	public Mouton() {
		this(0, 0);
	}

	public Mouton(int _x, int _y) {
		super(_x, _y);

		// Position
		orientation = (float) Math.PI;
		vitesse = 1.0f;
		vitesseRot = 0.3f;
		especesEnCollision.add(Pondeuse.Espece.MOUTON);
		
		// Reproduction
		densite = 0;
		rayonEspacePerso = 30;
		gaugeRepro = 0;
		distanceMax = 30;
		distanceMin = 20;
		taille = 20;
	}
	
	@Override
	protected void dirige(){
		// on change l'orientaton aléatoirement
		orientation += Math.random() * vitesseRot - vitesseRot / 2;
		
		// on s'assure que la nouvelle vitesse est dans les bornes
		if (orientation < 0) orientation += 2 * Math.PI;
		if (orientation >= 2 * Math.PI) orientation -= 2 * Math.PI;
	}
}
