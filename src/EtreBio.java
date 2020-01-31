import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class EtreBio extends JComponent {

	int x = 0;
	int y = 0;
	

	public EtreBio(){
		// Initialisation du Timer pour rafraichir le panneau
		Timer timer = new Timer(50, new ActionListener(){
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
	
	private void Tick() {
		x += 5;
		y += 5;
	}
}
