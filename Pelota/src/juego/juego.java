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

	public static void siguiente(long ns) {

	}

	public static void render(Graphics g) {

	}

}
