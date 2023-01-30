package game.leveleditor;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Vector;

public class LevelEditor {
	private HashMap<Tile, Boolean> levelMap = null;
	private Vector<Tile> tilesInRectangles = null;
	private double levelWidth = 0.0;
	private double levelHeight = 0.0;

	public LevelEditor(double width, double height) {
		levelMap = new HashMap<Tile, Boolean>();
		tilesInRectangles = new Vector<Tile>();
		levelWidth = width;
		levelHeight = height;
	}

	
}
