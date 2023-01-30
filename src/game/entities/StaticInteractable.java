package game.entities;

import java.util.Random;
import java.util.TimerTask;

import javax.swing.JLabel;

import game.artwork.SimpleSpriteGraphics;
import game.artwork.SpriteArt;
import game.surroundings.GameItem;

public class StaticInteractable extends GameItem {

	StaticInteractableAttributes attributes = null;
	JLabel imageWhenIteracts = null;
	
	public StaticInteractable(JLabel item, JLabel imageWhenIteracts, int type, int amount, boolean mustDiapear, float posX, float posY, int width, int height) {
		this.positionX = posX;
		this.positionY = posY;
		this.levelWidth = width;
		this.levelHeight = height;
		this.luckyPick = new Random();
		this.imageWhenIteracts = imageWhenIteracts;
		setSpriteArt(new SimpleSpriteGraphics(item, imageWhenIteracts));
		attributes = new StaticInteractableAttributes(type, amount, mustDiapear);
	}

	public StaticInteractableAttributes getAttributes() {
		return this.attributes;
	}

	public int getType(){
		return this.attributes.type;
	}

	public int getAmount(){
		return this.attributes.amount;
	}
	
	public boolean mustDisapear(){
		return this.attributes.diaspearAfterInteraction;
	}
	
	@Override
	public void restInPeace() {
		this.spriteArt.setDirection(SpriteArt.SPRITE_INTERACTS);
		this.isDying = true;
		if(this.imageWhenIteracts == null){
			isAlive = false;
		}
		triggerDeath.schedule(new TimerTask() {

			@Override
			public void run() {
				isAlive = false;
			}
		}, 700);
	}
}
