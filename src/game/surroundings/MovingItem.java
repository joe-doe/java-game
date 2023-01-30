package game.surroundings;

import game.artwork.SimpleSpriteGraphics;
import game.baseClasses.Moveable;

import java.util.Random;
import javax.swing.JLabel;

public class MovingItem extends GameItem implements Moveable {

	public boolean stopThread = false;

	public MovingItem(JLabel item, float posX, float posY, float speedX, float speedY, int width, int height) {
		this.positionX = posX;
		this.positionY = posY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.levelWidth = width;
		this.levelHeight = height;
		this.luckyPick = new Random();
		setSpriteArt(new SimpleSpriteGraphics(item, null));
		move(100L);
	}

	@Override
	public void move(long timeBetweenUpates) {
		Thread animation = new Thread(new Runnable() {

			@Override
			public void run() {
				while (stopThread == false) {
					if (positionX < levelWidth + 2) {
						positionX = positionX + getSpeedX();
					} else {
						positionX = -200 * (luckyPick.nextInt(3) + 1);
					}

					if (positionY < levelHeight + 2) {
						positionY = positionY + getSpeedY();
					} else {
						positionY = -200 * (luckyPick.nextInt(3) + 1);
					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		animation.start();
	}

	@Override
	public void restInPeace() {
		this.isAlive = false;
		p("Moving Item Killed !");
	}
}
