package game.leveleditor;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Tile {

	public static double rectangleWidth;
	public static double rectangleHeight;

	private Rectangle2D.Double rectangle = null;
	private Image tileImage = null;

	public Tile(Rectangle2D.Double rect, Image image) {
		rectangle = rect;
		if (image == null){
			
		} else {
			tileImage = image;
		}
	}

	public Rectangle2D getRectangle() {
		return rectangle;
	}

	public Image getImage() {
		return tileImage;
	}

	public static final void setTilesSize(double w, double h) {
		rectangleWidth = w;
		rectangleHeight = h;
	}
}
