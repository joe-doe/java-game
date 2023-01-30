package game.data;

import game.artwork.ImageRepository;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JProgressBar;

public class GradientProgerssBar extends JProgressBar {

	private String message = null;
	private Image progressBarMask = null;
	private Graphics2D g2d = null;
	private GradientPaint gp = null;
	private int index = 0;
	private Color color1 = null;
	private Color color2 = null;
	private int w = 0;
	private int h = 0;
	private int intervals = 15;
	private int maximum = 0;

	public GradientProgerssBar(int max, String message, Color color) {
		maximum = max;

		setMaximum(maximum);
		setMinimum(0);
		setBounds(560, 12, 240, 35);

		// setStringPainted(true);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setBorderPainted(false);
		this.message = message;
		progressBarMask = ImageRepository.getInstance().getImage(ImageRepository.IMAGE_PROGRESSBAR_MASK);
		color1 = new Color(255, 0, 0);
		color2 = new Color(0, 255, 0);
		w = getWidth();
		h = getHeight();
		gp = new GradientPaint(0, 0, color1, (int) w, 0, color2);
	}

	public void reset() {
		setValue(maximum);
		index = 0;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// System.out.println("index " + index);
		g2d = (Graphics2D) g;
		g2d.setPaint(gp);
		g2d.fillRoundRect(0, 0, (w - (intervals * index)), h, 14, 14);
		g2d.drawImage(progressBarMask, 0, 0, null);
	}

	@Override
	public void setValue(int newValue) {
		if (newValue < this.getValue()) {
			index++;
			System.out.println("Bar lower");
		} else if (newValue > this.getValue()){
			index--;
			System.out.println("Bar higher");
		}
		super.setValue(newValue);
	}

}
