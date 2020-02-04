import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

@SuppressWarnings("serial")
public abstract class EtreBio extends JComponent {

	// Position
	protected double posX = 0;
	protected double posY = 0;
	protected float orientation = 0.0f;
	protected float vitesse = 0.0f;
	protected float vitesseRot = 0.0f;
	
	// Reproduction
	protected int densite = 0;
	protected float rayonEspacePerso = 30;
	protected float gaugeRepro = 0;
	protected float distanceMax = 30;
	protected float distanceMin = 20;
	
	// Tick
	private Timer timer;

	public EtreBio(){
		this(0, 0);
	}

	public EtreBio(int _x, int _y){
		super();
		posX = _x;
		posY = _y;

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
		if(Pondeuse.nbEtresDansCercle(this.getPosX(), this.getPosY(), rayonEspacePerso, this.getEspece()) > densite){
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
		// on calcule le futur emplacement
		double newPosX = posX + Math.cos(orientation) * vitesse;
		double newPosY = posY + Math.sin(orientation) * vitesse;
		
		// on s'assure que les nouvelles coordonnées sont dans les bornes
		if (newPosX < 0) newPosX = 0;
		if (newPosY < 0) newPosY = 0;
		if (newPosX >= Pondeuse.recupTailleX()) newPosX = Pondeuse.recupTailleX() - 1;
		if (newPosY >= Pondeuse.recupTailleY()) newPosY = Pondeuse.recupTailleY() - 1;
		
		// on garde en mémoire l'ancien emplacement
		double oldPosX = posX;
		double oldPosY = posY;
		
		// on fait le déplacement
		posX = newPosX;
		posY = newPosY;
		
		// on signale à la Pondeuse le déplacement (elle fera le changement de zones si necessaire)
		Pondeuse.changeZone(this, oldPosX, oldPosY);
	}
}