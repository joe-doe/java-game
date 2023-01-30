package game.baseClasses;

import game.data.VitalsAndScore;
import game.entities.Hero;
import game.entities.StaticInteractable;
import game.entities.StaticInteractableAttributes;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class InteractablesCollisionDetector {

	Rectangle2D.Double heroRect = null;
	StaticInteractable currentStaticInteractableItem = null;

	public boolean detectCollisionWithInteractableX(Vector<StaticInteractable> staticInteractableGraphics, Hero hero, float wantToGoToX) {
		boolean answer = false;

		heroRect = new Rectangle2D.Double(wantToGoToX, hero.getY(), hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());

		for (int i = 0; i < staticInteractableGraphics.size(); i++) {
			currentStaticInteractableItem = staticInteractableGraphics.get(i);
			if (currentStaticInteractableItem.isDying == false) {
				if (currentStaticInteractableItem.getItemRectangle().intersects(heroRect)) {
					int howMuch = currentStaticInteractableItem.getAmount();
					boolean disapear = currentStaticInteractableItem.mustDisapear();

					switch (currentStaticInteractableItem.getType()) {
					case StaticInteractableAttributes.TYPE_SCORE:
						if (howMuch > 0) {
							hero.changeScore(howMuch);
						} else {
							hero.changeScore(howMuch);
						}
						break;
					case StaticInteractableAttributes.TYPE_HEALTH:

						if (howMuch < 0) {
							if (hero.cutSlack == false) {
								hero.cutSomeSlack();
								hero.changeHealthStatus(howMuch);
							}
						} else {
							if (VitalsAndScore.getInstance().getCurrentLifeLevel() < VitalsAndScore.getInstance().getMaxLevel()) {
								hero.changeHealthStatus(howMuch);
							}
						}

						break;
					default:
						break;
					}
					
					if(disapear == true){
						currentStaticInteractableItem.restInPeace();
					} else {
						currentStaticInteractableItem.delayCollision();
					}
					
					answer = true;
				}
			}
		}
		return answer;
	}

	public boolean detectCollisionWithInteractableY(Vector<StaticInteractable> staticInteractableGraphics, Hero hero, float wantToGoToY) {
		boolean answer = false;

		return answer;
	}
}
