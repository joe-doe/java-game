package game.entities;

import javax.swing.JLabel;

import game.artwork.SimpleSpriteGraphics;
import game.baseClasses.BoardCollisionDetector;
import game.baseClasses.Interactable;
import game.baseClasses.Moveable;
import game.baseClasses.EnemiesCollisionDetector;
import game.surroundings.AbstractBoard;
import game.surroundings.GameItem;

public class Weapon extends GameItem implements Moveable, Interactable {

	AbstractBoard board = null;
	Enemies enemies = null;
	EnemiesCollisionDetector enemiesCollisionDetector = null;
	BoardCollisionDetector boardCollisionDetector = null;
	long lifeTime = 0;

	public Weapon(JLabel item, AbstractBoard board, Enemies enemies, int startingX, int startingY, float speedX, float speedY) {
		this.board = board;
		this.enemies = enemies;
		enemiesCollisionDetector = new EnemiesCollisionDetector();
		boardCollisionDetector = new BoardCollisionDetector();

		setSpriteArt(new SimpleSpriteGraphics(item,null));
		setX(startingX);
		setY(startingY);
		setSpeedX(speedX);
		setSpeedY(speedY);

	}

	public long getLifeTime() {
		return this.lifeTime;
	}

	@Override
	public void move(long timeBetweenUpates) {
		lifeTime += timeBetweenUpates;
		float wantToGoToX = positionX + (speedX * timeBetweenUpates);
		// float wantToGoToY = positionY + (speedY * timeBetweenUpates) / 3;

		// p("X:" + positionX + " -------- SPEED:" + speedX + " -------- TIME:"
		// + timeBetweenUpates);
		// p("X:" + wantToGoToX + " Y:" + wantToGoToY);

		if (detectCollisionX(wantToGoToX) == false) {
			setX(wantToGoToX);
		} else {
			setSpeedX(-getSpeedX());
		}

		// if (detectCollisionY(wantToGoToY) == false) {
		// positionY = wantToGoToY;
		// }
	}

	@Override
	public boolean detectCollisionX(float wantToGoToX) {
		boolean answer = false;

		if (enemiesCollisionDetector.detectCollisionWithEnemiesX(enemies, this, wantToGoToX)) {
			this.isDying = true;
			this.isAlive = false;
			answer = true;
		}
		if (boardCollisionDetector.detectCollisionWithBoardX(board, this, wantToGoToX)) {
			this.isDying = true;
			this.isAlive = false;
			answer = true;
		}
		return answer;

	}

	@Override
	public boolean detectCollisionY(float wantToGoToY) {
		boolean answer = false;

		if (enemiesCollisionDetector.detectCollisionWithEnemiesY(enemies, this, wantToGoToY) == true) {
			this.isDying = true;
			this.isAlive = false;
			answer = true;
		}
		if (boardCollisionDetector.detectCollisionWithBoardY(board, this, wantToGoToY) == true) {
			this.isDying = true;
			this.isAlive = false;
			answer = true;
		}
		return answer;

	}
	
//	@Override
//	public void restInPeace() {
//		this.spriteArt.setDirection(SpriteArt.SPRITE_DEAD);//maybe hit image more appropriate
//		super.restInPeace();
//	}
}
