import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanneauPrincipal extends JPanel {
		
		public PanneauPrincipal() {
			super();
			this.setBackground(Color.BLACK);
			
			// Initialisation du Timer pour rafraichir le panneau
			Timer timer = new Timer(50, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					repaint();
				}
			});
			timer.start();
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// Boucle sur tous les etres enregistr�s
			int nbEtres = Pondeuse.NombreEtres();
			for (int i = 0 ; i < nbEtres ; i++){

				// couleur par d�faut (type d'�tre non identifi�)
				g.setColor(Color.red);
				
				// Recuperation de l'etre
				EtreBio etre = Pondeuse.RecupEtre(i);
				
				// Recherche de la couleur de l'�tre
				if (etre instanceof Herbe) {
					g.setColor(Color.green);
				}
				
				// Dessine l'�tre
				g.fillOval(etre.getX(), etre.getY(), 50, 50);
			}
		}
	}