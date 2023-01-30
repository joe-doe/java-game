package game.surroundings;

import game.leveleditor.Tile;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;

public class TilesBoard {
	public Rectangle2D.Double rect = null;
	public Image image = null;
	public Vector<Tile> boardTiles = null;
	public Iterator<Tile> it = null;
	private Dimension levelDimension = null;
	
	public TilesBoard(Dimension dimension) {
		levelDimension = dimension;
		image = new ImageIcon(
				"/data/DevelopersCorner/Eclipse/JavaGame_2/images/brick.png")
				.getImage();
		
		rect = new Rectangle2D.Double(0,580,7800,20);
		Tile t0 = new Tile(rect, image);
		
		rect = new Rectangle2D.Double(400,433+88, image.getWidth(null),
				image.getHeight(null));
		Tile t1 = new Tile(rect, image);
		rect = new Rectangle2D.Double(1500, 433+30, image.getWidth(null),
				image.getHeight(null));
		Tile t2 = new Tile(rect, image);
//		rect = new Rectangle2D.Double(600, 433+100, image.getWidth(null),
//				image.getHeight(null));
//		Tile t3 = new Tile(rect, image);
		
		
		boardTiles = new Vector<Tile>();
		boardTiles.add(t0);
		boardTiles.add(t1);
		boardTiles.add(t2);
//		boardTiles.add(t3);

		it = boardTiles.iterator();
		// setBounds(400,388,image.getWidth(null), image.getHeight(null));
		// System.out.println("W:"+image.getWidth(null)+" H:"+image.getHeight(null));
	}

	public Dimension getLevelDimension(){
		return this.levelDimension;
	}
	// @Override
	// public void paint(Graphics g) {
	// super.paint(g);
	// Graphics2D g2d = (Graphics2D) g;
	// g2d.setColor(Color.red);
	// g2d.drawRect(this.getX(), this.getY(), (int) rect.width,
	// (int) rect.height);
	// }

}
