package ecoIhm;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import ecoEtres.Pondeuse;

@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame {
	
	PanneauPrincipal pp;

	public FenetrePrincipale() throws HeadlessException {
		super();
		
		//D�finit un titre pour notre fen�tre
		this.setTitle("Eco");
		// D�finit sa taille
		this.setSize(1600, 800);
		//Nous demandons maintenant � notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on ferme la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Cr�ation du panneau principal
		pp = Pondeuse.recupPanneau();
		// Ajout du panneau principal � la fen�tre
		this.add(pp);
		
		// Et enfin, on rends la fen�tre visible
		this.setVisible(true);
	}

}