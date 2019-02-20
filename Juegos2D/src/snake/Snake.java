package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Snake extends Juego {

	private static final int L = 30;
	private static final int M = 2;
	private static final int LS = L - M * 2;

	private static final Point UP = new Point(0, -1);
	private static final Point LEFT = new Point(-1, 0);
	private static final Point DOWN = new Point(0, 1);
	private static final Point RIGHT = new Point(1, 0);

	private int w;
	private int h;

	private Point dir = DOWN;
	private long lapso = 0;

	private Deque<Point> snake = new LinkedList<Point>();
	private Point comida = new Point();
	private int crecer = 0;
	private int longitudCrecimiento = 5;

	private Random r = new Random();

	public Snake(Lienzo lienzo) {
		super(lienzo);
		w = lienzo.getWidth() / L;
		h = lienzo.getHeight() / L;
		int col = w / 2;
		int fil = 1;
		snake.add(new Point(col, fil));
		snake.add(new Point(col, fil + 1));
		snake.add(new Point(col, fil + 2));
		colocarComida();
	}

	private void colocarComida() {
		do {
			comida.setLocation(r.nextInt(w), r.nextInt(h));
		} while (snake.contains(comida));

	}

	@Override
	public void siguiente(long ns) {
		lapso += ns;

		if (lapso >= 100000000L) {
			Point cola;
			Point cabeza = snake.getLast();
			if (crecer > 0) {
				cola = new Point(cabeza.x + dir.x, cabeza.y + dir.y);
				crecer--;
				if (crecer == 0)
					colocarComida();
			} else {
				cola = snake.removeFirst();
				cola.setLocation(cabeza.x + dir.x, cabeza.y + dir.y);
				if (cola.equals(comida)) {
					crecer = longitudCrecimiento;
				}
			}
			snake.addLast(cola);
			lapso -= 100000000L;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		Iterator<Point> i = snake.iterator();
		while (i.hasNext()) {
			Point p = i.next();
			if (!i.hasNext())
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			g.fillRect(p.x * L + M, p.y * L + M, LS, LS);
		}
		if (crecer == 0) {
			g.setColor(Color.GREEN);
			g.fillRect(comida.x * L + M, comida.y * L + M, LS, LS);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_LEFT):
			dir = LEFT;
			break;
		case (KeyEvent.VK_RIGHT):
			dir = RIGHT;
			break;
		case (KeyEvent.VK_UP):
			dir = UP;
			break;
		case (KeyEvent.VK_DOWN):
			dir = DOWN;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}