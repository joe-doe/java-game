package game.play;

import game.artwork.ImageRepository;
import game.artwork.SpriteArt;
import game.data.VitalsAndScore;
import game.entities.Enemies;
import game.entities.Enemy;
import game.entities.Hero;
import game.entities.StaticInteractable;
import game.entities.Weapon;
import game.sound.SampledPlayer;
import game.surroundings.AbstractBoard;
import game.surroundings.Environment;
import game.surroundings.MovingItem;
import game.surroundings.StaticItem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class CentralPanel extends JInternalFrame implements ActionListener {

	public static final int PANEL_WIDTH = 800;
	public static final int PANEL_HIGHT = 600;
	private static int switchImagesForBlink = 0;

	private Timer panelRefreshTimer = null;
	private java.util.Timer shootingNormilizerTimer = null;

	private Image background = null;
	private Image background2 = null;
	private Image grass = null;

	private AbstractBoard board = null;

	public Hero hero = null;
	public Enemies enemies = null;
	private Enemy currentEnemy = null;
	private Vector<Weapon> weapons = null;
	private Weapon currentWeapon = null;

	private long timeBetweenUpates = 0;
	private long lastCallTime = 0;
	private Environment environment = null;
	private Dimension levelDimension = null;
	private int heroX = 0;
	private int heroY = 0;
	private int backgroundSpeed = 0;
	private Graphics2D g2d = null;

	private int mainBackgroundEnd = 0;
	private int treesBackgroundEnd = 0;

	private SampledPlayer sp = null;
	private double volume = 0.1;

	private boolean canShoot = true;
	private boolean gameOver = false;

	private CentralFrame parent = null;

	/**
	 * Create the panel.
	 */
	public CentralPanel(CentralFrame parent) {
		setLayout(null);
		setBounds(12, 12, PANEL_WIDTH, PANEL_HIGHT);
		setFocusable(true);
		setBackground(new Color(0, 0, 0, 0));
		this.parent = parent;
		startPlay();
	}

	private void startPlay() {
		background = ImageRepository.getInstance().getImage(ImageRepository.IMAGE_BACKGROUND_1);
		background2 = ImageRepository.getInstance().getImage(ImageRepository.IMAGE_BACKGROUND_2);
		grass = ImageRepository.getInstance().getImage(ImageRepository.IMAGE_BACKGROUND_3);

		levelDimension = new Dimension((int) grass.getWidth(null), (int) grass.getHeight(null));
		board = new AbstractBoard(levelDimension);
		environment = new Environment(levelDimension);
		weapons = new Vector<Weapon>();
		hero = new Hero(board, environment.getStaticInteractableItems());
		enemies = new Enemies(board, hero);

		if (panelRefreshTimer != null) {
			panelRefreshTimer.restart();
		} else {
			panelRefreshTimer = new Timer(30, this);
			panelRefreshTimer.start();
		}
		shootingNormilizerTimer = new java.util.Timer();
		startKeyListener();
//		startSound();
		resetVitals();
	}

	private void resetVitals() {
		VitalsAndScore.getInstance().reset();
	}

	public void killAllThreads() {
		if (hero.isAlive) {
			hero.spriteArt.stopThread = true;
		}
		for (int i = 0; i < enemies.enemiesVector.size(); i++) {
			enemies.enemiesVector.get(i).spriteArt.stopThread = true;
		}

		for (int i = 0; i < environment.backgroundGraphics.size(); i++) {
			environment.backgroundGraphics.get(i).stopThread = true;
		}
		panelRefreshTimer.stop();

	}

	// public void restartPlay() {
	// hero.deleteObservers();
	//
	// panelRefreshTimer = null;
	// shootingNormilizerTimer = null;
	//
	// background = null;
	// background2 = null;
	// grass = null;
	//
	// board = null;
	//
	// // hero = null;
	// // enemies = null;
	// currentEnemy = null;
	// weapons = null;
	// currentWeapon = null;
	//
	// timeBetweenUpates = 0;
	// lastCallTime = 0;
	//
	// environment = null;
	// levelDimension = null;
	// heroX = 0;
	// heroY = 0;
	// backgroundSpeed = 0;
	// g2d = null;
	//
	// mainBackgroundEnd = 0;
	// treesBackgroundEnd = 0;
	//
	// sp = null;
	// volume = 0.1;
	//
	// canShoot = true;
	// gameOver = false;
	//
	// startPlay();
	// parent.hookObservers();
	// parent.resetVitalsAndScorePanel();
	// }

	private void normilizeShooting() {
		canShoot = false;

		shootingNormilizerTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				canShoot = true;
			}
		}, 200);
	}

	private void startSound() {
		// sp = new
		// SampledPlayer("/data/DevelopersCorner/Eclipse/JavaGame_2/sounds/01_Beast-Playtune4.wav");
		// sp.playSampled();
	}

	private void changeBorder(boolean mustGrow) {
		final boolean grow = mustGrow;
		Thread moveit = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 24; i++) {
					if (grow == true) {
						setBounds(getX() - (i), getY() - (i), getWidth() + (2 * i), getHeight() + (2 * i));
					} else {
						setBounds(getX() + (i), getY() + (i), getWidth() - (2 * i), getHeight() - (2 * i));
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		moveit.start();

	}

	private void startKeyListener() {

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// System.out.println("Time between releases:"+(e.getWhen()-previousTime));

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//					hero.freeze(SpriteArt.SPRITE_LEFT);
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//					hero.freeze(SpriteArt.SPRITE_RIGHT);
				}

				if (e.getKeyCode() == KeyEvent.VK_X) {
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					hero.freeze(SpriteArt.SPRITE_RIGHT);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					parent.startOver();
				}

//				if (e.getKeyCode() == KeyEvent.VK_G) {
//					((CentralPanel) e.getSource()).changeBorder(true);
//				}
//
//				if (e.getKeyCode() == KeyEvent.VK_S) {
//					((CentralPanel) e.getSource()).changeBorder(false);
//				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					hero.moveLeft();
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					hero.moveRight();
				}

				if (e.getKeyCode() == KeyEvent.VK_Z) {
					hero.jump();
				}

