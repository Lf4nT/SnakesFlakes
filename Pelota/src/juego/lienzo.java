package juego;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class lienzo extends JPanel {

	private Dimension d;
	private Thread t;
	private juego juego;
	private BufferedImage buffer;
	private Graphics g;
	
	public lienzo(int w, int h) {
		d = new Dimension(w, h);
		buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		g = buffer.createGraphics();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return d;
	}
	
	public void iniciarAnimacion() {
		juego = new pelotasLocas(this, 100);
		t = new Thread(() -> {
			long t0 = System.nanoTime(), t1, t;
			while(true) {
				t1 = System.nanoTime();
				t = t1 - t0;
				t0 = t1;
				juego.siguiente(t);
				juego.render(g);
				paintComponent(getGraphics());
//				repaint();
			}
		});
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}
}