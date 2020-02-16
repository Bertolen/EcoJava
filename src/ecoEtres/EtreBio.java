package ecoEtres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public abstract class EtreBio {

	// fréquence des ticks
	protected final int timeStep = 50;
	
	// Position et dimensions
	protected double posX = 0.0d;
	protected double posY = 0.0d;
	protected double orientation = 0.0f;
	protected float taille = 0.0f;
	
	// Déplacement
	protected float vitesse = 0.0f;
	protected double vitesseRot = 0.0f;
	protected ArrayList<Pondeuse.Espece> especesEnCollision = new ArrayList<>();
	
	// Besoins
	protected double gaugeEnergie = 100.0f;
	protected double gaugeRepro = 0.0f;
	protected float perteEnergie = 1.0f;
	protected ArrayList<Pondeuse.Espece> nourritures = new ArrayList<>();
	protected double seuilFaim = 50.0f;
	
	// Reproduction
	protected int densite = 0;
	protected float rayonEspacePerso = 30.0f;
	protected float distanceMax = 30.0f;
	protected float distanceMin = 20.0f;
	
	// Tick
	private Timer timer;
	
	// Gestion du temps
	private long dernierTick = 0;
	
	// on est tous la proie de quelqu'un d'autre
	protected EtreBio proie = null;

	public EtreBio(){
		this(0, 0);
	}

	public EtreBio(int _x, int _y){
		super();
		posX = _x;
		posY = _y;

		// toute espèce est en collition avec elle même
		especesEnCollision.add(getEspece());
		
		// Initialisation du Timer pour rafraichir le panneau
		initTimer();
		
		// ceci est techniquement un tick
		dernierTick = System.currentTimeMillis();
	}

	// Initialisation du Timer pour rafraichir le panneau
	private void initTimer() {
		timer = new Timer(timeStep, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Update();
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
	
	public double calculRatioProgression (double gauge) {
		return (System.currentTimeMillis() - dernierTick) / 1000.0d * gauge;
	}
	
	public Pondeuse.Espece getEspece(){
		if(this instanceof Herbe) return Pondeuse.Espece.HERBE;
		if(this instanceof Mouton) return Pondeuse.Espece.MOUTON;
		return Pondeuse.Espece.NIMP;
	}
	
	private void Update() {
		verfieEnergie();
		Tick();
		reproduction();	
		dirige();
		avance();
		dernierTick = System.currentTimeMillis();
	}
	
	protected abstract void Tick();

	protected void verfieEnergie(){
		// consomation de l'energie passivement
		gaugeEnergie -= calculRatioProgression(perteEnergie);
		
		// si l'être n'a plus d'energie on supprime l'être
		if(gaugeEnergie <= 0.0f){
			this.meurs();
		}
	}
	
	public void meurs() {
		timer.stop();
		Pondeuse.supprimeEtre(this);
	}
	
	protected void reproduction(){
		// on ne fait cette action que si la gauge de reproduction est remplie.
		if (gaugeRepro < 100.0f) {
			return ;
		}
	
		// on viens de se reproduire, on n'aura pas besoin de le refaire au prochain Tick
		gaugeRepro = 0.0f;
		
		// On vérifie qu'on a la place de se reproduire
		if(Pondeuse.etresDansCercle(this.getPosX(), this.getPosY(), rayonEspacePerso, especesEnCollision).size() > densite){
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
		double newPosX = posX + Math.cos(orientation) * calculRatioProgression(vitesse);
		double newPosY = posY + Math.sin(orientation) * calculRatioProgression(vitesse);
		
		// on demande à la Pondeuse de nous déplacer (elle vérifie les collisions et les changements de zones
		Pondeuse.deplaceEtre(this, newPosX, newPosY);
	}
}