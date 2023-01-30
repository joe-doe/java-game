package game.surroundings;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Vector;

public class AbstractBoard {

	public Vector<Shape> boardCollisionShapes = null;
	public Iterator<Shape> it = null;
	private Dimension levelDimension = null;

	public AbstractBoard(Dimension dimension) {
		levelDimension = dimension;
		boardCollisionShapes = new Vector<Shape>();

		// ============= Level bounds ============= 
		//begin
		boardCollisionShapes.add(new Rectangle2D.Double(-10, 0, 10, levelDimension.getHeight()));
		//end
		boardCollisionShapes.add(new Rectangle2D.Double(levelDimension.getWidth(), 0, 10, levelDimension.getHeight()));
		//ground
		boardCollisionShapes.add(new Rectangle2D.Double(0, 600, 10005, 20));

		//============= Obstacles ============= 
		boardCollisionShapes.add(new Rectangle2D.Double(400,513, 200,50));
		boardCollisionShapes.add(new Rectangle2D.Double(1100,513, 200,50));
		boardCollisionShapes.add(new Rectangle2D.Double(10000-400,513, 200,50));
		boardCollisionShapes.add(new Rectangle2D.Double(10000-1100,513, 200,50));
		Polygon p = new Polygon();
		p.addPoint(750, 450);
		p.addPoint(850, 500);
		p.addPoint(1050, 800);
		
//		boardCollisionShapes.add(p);
		
		it = boardCollisionShapes.iterator();
	}

	public Dimension getLevelDimension() {
		return this.levelDimension;
	}
}
