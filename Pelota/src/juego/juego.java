package juego;

import java.awt.Graphics;

public abstract class juego {

	private lienzo lienzo;
	
	public juego(lienzo lienzo) {
		this.lienzo = lienzo;
	}

	public lienzo getLienzo() {
		return lienzo;
	}
	
	public abstract void siguiente(long ns);
	
	public abstract void render(Graphics g);
	
}