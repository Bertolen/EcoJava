import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public abstract class EtreBio extends JComponent {

	int x = 0;
	int y = 0;
	float gaugeRepro = 0;
	private Timer timer;

	public EtreBio(){
		super();
		
		x = 0;
		y = 0;

		// Initialisation du Timer pour rafraichir le panneau
		initTimer();
	}

	public EtreBio(int _x, int _y){
		super();
		x = _x;
		y = _y;

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
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	protected void Tick() {
		Reproduction();	
	}
	
	protected void Reproduction(){
		// on ne fait cette action que si la gauge de reproduction est remplie.
		if (gaugeRepro < 100.0f) {
			return ;
		}
		
		gaugeRepro = 0.0f;
		int bbX = x + 10;
		int bbY = y + 10;
		
		Pondeuse.PondreEtre(bbX, bbY, Pondeuse.Espece.HERBE);
	}
}
