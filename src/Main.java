import ecoEtres.Pondeuse;
import ecoIhm.FenetrePrincipale;

public class Main {

	public static void main(String[] args) {
		
		// création de la fenêtre
		FenetrePrincipale FP = new FenetrePrincipale();
		
		// Définit sa taille
		FP.setSize(150, 150);
		
		// Définition de la taille de la map
		Pondeuse.fixeLimites(FP);
		
		// on créé qq êtres pour le test
		//Pondeuse.pondreEtre(100, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(25, 25, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(50, 25, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(25, 50, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(50, 50, Pondeuse.Espece.MOUTON);
	}
}
