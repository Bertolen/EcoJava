package ecoEtres;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import ecoIhm.PanneauPrincipal;

public class Pondeuse {
	
	public enum Espece {
		NIMP, HERBE, MOUTON
	}
	
	private static ArrayList<EtreBio> listeEtres = null;
	private static PanneauPrincipal instancePanneau = null;
	
	// Limites de la map 
	private static int tailleX = 200;
	private static int tailleY = 200;
	
	//d�coupage de la zone
	private static ArrayList<ArrayList<ArrayList<EtreBio>>> zones = new ArrayList<ArrayList<ArrayList<EtreBio>>>();
	private static int tailleZone = 50;
	private static int nbZonesX = 0;
	private static int nbZonesY = 0;
	
	static long debut = 0;
	
	private static void checkListe() {
		// si la liste n'existe pas on la cr��
		if (listeEtres == null) {
			listeEtres = new ArrayList<>();
		}
	}
	
	private static void checkPanneau() {
		// si le panneau n'existe pas on le cr�e
		if (instancePanneau == null) {
			instancePanneau = new PanneauPrincipal();
		}
	}
	
	public static EtreBio pondreEtre(int _x, int _y, Espece espece) {
		// on v�rifie qu'on est dans l'espace d�limit�
		if(_x < 0 || _y < 0 || _x >= tailleX || _y >= tailleY){
			return null;
		}
		
		// on s'assure que la liste existe
		checkListe();
		checkPanneau();
		
		// Declaration de l'�tre que l'on va retourner
		EtreBio etre = null;
		
		// creation de l'�tre
		switch (espece) {
		
		// Creation d'un �tre de type herbe
		case HERBE:
			etre = new Herbe(_x,_y);
			break;
			
		// Creation d'un �tre de type herbe
		case MOUTON:
			etre = new Mouton(_x,_y);
			break;
			
		// pas de cr�ation d'�tre 	
		default:
			break;
		}
		
		// ajouter l'�tre aux listes qui vont bien
		if (etre != null) {
			listeEtres.add(etre);
			int zoneX = (int)(etre.getPosX() / tailleZone);
			int zoneY = (int)(etre.getPosY() / tailleZone);
			zones.get(zoneX).get(zoneY).add(etre);
		}
		
		// retour
		return etre;
	}
	
	public static int nombreEtres() {
		// on commence par s'assurer que la liste existe
		checkListe();
		
		return listeEtres.size();
	}
	
	public static EtreBio recupEtre(int i) {
		// on commence par s'assurer que la liste existe
		checkListe();
		
		return listeEtres.get(i);
	}
	
	public static PanneauPrincipal recupPanneau() {
		// on s'assure qu'un panneau existe
		checkPanneau();
		
		// retour
		return instancePanneau;
	}
	
	// Verification du type de l'�tre
	public static boolean estEspece(EtreBio etre, Espece espece){
		
		boolean bonneEspece = false;
		switch (espece) {
		
		case NIMP:
			bonneEspece = etre != null;
			break;
			
		case HERBE:
			bonneEspece = etre instanceof Herbe;
			break;
			
		case MOUTON:
			bonneEspece = etre instanceof Herbe;
			break;
			
		default:
			break;
		}
		
		return bonneEspece;
	}
	/*
	// Renvoi le nombre d'Etres dans un cercle
	public static int nbEtresDansCercle(int x, int y, int r) {
		ArrayList<Espece> liste = new ArrayList<>();
		liste.add(Espece.NIMP);
		return nbEtresDansCercle(x, y, r, liste);
	}
	*/
	// Renvoi le nombre d'Etres d'un espece particuli�re dans un cercle
	public static int nbEtresDansCercle(double x, double y, float rayon, ArrayList<Espece> especes) {
		int nb = 0;
		
		// On parcours tous les �tres de la liste
		ArrayList<EtreBio> etresProches = etresAProximite(x, y, rayon, especes);
		for (int i = 0 ; i < etresProches.size() ; i++) {

			// sauvegarde de l'�tre en cours
			EtreBio etre =  etresProches.get(i);
			
			// Pour chaque �tre on v�rifie la distance qui le s�pare du centre de notre cercle
			if (Point.distance(x, y, etre.getPosX(), etre.getPosY()) <= rayon){
				nb++;
			}
		}
		
		return nb;
	}
	
	public static int recupTailleX(){
		return tailleX;
	}
	
	public static int recupTailleY(){
		return tailleY;
	}

	public static void fixeLimites(JFrame frame) {
		// on r�duit un peu la hauteur pour prendre en compte la barre de titre
		fixeLimites(frame.getWidth(), frame.getHeight() - 30);
	}
	
