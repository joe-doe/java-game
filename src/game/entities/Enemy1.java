package game.entities;

import game.artwork.DefaultSpriteGraphics;
import game.artwork.ImageRepository;
import game.artwork.SpriteArt;
import game.baseClasses.AnimationHandler;
import game.surroundings.AbstractBoard;

public class Enemy1 extends Enemy {

	AnimationHandler animationHandler = null;
	
	public Enemy1(AbstractBoard board, Hero hero, int startingX, int startingY, float speedX, float speedY) {
		super(board, hero, startingX, startingY, speedX, speedY);
		loadImages();
		setBonusAmount(10);
		setSpriteArt(new DefaultSpriteGraphics(animationHandler.stillImages, animationHandler.movingLeftImages, animationHandler.movingRightImages));
		setX(startingX);
		setY(startingY - spriteArt.getImageHeight());
		setSpeedX(speedX);
		setSpeedY(speedY);
		spriteArt.setDirection(SpriteArt.SPRITE_RIGHT);
		this.heroLifeDecrease = 15;
	}

	@Override
	public void loadImages() {
		animationHandler = new AnimationHandler();

//		animationHandler.movingLeftImages.add(new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/taz_1.png").getImage());
//		animationHandler.movingRightImages.add(new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/taz_Right.png").getImage());
		animationHandler.stillImages.put(SpriteArt.SPRITE_LEFT, ImageRepository.getInstance().getImage(ImageRepository.IMAGE_ENEMY1_LEFT));
		animationHandler.stillImages.put(SpriteArt.SPRITE_RIGHT,ImageRepository.getInstance().getImage(ImageRepository.IMAGE_ENEMY1_RIGHT));
		animationHandler.stillImages.put(SpriteArt.SPRITE_DEAD, ImageRepository.getInstance().getImage(ImageRepository.IMAGE_ENEMY_RIP));
		
	}

}
