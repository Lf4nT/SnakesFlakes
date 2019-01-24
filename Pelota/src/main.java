
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class main {

	public static void main(String[] args) {
		lienzo lienzo;
		JFrame frame = new JFrame("Pelota que Rebota");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lienzo = new lienzo(900, 650);
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