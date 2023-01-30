package game.play;

import game.artwork.ImageRepository;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class GameOverPanel extends JPanel {

	Image background = null;

	public GameOverPanel() {
		setLayout(null);
		setOpaque(true);
		setBackground(new Color(0, 0, 0, 0));
		setSize(CentralPanel.PANEL_WIDTH, CentralPanel.PANEL_HIGHT);
		background = ImageRepository.getInstance().getImage(ImageRepository.IMAGE_GAMEOVER);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 0, 0, null);
	}
}
