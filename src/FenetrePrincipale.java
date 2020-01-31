import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame {
	
	PanneauPrincipal pp;

	public FenetrePrincipale() throws HeadlessException {
		super();
		
		//D�finit un titre pour notre fen�tre
		this.setTitle("Eco");
		//D�finit sa taille : 1600 pixels de large et 800 pixels de haut
		this.setSize(1600, 800);
		//Nous demandons maintenant � notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on ferme la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Cr�ation du panneau principal
		pp = Pondeuse.RecupPanneau();
		// Ajout du panneau principal � la fen�tre
		this.add(pp);
		
		// on cr�� un �tre pour le test
		Pondeuse.PondreEtre(100, 100, Pondeuse.Espece.HERBE);
		
		// Et enfin, on rends la fen�tre visible
		this.setVisible(true);
	}

}
