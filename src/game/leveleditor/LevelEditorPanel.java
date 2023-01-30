package game.leveleditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

public class LevelEditorPanel extends JScrollPane {

	private HashMap<Tile, Boolean> levelMap = null;
	private Vector<Tile> tilesInLevel = null;
	public Vector<Line2D.Double> linesInGrid = null;
	private Vector<Point2D.Double> pointsInGrid = null;
	private int levelWidth = 0;
	private int levelHeight = 0;
	private int fragments = 2;
	private JComponent client = null;
	Image background = null;
	Color transparent = null;
	/**
	 * Create the panel.
	 */
	public LevelEditorPanel(JComponent client, int width, int height) {
		super(client);
		this.client = client;
		this.transparent = new Color(0,0,0,0);
		background = new ImageIcon(
				"/data/DevelopersCorner/Eclipse/JavaGame_2/images/bg.jpg")
				.getImage();

		setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));

		Tile.setTilesSize(width / fragments, height / fragments);
		p("Tile width:" + Tile.rectangleHeight);
		levelMap = new HashMap<Tile, Boolean>();
		tilesInLevel = new Vector<Tile>();
		linesInGrid = new Vector<Line2D.Double>();
		pointsInGrid = new Vector<Point2D.Double>();

		levelWidth = width;
		levelHeight = height;

		setBounds(10, 10, 815, 620);

		calculateGird();
		startPintiting();
	}

	private void startPintiting() {
		Thread paintIt = new Thread(new Runnable() {

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
		paintIt.start();
	}

	private void calculateGird() {
		double x = Tile.rectangleWidth;
		double y = Tile.rectangleHeight;

		for (int i = 0; i < fragments; i++) {
			linesInGrid.add(new Line2D.Double(new Point2D.Double(x * i, 0),
					new Point2D.Double(x * i, y * fragments)));
			linesInGrid.add(new Line2D.Double(new Point2D.Double(0, y * i),
					new Point2D.Double(x * fragments, y * i)));
		}
	}

	public void setBackroundImage(String filename) {
		background = new ImageIcon(filename).getImage();
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, client.getX(), client.getY(), transparent, null);
		super.paint(g);
	}

	public void p(String p) {
		System.out.println(p);
	}
}
