package game.entities;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

import game.artwork.SpriteArt;
import game.baseClasses.AnimationHandler;
import game.baseClasses.BoardCollisionDetector;
import game.baseClasses.CanAnimate;
import game.baseClasses.HeroCollisionDetector;
import game.baseClasses.Interactable;
import game.baseClasses.Moveable;
import game.data.VitalsAndScore;
import game.surroundings.AbstractBoard;
import game.surroundings.GameItem;

public abstract class Enemy extends GameItem implements Moveable, Interactable, CanAnimate {

	AbstractBoard board = null;
	Hero hero = null;
	Vector<Weapon> weapons = null;

	int bonus = 0;
	int heroLifeDecrease = 0;
	BoardCollisionDetector boardCollisionDetector = null;
	boolean boardCollision = false;
	HeroCollisionDetector heroCollisionDetector = null;
	boolean heroCollision = false;
	AnimationHandler animationHandler = null;
	Rectangle2D.Double heroRect = null;
	Rectangle2D.Double itemRect = null;

	public Enemy(AbstractBoard board, Hero hero, int startingX, int startingY, float speedX, float speedY) {
		this.board = board;
		this.hero = hero;

		boardCollisionDetector = new BoardCollisionDetector();
		heroCollisionDetector = new HeroCollisionDetector();

		// setX(startingX);
		// setY(startingY - spriteArt.getImageHeight());
		// setSpeedX(speedX);
		// setSpeedY(speedY);
	}

	public void setBonusAmount(int bonus) {
		this.bonus = bonus;
	}

	public int getBonusAmount() {
		return this.bonus;
	}

	// ////////////////////////////// OVERRIDES ////////////////////////////////

	@Override
	public boolean detectCollisionX(float wantToGoToX) {
		boolean answer = false;

		if (boardCollisionDetector.detectCollisionWithBoardX(board, this, wantToGoToX) == true) {
			boardCollision = true;
			answer = true;
		}

		if ((heroCollisionDetector.detectCollisionWithHeroX(hero, this, wantToGoToX) == true) && hero.cutSlack == false) {
			heroCollision = true;
			hero.cutSomeSlack();
			answer = true;
		}

		return answer;
	}

	@Override
	public boolean detectCollisionY(float wantToGoToY) {
		boolean answer = false;

		if (boardCollisionDetector.detectCollisionWithBoardY(board, this, wantToGoToY) == true) {
			boardCollision = true;
			answer = true;
		}

		if ((heroCollisionDetector.detectCollisionWithHeroY(hero, this, wantToGoToY) == true) && hero.cutSlack == false) {
			heroCollision = true;
			hero.cutSomeSlack();
			answer = true;
		}

		return answer;

	}

	@Override
	public void move(long timeBetweenUpates) {
		float wantToGoToX = positionX + (speedX * timeBetweenUpates) / 3;
		// float wantToGoToY = positionY + (speedY * timeBetweenUpates) / 3;

		// p("X:" + positionX + " -------- SPEED:" + speedX + " -------- TIME:"
		// + timeBetweenUpates);
		// p("X:" + wantToGoToX + " Y:" + wantToGoToY);
		if (this.isDying == false) {
			if (detectCollisionX(wantToGoToX) == false) {
				setX(wantToGoToX);
			} else {
				if (boardCollision == true) {
					turnAround();
					boardCollision = false;
				} else if (heroCollision == true) {
					turnAround();
					hero.changeHealthStatus(-15);
					heroCollision = false;
				}
			}
		}

		// if (detectCollisionY(wantToGoToY) == false) {
		// setY(wantToGoToY);
		// } else {
		// if (boardCollision == true) {
		// turnAround();
		// } else if (heroCollision == true) {
		// // killHero();
		// }
		// }
	}

	private void turnAround() {

		if (getSpeedX() < 0) {
			setSpeedX(-getSpeedX());
			spriteArt.setDirection(SpriteArt.SPRITE_RIGHT);
		} else if (getSpeedX() > 0) {
			setSpeedX(-getSpeedX());
			spriteArt.setDirection(SpriteArt.SPRITE_LEFT);
		} else if (getSpeedY() > 0) {
			setSpeedY(-getSpeedY());
			spriteArt.setDirection(SpriteArt.SPRITE_DOWN);
		} else if (getSpeedX() < 0) {
			setSpeedY(-getSpeedY());
			spriteArt.setDirection(SpriteArt.SPRITE_LEFT);
		}

	}

	public void changeHealthStatus(int amount) {
			VitalsAndScore.getInstance().setCurrentLifeLevel(amount);
			updateObservers();
	}

	@Override
	public void restInPeace() {
		this.spriteArt.setDirection(SpriteArt.SPRITE_DEAD);//Enemy dead icon maybe more appropriate
		super.restInPeace();
	}

}
