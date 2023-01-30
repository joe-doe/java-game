package game.artwork;

import java.awt.Image;

public abstract  class SpriteArt {
	public static final int SPRITE_LEFT = 1;
	public static final int SPRITE_RIGHT = 2;
	public static final int SPRITE_UP = 3;
	public static final int SPRITE_DOWN = 4;
	public static final int SPRITE_MOVE_RIGHT = 5;
	public static final int SPRITE_MOVE_LEFT = 6;
	public static final int SPRITE_JUMP_RIGHT = 7;
	public static final int SPRITE_JUMP_LEFT = 8;
	public static final int SPRITE_DEAD = 9;
	public static final int SPRITE_BLINK = 10;
	public static final int SPRITE_IDLE = 11;
	public static final int SPRITE_INTERACTS = 12;
	
	public Image spriteImage = null;
	public boolean stopThread = false;
	private int imageWidth = 0;
	private int imageHeight = 0;

	public void setCurrentImage(Image newImage) {
		if(!(newImage.equals(spriteImage))){
			this.spriteImage = newImage;
			this.imageWidth = spriteImage.getWidth(null);
			this.imageHeight = spriteImage.getHeight(null);
		}
	}
	
	public Image getImage() {
		return spriteImage;
	}

	public int getImageWidth() {
		return this.imageWidth;
	}

	public int getImageHeight() {
		return this.imageHeight;
	}
	
	public abstract void setDirection(int direction);
	public abstract int getDirection();
//	public abstract void setImage(int imageNumber);
	public abstract int getStatus();

}
