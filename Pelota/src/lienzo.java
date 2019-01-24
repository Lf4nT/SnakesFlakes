
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class lienzo extends JPanel {

	private Dimension d;
	private Thread t;
	private pelota pelota;

	public lienzo(int w, int h) {
		d = new Dimension(w, h);
		pelota = new pelota(Color.RED, 50, 100, 100, (float) Math.PI / 4, 100, d);
	}

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
				pelota.mover(t);
				repaint();
			}
		});
		t.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, d.width, d.height);
		pelota.paint(g);
		g.setColor(Color.BLACK);
	}
}