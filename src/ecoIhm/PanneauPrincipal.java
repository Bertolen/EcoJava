package ecoIhm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import ecoEtres.EtreBio;
import ecoEtres.Pondeuse;

@SuppressWarnings("serial")
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
			
			// Boucle sur tous les etres enregistrés
			int nbEtres = Pondeuse.nombreEtres();
			for (int i = 0 ; i < nbEtres ; i++){
				
				// Recuperation de l'etre
				EtreBio etre = Pondeuse.recupEtre(i);
				
				// Recherche de la couleur de l'être
				switch(etre.getEspece()){
				//l'herbe est verte
				case HERBE:
					g.setColor(Color.green);
					break;

				// Les moutons sont blancs
				case MOUTON:
					g.setColor(Color.white);
					break;

				// couleur par défaut (type d'être non identifié)
				default:
					g.setColor(Color.red);
					break;
				}
				
				// Dessine l'être
				int taille = (int) etre.getTaille();
				int centreX = (int) etre.getPosX() - taille / 2;
				int centreY = (int) etre.getPosY() - taille / 2;
				
				g.fillOval(centreX, centreY, taille, taille);
			}
		}
	}