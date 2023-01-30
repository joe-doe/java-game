package game.entities;

import game.artwork.DefaultSpriteGraphics;
import game.artwork.ImageRepository;
import game.artwork.SpriteArt;
import game.baseClasses.AnimationHandler;
import game.baseClasses.BoardCollisionDetector;
import game.baseClasses.CanAnimate;
import game.baseClasses.Interactable;
import game.baseClasses.InteractablesCollisionDetector;
import game.baseClasses.Moveable;
import game.data.VitalsAndScore;
import game.play.CentralPanel;
import game.surroundings.AbstractBoard;
import game.surroundings.GameItem;

import java.util.TimerTask;
import java.util.Vector;

public class Hero extends GameItem implements Moveable, Interactable, CanAnimate {

	private int heroStartingX = 0;
	private int heroStartingY = 0;
	private float speedX = 0;
	private float jumpSpeed = 0;
	private float gravity = 0;
	private boolean onJump = false;
	private boolean fallingInProgress = false;
	private AbstractBoard board = null;
	Vector<StaticInteractable> staticInteractableGraphics = null;
	private AnimationHandler animationHandler = null;
	public boolean cutSlack = false;
	private java.util.Timer cutSomeSlackTimer = null;

	BoardCollisionDetector boardCollisionDetector = null;
	InteractablesCollisionDetector interactablesCollisionDetector = null;

	public Hero(AbstractBoard newBoard, Vector<StaticInteractable> allStaticInteractableGraphics) {
		board = newBoard;
		staticInteractableGraphics = allStaticInteractableGraphics;
		cutSomeSlackTimer = new java.util.Timer();
		boardCollisionDetector = new BoardCollisionDetector();
		interactablesCollisionDetector = new InteractablesCollisionDetector();
		animationHandler = new AnimationHandler();
		loadImages();
		setSpriteArt(new DefaultSpriteGraphics(animationHandler.stillImages, animationHandler.movingLeftImages, animationHandler.movingRightImages));
		setHeroStartingX(150);
		setHeroStartingY(CentralPanel.PANEL_HIGHT - spriteArt.getImageHeight());
		this.positionX = heroStartingX;
		this.positionY = heroStartingY;
	}

	public int getHeroStartingX() {
		return this.heroStartingX;
	}

	public int getHeroStartingY() {
		return this.heroStartingY;
	}

	public void setHeroStartingX(int heroStartingX) {
		this.heroStartingX = heroStartingX;
	}

	public void setHeroStartingY(int heroStartingY) {
		this.heroStartingY = heroStartingY;
	}

	public float calculateY(long m) {
		float newYposition = positionY;

		if (onJump == true || fallingInProgress == true) { // jump button has
															// been pressed
			jumpSpeed = jumpSpeed/* *m/8 */+ gravity;
			newYposition = positionY + jumpSpeed;

			// p("Y:" + y + " JS:" + jumpSpeed);

			// if (newYposition < jumpLevel) {
			// p("S");
			// }

		}

		return newYposition;
	}

	public void moveLeft() {
		speedX = -0.5F;
		spriteArt.setDirection(SpriteArt.SPRITE_MOVE_LEFT);
	}

	public void moveRight() {
		speedX = 0.5F;
		spriteArt.setDirection(SpriteArt.SPRITE_MOVE_RIGHT);
	}

	public void freeze(int whichDirection) {
		speedX = 0;
		switch (whichDirection) {
		case SpriteArt.SPRITE_RIGHT:
			spriteArt.setDirection(SpriteArt.SPRITE_RIGHT);
			break;
		case SpriteArt.SPRITE_LEFT:
			spriteArt.setDirection(SpriteArt.SPRITE_LEFT);
			break;
		default:
			break;

		}
	}

	public void jump() {
		if (onJump == false) {
			// spriteArt.setDirection(SpriteArt.SPRITE_JUMP_RIGHT); //if you
			// want jump sequence ...
			onJump = true;
			jumpSpeed = -12F;
			gravity = 0.5F;
		}
	}

	public void setJumpSpeed(float newJumpSpeed) {
		this.jumpSpeed = newJumpSpeed;
	}

	// ////////////////////////////// OVERRIDES ////////////////////////////////

	@Override
	public void loadImages() {
		animationHandler.movingRightImages = ImageRepository.getInstance().getImageSequenceList(ImageRepository.MOVE_RIGHT_SEQUENCE);
		animationHandler.movingLeftImages = ImageRepository.getInstance().getImageSequenceList(ImageRepository.MOVE_LEFT_SEQUENCE);

		animationHandler.stillImages.put(SpriteArt.SPRITE_LEFT, ImageRepository.getInstance().getImage(ImageRepository.IMAGE_HERO_LEFT));
		animationHandler.stillImages.put(SpriteArt.SPRITE_RIGHT, ImageRepository.getInstance().getImage(ImageRepository.IMAGE_HERO_RIGHT));
		animationHandler.stillImages.put(SpriteArt.SPRITE_DEAD, ImageRepository.getInstance().getImage(ImageRepository.IMAGE_HERO_RIP));
	}

	@Override
	public void move(long timeBetweenUpates) {
		float wantToGoToX = positionX + (speedX * timeBetweenUpates) / 3;
		float wantToGoToY = calculateY(timeBetweenUpates);
		// p("X:" + positionX + " -------- SPEED:" + speedX + " -------- TIME:"
		// + timeBetweenUpates);

		// p("X:" + wantToGoToX + " Y:" + wantToGoToY);
		if (this.isDying == false) {
			if (detectCollisionX(wantToGoToX) == false) {
				positionX = wantToGoToX;
			} else {
				if (wantToGoToX > positionX) {
					freeze(SpriteArt.SPRITE_RIGHT);
				} else {
					freeze(SpriteArt.SPRITE_LEFT);
				}
			}
			if (detectCollisionY(wantToGoToY) == false) {
				positionY = wantToGoToY;
			}
		}
	}

	@Override
	public boolean detectCollisionX(float wantToGoToX) {
		boolean answer = false;

		if (boardCollisionDetector.detectCollisionWithBoardX(board, this, wantToGoToX) == true) {
			answer = true;
		}

		if (interactablesCollisionDetector.detectCollisionWithInteractableX(staticInteractableGraphics, this, wantToGoToX) == true) {
			answer = false; // allow to move within
		}

		return answer;
	}

	@Override
	public boolean detectCollisionY(float wantToGoToY) {
		boolean answer = false;

		if (boardCollisionDetector.detectCollisionWithBoardY(board, this, wantToGoToY) == true) {
			jumpSpeed = 0;
			fallingInProgress = true;
			onJump = false;
			answer = true;
		}

		if (interactablesCollisionDetector.detectCollisionWithInteractableY(staticInteractableGraphics, this, wantToGoToY) == true) {
			answer = false; // allow to move within
		}

		return answer;

	}
	@Override
	public void changeHealthStatus(int amount) {

		if (this.isDying == false) {
				VitalsAndScore.getInstance().setCurrentLifeLevel(amount);
			}
			
			updateObservers();

			System.out.println("Life: " + VitalsAndScore.getInstance().getCurrentLifeLevel());
			if (VitalsAndScore.getInstance().getCurrentLifeLevel() <= 0) {
				restInPeace();
			}
	}

	public void cutSomeSlack() {
		if (cutSlack == true) {
			return;
		}
		cutSlack = true;

		cutSomeSlackTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				cutSlack = false;
			}
		}, 700);
	}

	@Override
	public void restInPeace() {
		this.spriteArt.setDirection(SpriteArt.SPRITE_DEAD);
		super.restInPeace();
	}

}
