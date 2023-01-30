package game.data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ProgressBarPanel extends JPanel {

	private Image progressBarBackground = null;
	private Graphics2D g2d = null;

	public ProgressBarPanel() {
		setLayout(null);
		setBackground(Color.YELLOW);
//		setOpaque(true);

		progressBarBackground = new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/VitalsAndScore/mask.png").getImage();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
//		 t.start();
	}

//	@Override
//	public void paint(Graphics g) {
//		g2d = (Graphics2D) g;
//		g2d.drawImage(progressBarBackground, 0, 0, null);
//		super.paint(g);
//
//	}
}
