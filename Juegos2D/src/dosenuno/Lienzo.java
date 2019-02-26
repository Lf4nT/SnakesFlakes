package dosenuno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class Lienzo extends JPanel implements MouseListener, KeyListener {

	private Dimension d;
	private Thread t;
	Map<String, Juego> juegos = new HashMap<>();
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

	public void add(String nombre, Juego juego) {
		juegos.put(nombre, juego);
	}

	@Override
	public Dimension getPreferredSize() {
		return d;
	}

	public void iniciarJuego(String nombre) {
		juego = juegos.get(nombre);
		t = new Thread(() -> {
			long t0 = System.nanoTime(), t1, t;
			boolean fin = false;
			while (!fin) {
				t1 = System.nanoTime();
				t = t1 - t0;
				t0 = t1;
				fin = juego.siguiente(t);
				juego.render(g);
				paintComponent(getGraphics());
//				repaint();
			}
		});
		t.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (t != null)
			g.drawImage(buffer, 0, 0, this);
		else {
			g.setColor(Color.BLACK);
			g.drawString("1 - PelotasLocas", 100, 100);
			g.drawString("2 - Snake", 100, 150);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (t != null)
			juego.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (t != null)
			juego.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (t != null)
			juego.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (t != null)
			juego.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (t != null)
			juego.mouseReleased(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (t != null)
			juego.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (t != null)
			juego.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (t != null)
			juego.keyTyped(e);
		else {
			switch (e.getKeyChar()) {
			case '1':
				iniciarJuego("Pelotas Locas");
				break;
			case '2':
				iniciarJuego("Snake");
				break;
			}
		}
	}
}