	public static void fixeLimites(int limiteX, int limiteY) {
		// on s'assure que les limites ne sont pas ridicules
		if(limiteX > 0) tailleX = limiteX;
		if(limiteY > 0) tailleY = limiteY;
		
		// on vide les zones
		for (int i = 0; i < zones.size() ; i++){
			for (int j = 0; j < zones.get(i).size() ; j++){
				zones.get(i).get(j).clear();
			}
			zones.get(i).clear();
		}
		
		// calcul du nouveau nombre de zones
		nbZonesX = Math.max((int)(tailleX / tailleZone) , 1);
		nbZonesY = Math.max((int)(tailleY / tailleZone) , 1);
		
		// on refait les zones
		for (int i = 0; i < nbZonesX ; i++){
			zones.add(new ArrayList<ArrayList<EtreBio>>());
			for (int j = 0; j < nbZonesY ; j++){
				zones.get(i).add(new ArrayList<EtreBio>());
			}
		}

		// on s'assure que la liste existe
		checkListe();
		
		// On redistribue tous les �tres en cours dans leurs zones respectives
		for (int i = 0 ; i < listeEtres.size() ; i++) {
			EtreBio etre = listeEtres.get(i);
			int zoneX = (int)(etre.getPosX() / tailleZone);
			int zoneY = (int)(etre.getPosY() / tailleZone);
			zones.get(zoneX).get(zoneY).add(etre);
		}
	}
	/*
	public static ArrayList<EtreBio> etresAProximite(double x, double y, float portee){
		ArrayList<Espece> liste = new ArrayList<>();
		liste.add(Espece.NIMP);
		return etresAProximite(x, y, portee, liste);
	}
	*/
	public static ArrayList<EtreBio> etresAProximite(double x, double y, float portee, ArrayList<Espece> especes){
		// allocation de la liste en sortie
		ArrayList<EtreBio> liste = new ArrayList<>();
		
		// calcul de la zone au centre de la recherche
		int zoneX = (int) x / tailleZone;
		int zoneY = (int) y / tailleZone;
		
		// calcul du nombre de zones � port�e
		int porteeZones = (int)(portee / tailleZone) + 1;
		
		//extr�mes des zones � chercher
		int xMin = Math.max(0 , zoneX - porteeZones);
		int yMin = Math.max(0 , zoneY - porteeZones);
		int xMax = Math.min(nbZonesX - 1 , zoneX + porteeZones);
		int yMax = Math.min(nbZonesY - 1 , zoneY + porteeZones);
		
		// pour toutes les zones autour de la notre
		for (int i = xMin ; i <= xMax ; i++) {
			for (int j = yMin ; j <= yMax ; j++) {
					
				// r�cup�ration de la zone
				ArrayList<EtreBio> zone = zones.get(i).get(j);
				
				// on parcours tous les �tres de la zone
				for(int k = 0 ; k < zone.size() ; k++){
					
					// On ajoute les �tres de la bonne esp�ce � la liste
					EtreBio etre = zone.get(k);
					if(especes.contains(etre.getEspece())) 
						liste.add(etre);
				}
			}
		}
		
		// on renvoi la liste
		return liste;
	}

	public static void changeZone(EtreBio etreBio, double newPosX, double newPosY) {
		
		// calcul des coordonn�es des zones (ancienne et nouvelle)
		int zoneX = (int)(etreBio.getPosX() / tailleZone);
		int zoneY = (int)(etreBio.getPosY() / tailleZone);
		int newZoneX = (int)(newPosX / tailleZone);
		int newZoneY = (int)(newPosY / tailleZone);
		
		// si la nouvelle zone diff�re de la pr�c�dente
		if(zoneX != newZoneX || zoneY != newZoneY){
			zones.get(zoneX).get(zoneY).remove(etreBio);
			zones.get(newZoneX).get(newZoneY).add(etreBio);
		}
	}

	public static void deplaceEtre(EtreBio etreBio, double newPosX, double newPosY) {
		
		// on s'assure que les nouvelles coordonn�es sont dans les bornes
		if (newPosX < 0) newPosX = 0;
		if (newPosY < 0) newPosY = 0;
		if (newPosX >= tailleX) newPosX = tailleX - 1;
		if (newPosY >= tailleY) newPosY = tailleY - 1;
		
		// on r�cup�re le nombre de collisions � destination (nombre d'�tres � destination moins un, l'�tre en question)
		int collisions = nbEtresDansCercle(newPosX, newPosY, (float) etreBio.getTaille(), etreBio.especesEnCollision) - 1;
		
		// si on a une collision alors on arr�te le traitement du d�placement
		if (collisions > 0) return;
		
		// on change l'emplacement de l'�tre
		etreBio.posX = newPosX;
		etreBio.posY = newPosY;
		
		// on change la zone
		changeZone(etreBio, newPosX, newPosY);
	}
}