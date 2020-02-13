package ecoEtres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public abstract class EtreBio {

	// Position et dimensions
	protected double posX = 0.0d;
	protected double posY = 0.0d;
	protected float orientation = 0.0f;
	protected float taille = 0.0f;
	
	// Déplacement
	protected float vitesse = 0.0f;
	protected float vitesseRot = 0.0f;
	protected ArrayList<Pondeuse.Espece> especesEnCollision = null;
	
	// Reproduction
	protected int densite = 0;
	protected float rayonEspacePerso = 30.0f;
	protected float gaugeRepro = 0.0f;
	protected float distanceMax = 30.0f;
	protected float distanceMin = 20.0f;
	
	// Tick
	private Timer timer;

	public EtreBio(){
		this(0, 0);
	}

	public EtreBio(int _x, int _y){
		super();
		posX = _x;
		posY = _y;

		// initialisation des especes avec lesquelles cet être entre en collision
		especesEnCollision = new ArrayList<>();
		
		// Initialisation du Timer pour rafraichir le panneau
		initTimer();
	}

	// Initialisation du Timer pour rafraichir le panneau
	private void initTimer() {
		timer = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Tick();
			}
		});
		timer.start();
	}
	
	public double getPosX(){
		return posX;
	}

	public double getPosY(){
		return posY;
	}

	public double getTaille(){
		return taille;
	}
	
	protected void Tick() {
		reproduction();	
		dirige();
		avance();
	}
	
	public Pondeuse.Espece getEspece(){
		if(this instanceof Herbe) return Pondeuse.Espece.HERBE;
		if(this instanceof Mouton) return Pondeuse.Espece.MOUTON;
		return Pondeuse.Espece.NIMP;
	}
	
	protected void reproduction(){
		// on ne fait cette action que si la gauge de reproduction est remplie.
		if (gaugeRepro < 100.0f) {
			return ;
		}
	
		// on viens de se reproduire, on n'aura pas besoin de le refaire au prochain Tick
		gaugeRepro = 0.0f;
		
		// On vérifie qu'on a la place de se reproduire
		if(Pondeuse.nbEtresDansCercle(this.getPosX(), this.getPosY(), rayonEspacePerso, especesEnCollision) > densite){
			return;
		}
		
		// Initialisation des coordonnées de l'enfant
		double angle = Math.toRadians(Math.random() * 360);
		double distance = Math.random() * (distanceMax - distanceMin) + distanceMin;
		int bbX = (int) (posX + Math.cos(angle) * distance);
		int bbY = (int) (posY + Math.sin(angle) * distance);
		
		// Création de l'enfant
		Pondeuse.pondreEtre(bbX, bbY, this.getEspece());
	}

	// sers à changer l'orientation
	protected void dirige() {}
	
	// fais avancer l'être dans une ligne droite
	protected void avance() {
		
		// si on ne peut pas se déplacer (vitesse nulle) on ne fais rien ici
		if(vitesse == 0.0f && vitesseRot == 0.0f)
			return;
		
		// on calcule le futur emplacement
		double newPosX = posX + Math.cos(orientation) * vitesse;
		double newPosY = posY + Math.sin(orientation) * vitesse;
		
		// on demande à la Pondeuse de nous déplacer (elle vérifie les collisions et les changements de zones
		Pondeuse.deplaceEtre(this, newPosX, newPosY);
	}
}