package game.baseClasses;

import game.entities.Enemies;
import game.entities.Enemy;
import game.entities.Weapon;
import game.surroundings.GameItem;

import java.awt.geom.Rectangle2D;

public class EnemiesCollisionDetector {
	Rectangle2D.Double itemRect = null;
	Enemy currentEnemy = null;
	
	public boolean detectCollisionWithEnemiesX(Enemies enemies, GameItem item, float wantToGoToX) {
		boolean answer = false;
		itemRect = new Rectangle2D.Double(wantToGoToX, item.getY(), item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

		for (int i = 0; i < enemies.enemiesVector.size(); i++) {
			currentEnemy = enemies.enemiesVector.get(i);
			if (currentEnemy.getItemRectangle().intersects(itemRect)) {
				if(item instanceof Weapon){
					System.out.println("OH shit !");
					currentEnemy.restInPeace();
				}
				answer = true;
			}
		}

		return answer;
	}

	public boolean detectCollisionWithEnemiesY(Enemies enemies, GameItem item, float wantToGoToY) {
		boolean answer = false;
		itemRect = new Rectangle2D.Double(item.getX(), wantToGoToY, item.spriteArt.getImageWidth(), item.spriteArt.getImageHeight());

		for (int i = 0; i < enemies.enemiesVector.size(); i++) {
			currentEnemy = enemies.enemiesVector.get(i);
			if (currentEnemy.getItemRectangle().intersects(itemRect)) {
				if(item instanceof Weapon){
					System.out.println("OH shit !");
					currentEnemy.restInPeace();
				}
				answer = true;
			}
		}

		return answer;
	}
}
