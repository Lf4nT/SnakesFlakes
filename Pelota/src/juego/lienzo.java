package juego;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class lienzo extends JPanel {

	private Dimension d;
	private Thread t;

	@Override
	public Dimension getPreferredSize() {
		return d;
	}

	public void iniciarAnimacion() {
		t = new Thread(() -> {
			long t0 = System.nanoTime(), t1, t;
			while (true) {
				t1 = System.nanoTime();
				t = t1 - t0;
				t0 = t1;
				juego.siguiente(t);
				repaint();
			}
		});
		t.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		juego.render(g);
	}
}