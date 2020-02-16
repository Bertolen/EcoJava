import ecoEtres.Pondeuse;
import ecoIhm.FenetrePrincipale;

public class Main {

	public static void main(String[] args) {
		
		// cr�ation de la fen�tre
		FenetrePrincipale FP = new FenetrePrincipale();
		
		// D�finit sa taille
		FP.setSize(1600, 800);
		
		// D�finition de la taille de la map
		Pondeuse.fixeLimites(FP);
		
		// on cr�� qq �tres pour le test
		Pondeuse.pondreEtre(100, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(100, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 120, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(100, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(120, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(140, 140, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(250, 250, Pondeuse.Espece.MOUTON);
	}
}
