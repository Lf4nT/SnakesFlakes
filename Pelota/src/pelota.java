
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class pelota {

	private double vx;
	private double vy;
	private double x;
	private double y;
	private int diametro;
	private Dimension marco;
	private Color color;

	public pelota(Color color, int radio, double x, double y, double d, double v, Dimension marco) {
		this.color = color;
		this.x = x - radio;
		this.y = y - radio;
		vx = v * Math.cos(d);
		vy = v * Math.sin(d);
		diametro = 2 * radio;
		this.marco = marco;
	}

	public void mover(long t) {
		double dx = t * vx / 200000000d;
		double dy = t * vy / 200000000d;
		x += dx;
		y += dy;
		if (x <= 0 || x + diametro >= marco.width)
			vx *= -1;
		else if (y <= 0 || y + diametro >= marco.height)
			vy *= -1;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diametro, diametro);
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diametro, diametro);
	}

}