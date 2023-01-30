package game.baseClasses;

import game.entities.Hero;
import game.surroundings.GameItem;

import java.awt.geom.Rectangle2D;

public class HeroCollisionDetector {

	Rectangle2D.Double itemRect = null;
	Rectangle2D.Double heroRect = null;

	public boolean detectCollisionWithHeroX(Hero hero, GameItem item, float wantToGoToX) {
		boolean answer = false;
		if (hero.isAlive == true) {
			heroRect = hero.getItemRectangle();
			itemRect = new Rectangle2D.Double(wantToGoToX, item.getY(), item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

			if (itemRect.intersects(heroRect)) {
				answer = true;
			}
		}
		return answer;
	}

	public boolean detectCollisionWithHeroY(Hero hero, GameItem item, float wantToGoToY) {
		boolean answer = false;
		if (hero.isAlive == true) {
			heroRect = hero.getItemRectangle();
			itemRect = new Rectangle2D.Double(item.getX(), wantToGoToY, item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

			if (itemRect.intersects(heroRect)) {
				answer = true;
			}
		}
		return answer;
	}
}