//				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//					// hero.freeze();
//				}
//				if (e.getKeyCode() == KeyEvent.VK_1) {
//					System.out.println("Vol " + volume);
//					volume = volume + 0.1;
//					sp.setVolume(volume);
//				}
//				if (e.getKeyCode() == KeyEvent.VK_0) {
//					System.out.println("Vol " + volume);
//					volume = volume - 0.1;
//					sp.setVolume(volume);
//				}
				if (e.getKeyCode() == KeyEvent.VK_X) {
					if (canShoot == true) {
						// System.out.println("Fire !");
						normilizeShooting();
						int whichDirection = hero.spriteArt.getStatus();
						if ( whichDirection == SpriteArt.SPRITE_LEFT || whichDirection == SpriteArt.SPRITE_JUMP_LEFT || whichDirection == SpriteArt.SPRITE_MOVE_LEFT){
							weapons.add(new Weapon(new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_FIREBALL_LEFT)), board, enemies, (int) hero.getX() - 20, (int) hero
									.getY() + ((int) hero.spriteArt.getImageHeight() / 3), -0.4F, 0));
						} else if ( whichDirection == SpriteArt.SPRITE_RIGHT || whichDirection == SpriteArt.SPRITE_JUMP_RIGHT || whichDirection == SpriteArt.SPRITE_MOVE_RIGHT){

							weapons.add(new Weapon(new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_FIREBALL_RIGHT)), board, enemies, (int) hero.getX()
									+ (int) hero.spriteArt.getImageWidth() - 30, (int) hero.getY() + ((int) hero.spriteArt.getImageHeight() / 3), 0.4F, 0));
						}
					} else {
						// System.out.println("Can't Fire !");
					}
				}

			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		timeBetweenUpates = System.currentTimeMillis() - lastCallTime;
		lastCallTime = System.currentTimeMillis();
		if (timeBetweenUpates > 10000) {
			timeBetweenUpates = 10;
		}
		if (gameOver == false) {
			hero.move(timeBetweenUpates);
		}
		for (int i = 0; i < enemies.enemiesVector.size(); i++) {
			enemies.enemiesVector.get(i).move(timeBetweenUpates);
		}

		for (int i = 0; i < weapons.size(); i++) {
			if (weapons.get(i).getLifeTime() < 800) {
				weapons.get(i).move(timeBetweenUpates);
			} else {
				weapons.remove(weapons.get(i));
			}
		}

		repaint();
	}

	public int groundRelativeX(double value) {
		return (int) ((hero.getHeroStartingX() - hero.getX()) + value);
	}

	public int groundRelativeY(double value) {
		return (int) ((hero.getHeroStartingY() - hero.getY()) + value);
	}

	@Override
	public void paint(Graphics g) {
		// System.out.println("W:"+board.image.getWidth(null)+" H:"+board.image.getHeight(null));
		// System.out.println("W:"+board.rect.width+" H:"+board.rect.height+" ");

		g2d = (Graphics2D) g;

		heroX = (int) hero.getX();
		heroY = (int) hero.getY();
		// System.out.println("HERO x:"+heroX+" y:"+heroY);

		int startScrolling = hero.getHeroStartingX();
		int stopScrollingAt = heroX + PANEL_WIDTH - hero.getHeroStartingX();
		double end = levelDimension.getWidth();

		// /////////////////////////////////////////////////////
		// /////////////// 0 --> startScrolling ////////////////
		// /////////////////////////////////////////////////////
		if (heroX <= startScrolling) {
			// ============= Move backgrounds =============
			g2d.drawImage(background, 0, 0, null);
			g2d.drawImage(background2, 0, 0, null);
			g2d.drawImage(grass, 0, 0, null);

			// ============= Move hero =============
			if (hero.isAlive) {
				if (hero.cutSlack == true) {
					if (switchImagesForBlink <= 3) {
						switchImagesForBlink++;
					} else if (switchImagesForBlink <= 6 && switchImagesForBlink > 3) {
						g2d.drawImage(hero.spriteArt.getImage(), heroX, heroY, null);
						switchImagesForBlink++;
					} else if (switchImagesForBlink == 7) {
						switchImagesForBlink = 0;
					}
				} else {
					g2d.drawImage(hero.spriteArt.getImage(), heroX, heroY, null);
				}
			} else {
				if (gameOver == false) {
					gameOver();
				}
			}
			// ============= Move enemies =============
			for (int i = 0; i < enemies.enemiesVector.size(); i++) {
				currentEnemy = enemies.enemiesVector.get(i);
				if (currentEnemy.isAlive) {
					g2d.drawImage(currentEnemy.spriteArt.getImage(), (int) currentEnemy.getX(), (int) currentEnemy.getY(), null);
				} else {
					hero.changeScore(currentEnemy.getBonusAmount());
					enemies.removeEnemy(currentEnemy);
				}
			}

			// ============= Move weapons =============
			for (int i = 0; i < weapons.size(); i++) {
				currentWeapon = weapons.get(i);
				if (currentWeapon.isAlive) {
					g2d.drawImage(currentWeapon.spriteArt.getImage(), (int) currentWeapon.getX(), (int) currentWeapon.getY(), null);
				} else {
					weapons.remove(currentWeapon);
				}
			}

			// ============= Move backgroundItems =============
			for (MovingItem bi : environment.backgroundGraphics) {
				int biX = (int) bi.getX();
				int biY = (int) bi.getY();
				int imgW = bi.spriteArt.getImageWidth() + 100;
				int imgH = bi.spriteArt.getImageHeight() + 100;
				// System.out.println("cloud X: " + biX + "      hero X:" +
				// heroX);

				if (biX / 2 < stopScrollingAt + imgW) {
					g2d.drawImage(bi.spriteArt.getImage(), (biX - hero.getHeroStartingX()) / 2, biY, null);
				} else {
					bi.setX((heroX - hero.getHeroStartingX()) / 2 - imgW);
				}
			}

			// ============= Draw staticItems =============
			for (int i = 0; i < environment.staticGraphics.size(); i++) {
				StaticItem staticItem = environment.staticGraphics.get(i);
				g2d.drawImage(staticItem.spriteArt.getImage(), (int) staticItem.getX(), (int) staticItem.getY(), null);
			}

			// ============= Draw staticInteractableItems =============
			for (int i = 0; i < environment.staticInteractableGraphics.size(); i++) {
				StaticInteractable staticInteractableItem = environment.staticInteractableGraphics.get(i);
				if (staticInteractableItem.isAlive) {
					g2d.drawImage(staticInteractableItem.spriteArt.getImage(), (int) staticInteractableItem.getX(), (int) staticInteractableItem.getY(), null);
				} else {
					environment.removeStaticInteractableComponent(staticInteractableItem);
				}
			}
		}

		// /////////////////////////////////////////////////////
		// /////////////// stopScrolling --> end ///////////////
		// /////////////////////////////////////////////////////
		else if (stopScrollingAt >= end) {
			// ============= Move backgrounds =============
			backgroundSpeed = groundRelativeX(0) / 3;
			if (mainBackgroundEnd == 0) {
				mainBackgroundEnd = backgroundSpeed;
			}

			backgroundSpeed = groundRelativeX(0) / 2;
			if (treesBackgroundEnd == 0) {
				treesBackgroundEnd = backgroundSpeed;
			}
			g2d.drawImage(background, mainBackgroundEnd, 0, null);
			g2d.drawImage(background2, treesBackgroundEnd, 0, null);
			g2d.drawImage(grass, PANEL_WIDTH - (int) levelDimension.getWidth(), 0, null);

			// ============= Move hero =============
			if (hero.isAlive) {
				if (hero.cutSlack == true) {
					if (switchImagesForBlink <= 3) {
						switchImagesForBlink++;
					} else if (switchImagesForBlink <= 6 && switchImagesForBlink > 3) {
						g2d.drawImage(hero.spriteArt.getImage(), heroX + PANEL_WIDTH - (int) levelDimension.getWidth(), heroY, null);
						switchImagesForBlink++;
					} else if (switchImagesForBlink == 7) {
						switchImagesForBlink = 0;
					}
				} else {
					g2d.drawImage(hero.spriteArt.getImage(), heroX + PANEL_WIDTH - (int) levelDimension.getWidth(), heroY, null);
				}
			} else {
				if (gameOver == false) {
					gameOver();
				}
			}

			// ============= Move enemies =============
			for (int i = 0; i < enemies.enemiesVector.size(); i++) {
				currentEnemy = enemies.enemiesVector.get(i);
				if (currentEnemy.isAlive) {
					g2d.drawImage(currentEnemy.spriteArt.getImage(), (int) currentEnemy.getX() + PANEL_WIDTH - (int) levelDimension.getWidth(), (int) currentEnemy.getY(), null);
				} else {
					hero.changeScore(currentEnemy.getBonusAmount());
					enemies.removeEnemy(currentEnemy);
				}
			}
			// ============= Move backgroundItems =============
			for (MovingItem bi : environment.backgroundGraphics) {
				int biX = (int) bi.getX();
				int biY = (int) bi.getY();
				int imgW = bi.spriteArt.getImageWidth() + 100;
				int imgH = bi.spriteArt.getImageHeight() + 100;
				// System.out.println("cloud X: " + biX + "      hero X:" +
				// heroX);

				if (biX / 2 < stopScrollingAt + imgW) {
					// System.out.println("cloud X: " + biX +
					// "      hero X:" +
					// heroX);
					g2d.drawImage(bi.spriteArt.getImage(), (biX + (PANEL_WIDTH - (int) levelDimension.getWidth() - hero.getHeroStartingX())) / 2, biY, null);

				} else {
					bi.setX((heroX - hero.getHeroStartingX()) / 2 - imgW);
				}
			}
			// ============= Draw staticItems =============
			for (int i = 0; i < environment.staticGraphics.size(); i++) {
				StaticItem staticItem = environment.staticGraphics.get(i);
				g2d.drawImage(staticItem.spriteArt.getImage(), (int) staticItem.getX() + PANEL_WIDTH - (int) levelDimension.getWidth(), (int) staticItem.getY(), null);
			}

			// ============= Draw staticInteractableItems =============
			for (int i = 0; i < environment.staticInteractableGraphics.size(); i++) {
				StaticInteractable staticInteractableItem = environment.staticInteractableGraphics.get(i);
				if (staticInteractableItem.isAlive) {
					g2d.drawImage(staticInteractableItem.spriteArt.getImage(), (int) staticInteractableItem.getX() + PANEL_WIDTH - (int) levelDimension.getWidth(),
							(int) staticInteractableItem.getY(), null);
				} else {
					environment.removeStaticInteractableComponent(staticInteractableItem);
				}
			}

			// ============= Move weapons =============
			for (int i = 0; i < weapons.size(); i++) {
				currentWeapon = weapons.get(i);
				if (currentWeapon.isAlive) {
					g2d.drawImage(currentWeapon.spriteArt.getImage(), (int) currentWeapon.getX() + PANEL_WIDTH - (int) levelDimension.getWidth(), (int) currentWeapon.getY(), null);
				} else {
					weapons.remove(currentWeapon);
				}
			}
		}

		// /////////////////////////////////////////////////////
		// //////// startScrolling --> stopScrolling //////////
		// /////////////////////////////////////////////////////
		else {
			// ============= Move backgrounds =============
			g2d.drawImage(background, groundRelativeX(0) / 3, 0, null);
			g2d.drawImage(background2, groundRelativeX(0) / 2, 0, null);
			g2d.drawImage(grass, groundRelativeX(0), 0, null);

			// ============= Move hero =============
			if (hero.isAlive) {
				if (hero.cutSlack == true) {
					if (switchImagesForBlink <= 3) {
						switchImagesForBlink++;
					} else if (switchImagesForBlink <= 6 && switchImagesForBlink > 3) {
						g2d.drawImage(hero.spriteArt.getImage(), hero.getHeroStartingX(), heroY, null);
						switchImagesForBlink++;
					} else if (switchImagesForBlink == 7) {
						switchImagesForBlink = 0;
					}
				} else {
					g2d.drawImage(hero.spriteArt.getImage(), hero.getHeroStartingX(), heroY, null);
				}

			} else {
				if (gameOver == false) {
					gameOver();
				}
			}

			// ============= Move enemies =============
			for (int i = 0; i < enemies.enemiesVector.size(); i++) {
				currentEnemy = enemies.enemiesVector.get(i);
				if (currentEnemy.isAlive) {
					g2d.drawImage(currentEnemy.spriteArt.getImage(), groundRelativeX(currentEnemy.getX()), (int) currentEnemy.getY(), null);
				} else {
					hero.changeScore(currentEnemy.getBonusAmount());
					enemies.removeEnemy(currentEnemy);
				}
			}
			// ============= Move backgroundItems =============
			for (MovingItem bi : environment.backgroundGraphics) {
				int biX = (int) bi.getX();
				int biY = (int) bi.getY();
				int imgW = bi.spriteArt.getImageWidth() + 100;
				int imgH = bi.spriteArt.getImageHeight() + 100;
				// System.out.println("cloud X: " + biX + "      hero X:" +
				// heroX);

				if (biX / 2 < stopScrollingAt + imgW) {
					g2d.drawImage(bi.spriteArt.getImage(), (biX - heroX) / 2, biY, null);
				} else {
					bi.setX((heroX - hero.getHeroStartingX()) / 2 - imgW);
				}
			}

			// ============= Draw staticItems =============
			for (int i = 0; i < environment.staticGraphics.size(); i++) {
				StaticItem staticItem = environment.staticGraphics.get(i);
				g2d.drawImage(staticItem.spriteArt.getImage(), groundRelativeX((int) staticItem.getX()), (int) staticItem.getY(), null);
			}

			// ============= Draw staticInteractableItems =============
			for (int i = 0; i < environment.staticInteractableGraphics.size(); i++) {
				StaticInteractable staticInteractableItem = environment.staticInteractableGraphics.get(i);
				if (staticInteractableItem.isAlive) {
					g2d.drawImage(staticInteractableItem.spriteArt.getImage(), groundRelativeX((int) staticInteractableItem.getX()), (int) staticInteractableItem.getY(), null);
				} else {
					environment.removeStaticInteractableComponent(staticInteractableItem);
				}
			}

			// ============= Move weapons =============
			for (int i = 0; i < weapons.size(); i++) {
				currentWeapon = weapons.get(i);
				if (currentWeapon.isAlive) {
					g2d.drawImage(currentWeapon.spriteArt.getImage(), groundRelativeX(currentWeapon.getX()), (int) currentWeapon.getY(), null);
				} else {
					weapons.remove(currentWeapon);
				}
			}
		}
//
//		// Debug shapes collision
//		if (CentralFrame.DEBUGGING) {
//			g2d.setColor(Color.red);
//
//			// Hero Box
//			if (heroX + PANEL_WIDTH - hero.getHeroStartingX() >= levelDimension.getWidth()) {
//				g2d.drawRect(heroX - (int) levelDimension.getWidth(), heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
//			} else if (heroX <= hero.getHeroStartingX()) {
//				g2d.drawRect(heroX, heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
//			} else {
//				g2d.drawRect(hero.getHeroStartingX(), heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
//			}
//
//			// Enemy box
//			// if (heroX + PANEL_WIDTH - hero.getHeroStartingX() >=
//			// levelDimension.getWidth()) {
//			// g2d.drawRect(Hero.heroStartingX + (heroX + PANEL_WIDTH -
//			// hero.getHeroStartingX()) - (int)
//			// levelDimension.getWidth(), heroY,
//			// hero.spriteArt.getImageWidth(),
//			// hero.spriteArt.getImageHeight());
//			// } else if (heroX <= Hero.heroStartingX) {
//			// g2d.drawRect((int) enemy.getX(), (int) enemy.getY(),
//			// enemy.spriteArt.getImageWidth(),
//			// enemy.spriteArt.getImageHeight());
//			// } else {
//			for (int i = 0; i < enemies.enemiesVector.size(); i++) {
//				currentEnemy = enemies.enemiesVector.get(i);
//				g2d.drawRect(hero.getHeroStartingX() - heroX + (int) currentEnemy.getX(), (int) currentEnemy.getY(), currentEnemy.spriteArt.getImageWidth(), currentEnemy.spriteArt.getImageHeight());
//			}
//			// }
//
//			// Abstract Board
//			for (Shape s : board.boardCollisionShapes) {
//
//				if (heroX + PANEL_WIDTH - hero.getHeroStartingX() >= levelDimension.getWidth()) {
//					AffineTransform origTransform = g2d.getTransform();
//					AffineTransform transformer = new AffineTransform();
//					transformer.translate(PANEL_WIDTH - (int) levelDimension.getWidth(), 0);
//					g2d.setTransform(transformer);
//					g2d.draw(s);
//					g2d.fill(s);
//					g2d.setTransform(origTransform);
//				} else if (heroX <= hero.getHeroStartingX()) {
//					g2d.draw(s);
//					g2d.fill(s);
//				} else {
//					AffineTransform origTransform = g2d.getTransform();
//					AffineTransform transformer = new AffineTransform();
//					transformer.translate(hero.getHeroStartingX() - heroX, 0);
//					g2d.setTransform(transformer);
//					g2d.draw(s);
//					g2d.fill(s);
//					g2d.setTransform(origTransform);
//				}
//
//			}
//		}

		super.paint(g);
	}

	private void gameOver() {
		gameOver = true;

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				GameOverPanel gameOverPanel = new GameOverPanel();
				loadPanel(gameOverPanel);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				removePanel(gameOverPanel);
				gameOverPanel.setVisible(false);

				HallOfFame hallOfFame = new HallOfFame();
				loadPanel(hallOfFame);
				hallOfFame.mustEnterHallOfFame(VitalsAndScore.getInstance().getScore());
				System.out.println("Thread: 'Game Over' ended gracefully ");
			}
		});
		t.start();
	}

	public void loadPanel(JPanel panel) {
		// panel.setLocation(panel.getX(), panel.getY()-100);
		add(panel);
	}

	public void removePanel(JPanel panel) {
		remove(panel);
	}
}
