package game.play;

import game.entities.Enemy;
import game.entities.Hero;
import game.surroundings.AbstractBoard;
import game.surroundings.Environment;
import game.surroundings.MovingItem;

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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class CentralPanel extends JPanel implements ActionListener {

	private Timer panelRefreshTimer = null;
	private Image background = null;
	private Image background2 = null;
	private Image grass = null;
	private Hero hero = null;
	private Enemy enemy = null;
	private AbstractBoard board = null;
	private long timeBetweenUpates = 0;
	private long lastCallTime = 0;
	private JLabel lblNewLabel = null;
	private Environment be = null;
	private Dimension levelDimension = null;
	private int screenStart = 0;
	private int screenEnd = 0;
	private int heroX = 0;
	private int heroY = 0;
	private int backgroundDepth = 0;
	private Graphics2D g2d = null;

	private int mainBackgroundEnd = 0;
	private int treesBackgroundEnd = 0;

	/**
	 * Create the panel.
	 */
	public CentralPanel() {
		setLayout(null);
		setBounds(12, 12, 800, 600);
		setFocusable(true);
		setBackground(new Color(0, 0, 0, 0));

		background = (new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/back1.png").getImage());
		background2 = (new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/treesBackground.png").getImage());
		grass = (new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/grass.png").getImage());
		levelDimension = new Dimension((int) grass.getWidth(null), (int) grass.getHeight(null));
		board = new AbstractBoard(levelDimension);
		hero = new Hero(board);
		enemy = new Enemy(new JLabel(new ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/enemy.png")), board);
		be = new Environment(levelDimension);

		panelRefreshTimer = new Timer(10, this);
		setLayout(null);

		lblNewLabel = new JLabel("Tazmanian devil");
		lblNewLabel.setForeground(Color.WHITE);
		// lblNewLabel.setIcon(new
		// ImageIcon("/data/DevelopersCorner/Eclipse/JavaGame_2/images/cloud_1.png"));
		lblNewLabel.setOpaque(false);
		add(lblNewLabel);
		panelRefreshTimer.start();
		startKeyListener();

	}

	private void startKeyListener() {

		this.addKeyListener(new KeyListener() {

			long previousTime = 0;

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// System.out.println("Time between releases:"+(e.getWhen()-previousTime));

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (e.getWhen() - previousTime < 40) {
						hero.freeze();
					}
					previousTime = e.getWhen();
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (e.getWhen() - previousTime < 40) {
						hero.freeze();
					}
					previousTime = e.getWhen();

				}
				//
				// if (e.getKeyCode() == KeyEvent.VK_UP) {
				// // hero.setJumpSpeed(0);
				// }
				//
				// if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				// // hero.setVelocityY(-1);
				// }
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("Time between press:"+(e.getWhen()-previousTime));

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					hero.moveLeft();
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (e.getWhen() - previousTime > 0) {
						hero.moveRight();
					}
					previousTime = e.getWhen();
				}

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					hero.jump();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					hero.freeze();
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
		hero.move(timeBetweenUpates);
		enemy.move(timeBetweenUpates);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		// System.out.println("W:"+board.image.getWidth(null)+" H:"+board.image.getHeight(null));
		// System.out.println("W:"+board.rect.width+" H:"+board.rect.height+" ");

		g2d = (Graphics2D) g;
		heroX = (int) hero.getX();
		heroY = (int) hero.getY();

		screenStart = Hero.heroStartingX - heroX;
		screenEnd = heroX + 650;

		// Move background
		if (heroX + 650 >= levelDimension.getWidth()) {
			backgroundDepth = (Hero.heroStartingX - heroX) / 3;
			if (mainBackgroundEnd == 0) {
				mainBackgroundEnd = backgroundDepth;
			}

			backgroundDepth = (Hero.heroStartingX - heroX) / 2;
			if (treesBackgroundEnd == 0) {
				treesBackgroundEnd = backgroundDepth;
			}
			g2d.drawImage(background, mainBackgroundEnd, 0, null); // background
																	// speed !=
																	// heroSpeed
			g2d.drawImage(background2, treesBackgroundEnd, 0, null); // background
																		// speed
																		// !=
																		// heroSpeed
			g2d.drawImage(grass, 800 - (int) levelDimension.getWidth(), 0, null);
		} else if (heroX <= Hero.heroStartingX) {
			g2d.drawImage(background, 0, 0, null);
			g2d.drawImage(background2, 0, 0, null);
			g2d.drawImage(grass, 0, 0, null);
		} else {
			backgroundDepth = (Hero.heroStartingX - heroX) / 3;
			g2d.drawImage(background, backgroundDepth, 0, null);

			backgroundDepth = (Hero.heroStartingX - heroX) / 2;
			g2d.drawImage(background2, backgroundDepth, 0, null);

			backgroundDepth = Hero.heroStartingX - heroX;
			g2d.drawImage(grass, backgroundDepth, 0, null);
		}

		// Move hero
		// System.out.println("hero X:" + heroX);
		if (heroX + 650 >= levelDimension.getWidth()) {
			g2d.drawImage(hero.spriteArt.getImage(), Hero.heroStartingX + (heroX + 650) - (int) levelDimension.getWidth(), heroY, null);
		} else if (heroX <= Hero.heroStartingX) {
			g2d.drawImage(hero.spriteArt.getImage(), heroX, heroY, null);
		} else {
			g2d.drawImage(hero.spriteArt.getImage(), Hero.heroStartingX, heroY, null);
		}

		// Move enemies
		backgroundDepth = Hero.heroStartingX - heroX;
		if (heroX + 650 >= levelDimension.getWidth()) {
			g2d.drawImage(enemy.spriteArt.getImage(), (heroX + 650) - (int) levelDimension.getWidth(), heroY, null);
		} else if (heroX <= Hero.heroStartingX) {
			g2d.drawImage(enemy.spriteArt.getImage(), backgroundDepth+(int) enemy.getX(), (int) enemy.getY(), null);
		} else {
			g2d.drawImage(enemy.spriteArt.getImage(), backgroundDepth + (int) enemy.getX() , (int) enemy.getY(), null);
		}

		// Debug shapes collision
		if (CentralFrame.DEBUGGING) {
			g2d.setColor(Color.red);

			// Hero Box
			if (heroX + 650 >= levelDimension.getWidth()) {
				g2d.drawRect(Hero.heroStartingX + (heroX + 650) - (int) levelDimension.getWidth(), heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
			} else if (heroX <= Hero.heroStartingX) {
				g2d.drawRect(heroX, heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
			} else {
				g2d.drawRect(Hero.heroStartingX, heroY, hero.spriteArt.getImageWidth(), hero.spriteArt.getImageHeight());
			}

			// Enemy box
			// if (heroX + 650 >= levelDimension.getWidth()) {
			// g2d.drawRect(Hero.heroStartingX + (heroX + 650) - (int)
			// levelDimension.getWidth(), heroY, hero.spriteArt.getImageWidth(),
			// hero.spriteArt.getImageHeight());
			// } else if (heroX <= Hero.heroStartingX) {
			// g2d.drawRect((int) enemy.getX(), (int) enemy.getY(),
			// enemy.spriteArt.getImageWidth(),
			// enemy.spriteArt.getImageHeight());
			// } else {
			g2d.drawRect(Hero.heroStartingX - heroX + (int) enemy.getX(), (int) enemy.getY(), enemy.spriteArt.getImageWidth(), enemy.spriteArt.getImageHeight());
			// }

			// Abstract Board
			for (Shape s : board.boardCollisionShapes) {

				if (heroX + 650 >= levelDimension.getWidth()) {
					AffineTransform origTransform = g2d.getTransform();
					AffineTransform transformer = new AffineTransform();
					transformer.translate(800 - (int) levelDimension.getWidth(), 0);
					g2d.setTransform(transformer);
					g2d.draw(s);
					g2d.setTransform(origTransform);
				} else if (heroX <= Hero.heroStartingX) {
					g2d.draw(s);
				} else {
					AffineTransform origTransform = g2d.getTransform();
					AffineTransform transformer = new AffineTransform();
					transformer.translate(Hero.heroStartingX - heroX, 0);
					g2d.setTransform(transformer);
					g2d.draw(s);
					g2d.setTransform(origTransform);
				}

			}
		}
		// Background items movement
		for (MovingItem bi : be.backgroundGraphics) {
			int biX = (int) bi.getX();
			int biY = (int) bi.getY();
			int imgW = bi.spriteArt.getImageWidth() + 100;
			int imgH = bi.spriteArt.getImageHeight() + 100;
			// System.out.println("cloud X: " + biX + "      hero X:" + heroX);

			if (biX / 2 < screenEnd + imgW) {

				if (heroX + 650 >= levelDimension.getWidth()) {
					// System.out.println("cloud X: " + biX + "      hero X:" +
					// heroX);
					g2d.drawImage(bi.spriteArt.getImage(), (biX + (800 - (int) levelDimension.getWidth() - Hero.heroStartingX)) / 2, biY, null);
				} else if (heroX <= Hero.heroStartingX) {
					g2d.drawImage(bi.spriteArt.getImage(), (biX - Hero.heroStartingX) / 2, biY, null);
				} else {
					g2d.drawImage(bi.spriteArt.getImage(), (biX - heroX) / 2, biY, null);
				}
			} else {
				bi.setX((heroX - Hero.heroStartingX) / 2 - imgW);
			}
		}

		// Foreground items movement
		// for (StaticItem fi : be.foregroundGraphics) {
		// int xx = fi.getX();
		// int imgW = fi.getImageWidth()+100;
		// int imgH = fi.getImageHeight()+100;
		// System.out.println("cloud X: " + xx + "      hero X:" + heroX);
		//
		// if (xx < screenEnd + imgW) {
		// g2d.drawImage(fi.getImage(), xx - heroX, fi.getY(), null);
		// } else {
		// fi.setX(heroX-150-imgW);
		// System.out.println("DDD");
		// }
		// }

		// TILES
		// for (Tile t : board.boardTiles) {
		// g2d.drawImage(t.getImage(), 150 + (int) t.getRectangle().getX()
		// - heroX, (int) t.getRectangle().getY(), null);
		// g2d.drawRect(150 + (int) t.getRectangle().getX() - heroX, (int) t
		// .getRectangle().getY(), (int) t.getRectangle().getWidth(),
		// (int) t.getRectangle().getHeight());
		// }
		super.paint(g);

		g.dispose();
	}
}
