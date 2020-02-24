import ecoEtres.Pondeuse;
import ecoIhm.FenetrePrincipale;

public class Main {

	public static void main(String[] args) {
		
		// cr�ation de la fen�tre
		FenetrePrincipale FP = new FenetrePrincipale();
		
		// D�finition de la taille de la map
		Pondeuse.fixeLimites(FP);
		
		// on cr�� qq �tres pour le test
		Pondeuse.pondreEtre(100, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(160, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(100, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(160, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(100, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(160, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(100, 160, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 160, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 160, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(160, 160, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(250, 250, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(150, 250, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(250, 150, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(350, 350, Pondeuse.Espece.LOUP);
	}
}
