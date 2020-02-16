package ecoEtres;

import java.awt.Point;
import java.util.ArrayList;

public class Mouton extends EtreBio {

	enum Comportement {
		DEAMBULE, MANGE, FUIS
	}
	
	Comportement comportement;
	
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
	
	@Override
	protected void dirige(){
		// la direction de que l'on prends dépends du comportement
		switch (comportement) {
		case MANGE :
			mange();
			break;
		case FUIS :
			fuis();
			break;
		default :
			deambule();
			break;
			
		}
	}

	private void mange() {		
		
		// on vérifie s'il y a à manger à proximité
		ArrayList<EtreBio> nourriture = Pondeuse.etresDansCercle(posX, posY, 300, nourritures);
		
		// si on n'a pas de nourriture à proximité on deambule
		if(nourriture.size() == 0) {
			deambule();
			return;
		}
		
		// recherche de la nourriture la plus proche
		proie = nourriture.get(0);
		double distanceProie = Point.distance(this.posX, this.posY, proie.posX, proie.posY);
		
		for (int i = 1 ; i < nourriture.size() ; i ++) {
			EtreBio etre = nourriture.get(i);
			double distanceEtre = Point.distance(this.posX, this.posY, etre.posX, etre.posY);
			if(distanceEtre < distanceProie) {
				proie = etre;
				distanceProie = distanceEtre;
			}
		}
		
		// cet être est maintenant notre destination. On se dirige dessus.
		orientation = Math.atan2(proie.posY - this.posY, proie.posX - this.posX);

		// Maintenant qu'on a une proie on va essayer de la manger si elle est à portée
		if(distanceProie <= taille) {
			proie.meurs();
			proie = null;
			gaugeEnergie += 1.0f;
		}
	}

	private void fuis() {
		
	}

	private void deambule() {
		// on change l'orientaton aléatoirement
		float vitesseInstantanee = (float) this.calculRatioProgression(vitesseRot);
		orientation += Math.random() * vitesseInstantanee - vitesseInstantanee / 2;
		
		// on s'assure que la nouvelle vitesse est dans les bornes
		if (orientation < 0) orientation += 2 * Math.PI;
		if (orientation >= 2 * Math.PI) orientation -= 2 * Math.PI;
	}
	
	@Override
	protected void Tick() {

		// si le mouton manque d'energie il cherche à se nourir
		if (gaugeEnergie <= seuilFaim) {
			comportement = Comportement.MANGE;
		} else {
			comportement = Comportement.DEAMBULE;
		}
	}
}
