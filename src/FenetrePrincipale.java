import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

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
		pp = Pondeuse.RecupPanneau();
		// Ajout du panneau principal à la fenêtre
		this.add(pp);
		
		// on créé un être pour le test
		Pondeuse.PondreEtre(100, 100, Pondeuse.Espece.HERBE);
		
		// Et enfin, on rends la fenêtre visible
		this.setVisible(true);
	}

}
