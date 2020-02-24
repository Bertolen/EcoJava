package ecoIhm;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import ecoEtres.Pondeuse;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	
	PanneauPrincipal pp;

	public FenetrePrincipale() throws HeadlessException {
		super();
		
		//Définit un titre pour notre fenêtre
		this.setTitle("Eco");
		// Définit sa taille
		this.setSize(1600, 800);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on ferme la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Création du panneau principal
		pp = Pondeuse.recupPanneau();
		// Ajout du panneau principal à la fenêtre
		this.add(pp);
		
		// Et enfin, on rends la fenêtre visible
		this.setVisible(true);
	}

}