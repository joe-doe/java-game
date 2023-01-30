package game.artwork;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SimpleSpriteGraphics extends SpriteArt {

	Image imageWhenInteracts = null;
	Image imageIdle = null;

	public SimpleSpriteGraphics(JLabel imageContainer, JLabel imageWhenInteracts) {
		this.imageIdle = ((ImageIcon) imageContainer.getIcon()).getImage();
		if (imageWhenInteracts != null) {
			this.imageWhenInteracts = ((ImageIcon) imageWhenInteracts.getIcon()).getImage();
		}
		setCurrentImage(imageIdle);
	}

	@Override
	public void setDirection(int direction) {

		switch (direction) {
		case SpriteArt.SPRITE_IDLE:
			setCurrentImage(imageIdle);
			break;
		case SpriteArt.SPRITE_INTERACTS:
			if (imageWhenInteracts != null) {
				setCurrentImage(imageWhenInteracts);
			}
			break;
		default:
			setCurrentImage(imageIdle);
			break;
		}
	}

	// @Override
	// public void setImage(int imageNumber) {
	// }

	@Override
	public int getDirection() {
		return 0;
	}

	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}

}
