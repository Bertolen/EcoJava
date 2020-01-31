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
		
		this.add(new EtreBio());
		
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
		
		g.setColor(Color.green);
		
		int nbEtres = this.getComponentCount();
		for (int i = 0 ; i < nbEtres ; i++){
			
			int x = this.getComponent(i).getX();
			int y = this.getComponent(i).getY();
			g.drawOval(x, y, 100, 100);
		}
	}
}
