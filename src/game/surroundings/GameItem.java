package game.surroundings;

import game.artwork.SpriteArt;
import game.data.VitalsAndScore;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameItem extends Observable {

	public SpriteArt spriteArt = null;
	protected float speedX = 0;
	protected float speedY = 0;
	protected float positionX = 0;
	protected float positionY = 0;
	protected int levelWidth = 0;
	protected int levelHeight = 0;
	protected Random luckyPick = null;
	protected java.util.Timer triggerDeath = null;
	public boolean isAlive = true;
	public boolean isDying = false;

	public GameItem() {
		this.triggerDeath = new Timer();
	}

	public void setSpriteArt(SpriteArt spriteArt) {
		this.spriteArt = spriteArt;
	}

	public float getSpeedX() {
		return this.speedX;
	}

	public void setSpeedX(float speed) {
		this.speedX = speed;
	}

	public float getSpeedY() {
		return this.speedY;
	}

	public void setSpeedY(float speed) {
		this.speedY = speed;
	}

	public float getX() {
		return this.positionX;
	}

	public void setX(float newXpos) {
		this.positionX = newXpos;
	}

	public float getY() {
		return this.positionY;
	}

	public void setY(float newYpos) {
		this.positionY = newYpos;
	}

	public Rectangle2D.Double getItemRectangle() {
		return new Rectangle2D.Double(this.positionX, this.positionY, spriteArt.getImageWidth(), spriteArt.getImageHeight());
	}

	public void p(String printThis) {
		System.out.println(printThis);
	}

	public void restInPeace() {
		System.out.println("RIP - GameItem 1");
		this.isDying = true;
		triggerDeath.schedule(new TimerTask() {

			@Override
			public void run() {
				isAlive = false;
			}
		}, 800);

	}

	public void delayCollision(){
		
	}
	
	public void changeHealthStatus(int amount) {
//		Draft for gameItem to have health (enemies for example)
//		need to add health attributes to gameItem
//		
//		if (this.isDying == false) {
//				VitalsAndScore.getInstance().setCurrentLifeLevel(amount);
//			}
//
//			updateObservers();
//
//			System.out.println("Life: " + VitalsAndScore.getInstance().getCurrentLifeLevel());
//			if (VitalsAndScore.getInstance().getCurrentLifeLevel() <= 0) {
//				restInPeace();
//			}
//		}
	}

	public void changeScore(int amount) {
		VitalsAndScore.getInstance().setScore(amount);
		updateObservers();
	}

	public void updateObservers() {
		setChanged();
		notifyObservers(VitalsAndScore.getInstance());
	}
}