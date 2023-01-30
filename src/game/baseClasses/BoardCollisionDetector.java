package game.baseClasses;

import game.surroundings.AbstractBoard;
import game.surroundings.GameItem;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class BoardCollisionDetector {

	Rectangle2D.Double itemRect = null;

	public boolean detectCollisionWithBoardX(AbstractBoard board, GameItem item, float wantToGoToX) {
		boolean answer = false;
		itemRect = new Rectangle2D.Double(wantToGoToX, item.getY(), item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

		for (Shape s : board.boardCollisionShapes) {
			if (s.intersects(itemRect)) {
				// p("Can't go to x:" + wantToGoToX);
				answer = true;
			}
		}
		return answer;
	}

	public boolean detectCollisionWithBoardY(AbstractBoard board, GameItem item, float wantToGoToY) {
		boolean answer = false;
		itemRect = new Rectangle2D.Double(item.getX(), wantToGoToY, item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

		for (Shape s : board.boardCollisionShapes) {
			if (s.intersects(itemRect)) {
				// p("Can't go to y:" + wantToGoToY);
				answer = true;
			}
		}
		return answer;
	}

}
