import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Pondeuse {
	
	public enum Espece {
		HERBE
	}
	
	private static ArrayList<EtreBio> listeEtres = null;
	private static PanneauPrincipal instancePanneau = null;
	
	private static void checkListe() {
		// si la liste n'existe pas on la créé
		if (listeEtres == null) {
			listeEtres = new ArrayList<EtreBio>();
		}
	}
	
	private static void checkPanneau() {
		// si le panneau n'existe pas on le crée
		if (instancePanneau == null) {
			instancePanneau = new PanneauPrincipal();
		}
	}
	
	public static EtreBio PondreEtre(int _x, int _y, Espece espece) {
		// on commence par s'assurer que la liste existe
		checkListe();
		checkPanneau();
		
		// Declaration de l'être que l'on va retourner
		EtreBio etre = null;
		
		// creation de l'être
		switch (espece) {
		// Creation d'un être de type herbe
		case HERBE:
			etre = new Herbe(_x,_y);
			break;
			
		default:
			break;
		}
		
		// ajouter l'être aux composants du panneau principal
		if (etre != null) {
			listeEtres.add(etre);
			instancePanneau.add(etre);
		}
		
		// retour
		return etre;
	}
	
	public static int NombreEtres() {
		// on commence par s'assurer que la liste existe
		checkListe();
		
		return listeEtres.size();
	}
	
	public static EtreBio RecupEtre(int i) {
		// on commence par s'assurer que la liste existe
		checkListe();
		
		return listeEtres.get(i);
	}
	
	public static PanneauPrincipal RecupPanneau() {
		// on s'assure qu'un panneau existe
		checkPanneau();
		
		// retour
		return instancePanneau;
	}
}
