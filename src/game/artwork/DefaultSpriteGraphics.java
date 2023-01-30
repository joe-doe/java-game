package game.artwork;

import java.awt.Image;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class DefaultSpriteGraphics extends SpriteArt {

	public List<Image> movingLeftImages = null;
	public List<Image> movingRightImages = null;
	public HashMap<Integer, Image> stillImages = null;

	private boolean walkingLeft = false;
	private boolean walkingRight = false;
	private boolean jumpingLeft = false;
	private boolean jumpingRight = false;
	private boolean standingLeft = false;
	private boolean standingRight = false;

	private int direction = SpriteArt.SPRITE_RIGHT;

	public DefaultSpriteGraphics(HashMap<Integer, Image> stillImages, List<Image> movingLeftImages, List<Image> movingRightImages) {
		this.movingLeftImages = movingLeftImages;
		this.movingRightImages = movingRightImages;
		this.stillImages = stillImages;

		setCurrentImage(stillImages.get(SpriteArt.SPRITE_RIGHT));
		renderImages();
	}

	private void renderImages() {
		Thread animateThis = new Thread(new Runnable() {

			@Override
			public void run() {
				while (stopThread == false) {
					switch (direction) {
					case SpriteArt.SPRITE_LEFT:
						setCurrentImage(stillImages.get(SpriteArt.SPRITE_LEFT));
						break;
					case SpriteArt.SPRITE_RIGHT:
						setCurrentImage(stillImages.get(SpriteArt.SPRITE_RIGHT));
						break;
					case SpriteArt.SPRITE_UP:
						setCurrentImage(stillImages.get(SpriteArt.SPRITE_UP));
						break;
					case SpriteArt.SPRITE_DOWN:
						setCurrentImage(stillImages.get(SpriteArt.SPRITE_DOWN));
						break;
					// case SpriteArt.SPRITE_MOVE_LEFT:
					// // walkingLeft = true;
					// // animateLeft();
					// break;
					// case SpriteArt.SPRITE_MOVE_RIGHT:
					// // walkingRight = true;
					// // animateRight();
					// break;
					case SpriteArt.SPRITE_DEAD:
						setCurrentImage(stillImages.get(SpriteArt.SPRITE_DEAD));// .getScaledInstance(spriteImage.getWidth(null),
																				// spriteImage.getHeight(null),
																				// Image.SCALE_DEFAULT);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					default:
						break;
					}

					while (walkingLeft == true) {
						 System.out.println("Move Left");
						for (int i = 0; i < movingLeftImages.size(); i++) {
							if (walkingLeft == false) {
								setCurrentImage(stillImages.get(SpriteArt.SPRITE_LEFT));
								break;
							}
							setCurrentImage(movingLeftImages.get(i));
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
					}

					while (walkingRight == true) {
						 System.out.println("Move right");
						for (int i = 0; i < movingRightImages.size(); i++) {
							if (walkingRight == false) {
								setCurrentImage( stillImages.get(SpriteArt.SPRITE_RIGHT));
								break;
							}
							setCurrentImage(movingRightImages.get(i));
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
					}

					while (jumpingLeft == true) {
						// System.out.println("Jump Left");
						for (int i = 0; i < movingLeftImages.size(); i++) {
							if (jumpingLeft == false) {
								setCurrentImage(stillImages.get(SpriteArt.SPRITE_LEFT));
								break;
							}
							setCurrentImage(movingLeftImages.get(i));
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					while (jumpingRight == true) {
						// System.out.println("Jump Right");
						for (int i = 0; i < movingRightImages.size(); i++) {
							if (jumpingRight == false) {
								setCurrentImage(stillImages.get(SpriteArt.SPRITE_RIGHT));
								break;
							}
							setCurrentImage(movingRightImages.get(i));
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Thread: 'Default Sprite Graphics' end gracefully");
			}
		});
		animateThis.start();
		System.out.println("Thread: 'Default Sprite Graphics' started:" + animateThis.getName());
	}

//	@Override
//	public void setImage(int imageNumber) {
//	}

	@Override
	public void setDirection(int direction) {
		// System.out.println("D: " + direction);

		switch (direction) {
		case SpriteArt.SPRITE_MOVE_LEFT:
			walkingLeft = true;
			walkingRight = false;
			jumpingLeft = false;
			jumpingRight = false;
			standingLeft = false;
			standingRight = false;
			break;
		case SpriteArt.SPRITE_MOVE_RIGHT:
			walkingRight = true;
			walkingLeft = false;
			jumpingLeft = false;
			jumpingRight = false;
			standingLeft = false;
			standingRight = false;
			break;
		case SpriteArt.SPRITE_JUMP_LEFT:
			walkingLeft = false;
			walkingRight = false;
			jumpingLeft = true;
			jumpingRight = false;
			standingLeft = false;
			standingRight = false;
			break;
		case SpriteArt.SPRITE_JUMP_RIGHT:
			walkingLeft = false;
			walkingRight = false;
			jumpingLeft = false;
			jumpingRight = true;
			standingLeft = false;
			standingRight = false;
			break;
		case SpriteArt.SPRITE_LEFT:
			walkingLeft = false;
			walkingRight = false;
			jumpingLeft = false;
			jumpingRight = false;
			standingLeft = true;
			standingRight = false;
			break;
		case SpriteArt.SPRITE_RIGHT:
			walkingLeft = false;
			walkingRight = false;
			jumpingLeft = false;
			jumpingRight = false;
			standingLeft = false;
			standingRight = true;
			break;
		}
		this.direction = direction;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setStatus(int thisIsTrue) {

	}

	@Override
	public int getStatus() {
		int returnThis = SpriteArt.SPRITE_RIGHT;

		if (walkingLeft == true) {
			returnThis = SpriteArt.SPRITE_MOVE_LEFT;
		} else if (walkingRight == true) {
			returnThis = SpriteArt.SPRITE_MOVE_RIGHT;
		} else if (jumpingLeft == true) {
			returnThis = SpriteArt.SPRITE_JUMP_LEFT;
		} else if (jumpingRight == true) {
			returnThis = SpriteArt.SPRITE_JUMP_RIGHT;
		} else if (standingLeft == true) {
			returnThis = SpriteArt.SPRITE_LEFT;
		} else if (standingRight == true) {
			returnThis = SpriteArt.SPRITE_RIGHT;
		} else {
			returnThis = SpriteArt.SPRITE_RIGHT;
		}
		return returnThis;
	}
}