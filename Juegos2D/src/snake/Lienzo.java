package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Lienzo extends JPanel implements MouseListener, KeyListener {

	private Dimension d;
	private Thread t;
	private Juego juego;
	private BufferedImage buffer;
	private Graphics g;

	public Lienzo(int w, int h) {
		d = new Dimension(w, h);
		buffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		g = buffer.createGraphics();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);

	}

	@Override
	public Dimension getPreferredSize() {
		return d;
	}

	public void iniciarAnimacion() {
		juego = new Snake(this);
		t = new Thread(() -> {
			long t0 = System.nanoTime(), t1, t;
			while (true) {
				t1 = System.nanoTime();
				t = t1 - t0;
				t0 = t1;
				juego.siguiente(t);
				juego.render(g);
				paintComponent(getGraphics());
			}
		});
		t.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		juego.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		juego.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		juego.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		juego.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		juego.mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		juego.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		juego.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		juego.keyTyped(e);
	}
}