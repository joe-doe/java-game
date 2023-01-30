package game.surroundings;

import game.artwork.SimpleSpriteGraphics;

import java.util.Random;
import javax.swing.JLabel;

public class StaticItem extends GameItem {

	public StaticItem(JLabel item, float posX, float posY, int width, int height) {
		this.positionX = posX;
		this.positionY = posY;
		this.levelWidth = width;
		this.levelHeight = height;
		this.luckyPick = new Random();
		setSpriteArt(new SimpleSpriteGraphics(item, null));
	}

	@Override
	public void restInPeace() {
		this.isAlive = false;
		p("Static Item Killed !");		
	}
}
