import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	
	PanneauPrincipal pp;

	public FenetrePrincipale() throws HeadlessException {
		super();
		
		//Définit un titre pour notre fenêtre
		this.setTitle("Eco");
		//Définit sa taille : 1600 pixels de large et 800 pixels de haut
		this.setSize(1600, 800);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on ferme la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Création du panneau principal
		pp = Pondeuse.recupPanneau();
		// Ajout du panneau principal à la fenêtre
		this.add(pp);
		
		// Définition de la taille de la map
		Pondeuse.fixeLimites(1600, 800);
		// on créé un être pour le test
		Pondeuse.pondreEtre(100, 100, Pondeuse.Espece.HERBE);
		Pondeuse.pondreEtre(800, 400, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(800, 400, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(800, 400, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(800, 400, Pondeuse.Espece.MOUTON);
		Pondeuse.pondreEtre(800, 400, Pondeuse.Espece.MOUTON);
		
		// Et enfin, on rends la fenêtre visible
		this.setVisible(true);
	}

}