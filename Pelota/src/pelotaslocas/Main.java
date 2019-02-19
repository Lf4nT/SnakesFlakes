package pelotaslocas;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		Lienzo lienzo;
		JFrame frame = new JFrame("Pelotas Locas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lienzo = new Lienzo(900, 650);
		frame.add(lienzo);
		frame.pack();
		frame.setLocationRelativeTo(null);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
				lienzo.iniciarAnimacion();
			}
		});
	}

}