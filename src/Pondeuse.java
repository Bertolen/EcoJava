import java.util.ArrayList;

public class Pondeuse {
	
	public enum Espece {
		NIMP, HERBE, MOUTON
	}
	
	private static ArrayList<EtreBio> listeEtres = null;
	private static PanneauPrincipal instancePanneau = null;
	
	// Limites de la map 
	private static int tailleX = 200;
	private static int tailleY = 200;
	
	//découpage de la zone
	private static ArrayList<ArrayList<ArrayList<EtreBio>>> zones = new ArrayList<ArrayList<ArrayList<EtreBio>>>();
	private static int tailleZone = 50;
	private static int nbZonesX = 0;
	private static int nbZonesY = 0;
	
	static long debut = 0;
	
	private static void checkListe() {
		// si la liste n'existe pas on la créé
		if (listeEtres == null) {
			listeEtres = new ArrayList<>();
		}
	}
	
	private static void checkPanneau() {
		// si le panneau n'existe pas on le crée
		if (instancePanneau == null) {
			instancePanneau = new PanneauPrincipal();
		}
	}
	
	public static EtreBio pondreEtre(int _x, int _y, Espece espece) {
		// on vérifie qu'on est dans l'espace délimité
		if(_x < 0 || _y < 0 || _x >= tailleX || _y >= tailleY){
			return null;
		}
		
		// on s'assure que la liste existe
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
			
		// Creation d'un être de type herbe
		case MOUTON:
			etre = new Mouton(_x,_y);
			break;
			
		// pas de création d'être 	
		default:
			break;
		}
		
		// ajouter l'être aux listes qui vont bien
		if (etre != null) {
			listeEtres.add(etre);
			instancePanneau.add(etre);
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
	
	// Verification du type de l'être
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
	
	// Renvoi le nombre d'Etres dans un cercle
	public static int nbEtresDansCercle(int x, int y, int r) {
		return nbEtresDansCercle(x, y, r, Espece.NIMP);
	}
	
	// Renvoi le nombre d'Etres d'un espece particulière dans un cercle
	public static int nbEtresDansCercle(double x, double y, float r, Espece e) {
		int nb = 0;
		
		// On parcours tous les êtres de la liste
		ArrayList<EtreBio> etresProches = etresAProximite(x, y, r, e);
		for (int i = 0 ; i < etresProches.size() ; i++) {

			// sauvegarde de l'être en cours
			EtreBio etre =  etresProches.get(i);
			
			// Pour chaque être on vérifie la distance qui le sépare du centre de notre cercle
			if (estEspece(etre, e) && Math.sqrt(Math.pow(x - etre.getPosX(),2) + Math.pow(y - etre.getPosY(),2)) <= r){
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
		nbZonesX = (int)(tailleX / tailleZone);
		nbZonesY = (int)(tailleY / tailleZone);
		
		// on refait les zones
		for (int i = 0; i < nbZonesX ; i++){
			zones.add(new ArrayList<ArrayList<EtreBio>>());
			for (int j = 0; j < nbZonesY ; j++){
				zones.get(i).add(new ArrayList<EtreBio>());
			}
		}

		// on s'assure que la liste existe
		checkListe();
		
		// On redistribue tous les êtres en cours dans leurs zones respectives
		for (int i = 0 ; i < listeEtres.size() ; i++) {
			EtreBio etre = listeEtres.get(i);
			int zoneX = (int)(etre.getPosX() / tailleZone);
			int zoneY = (int)(etre.getPosY() / tailleZone);
			zones.get(zoneX).get(zoneY).add(etre);
		}
	}

	public static ArrayList<EtreBio> etresAProximite(double x, double y, float portee){
		return etresAProximite(x, y, portee, Espece.NIMP);
	}
	
	public static ArrayList<EtreBio> etresAProximite(double x, double y, float portee, Espece e){
		ArrayList<EtreBio> liste = new ArrayList<>();
		int zoneX = (int) x / tailleZone;
		int zoneY = (int) y / tailleZone;
		int porteeZones = (int)(portee / tailleZone + 1);
		
		// pour toutes les zones autour de la notre
		for (int i = zoneX - porteeZones ; i <= zoneX + porteeZones ; i++) {
			for (int j = zoneY - porteeZones ; j <= zoneY + porteeZones ; j++) {
				
				// il faut que la zone existe
				if (i >= 0 && i < nbZonesX && j >= 0 && j < nbZonesY){
					
					// récupération de la zone
					ArrayList<EtreBio> zone = zones.get(i).get(j);
					
					// on parcours tous les êtres de la zone
					for(int k = 0 ; k < zone.size() ; k++){
						
						// On ajoute les êtres de la bonne espèce à la liste
						EtreBio etre = zone.get(k);
						if(estEspece(etre, e)) liste.add(etre);
					}
				}
			}
		}
		
		// on renvoi la liste
		return liste;
	}

	public static void changeZone(EtreBio etreBio, double oldPosX, double oldPosY) {
		int zoneX = (int)(oldPosX / tailleZone);
		int zoneY = (int)(oldPosY / tailleZone);
		int newZoneX = (int)(etreBio.getPosX() / tailleZone);
		int newZoneY = (int)(etreBio.getPosY() / tailleZone);
		
		// si la nouvelle zone différe de la précédente
		if(zoneX != newZoneX || zoneY != newZoneY){
			zones.get(zoneX).get(zoneY).remove(etreBio);
			zones.get(newZoneX).get(newZoneY).add(etreBio);
		}
	}
}