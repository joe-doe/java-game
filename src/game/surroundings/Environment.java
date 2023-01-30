package game.surroundings;

import game.artwork.ImageRepository;
import game.entities.StaticInteractable;
import game.entities.StaticInteractableAttributes;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class Environment extends JComponent {

	public Vector<MovingItem> backgroundGraphics = null;
	public Vector<StaticInteractable> staticInteractableGraphics = null;
	public Vector<StaticItem> staticGraphics = null;
	private int levelWidth = 0;
	private int levelHeight = 0;

	public Environment(Dimension levelDimension) {
		this.levelWidth = (int)levelDimension.getWidth();
		this.levelHeight = (int)levelDimension.getHeight();
		this.backgroundGraphics = new Vector<MovingItem>();
		this.staticInteractableGraphics = new Vector<StaticInteractable>();
		this.staticGraphics = new Vector<StaticItem>();
		loadEnvironment();
	}

	private void loadEnvironment() {
		MovingItem cloud = new MovingItem(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_CLOUD_SINGLE)),
				-50, 0, 3, 0, levelWidth, levelHeight);
		addBackgroundComponent(cloud);
		cloud = new MovingItem(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_CLOUD_DOUBLE)),
				250, 0, 1, 0, levelWidth, levelHeight);
		addBackgroundComponent(cloud);
	    cloud = new MovingItem(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_CLOUD_SINGLE)),
				550, 0, 2, 0, levelWidth, levelHeight);
		addBackgroundComponent(cloud);
		
//		StaticInteractable fire = new StaticInteractable(
//				new JLabel(
//						new ImageIcon(
//								"/data/DevelopersCorner/Eclipse/JavaGame_2/src/images/Animations/fire1.gif")),
//								null,StaticInteractableAttributes.TYPE_HEALTH,false,10,230, 530, 80, 80);
//		addStaticInteractableComponent(fire);
		StaticInteractable life = new StaticInteractable(
				new JLabel(
						new ImageIcon(
								"/data/DevelopersCorner/Eclipse/JavaGame_2/src/images/Animations/heartSmall.gif")),
								null,StaticInteractableAttributes.TYPE_HEALTH,15,true,1540, 530, 45, 45);
		addStaticInteractableComponent(life);
		
		StaticInteractable coin = new StaticInteractable(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_GOLD_COIN)),
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_100)),
				StaticInteractableAttributes.TYPE_SCORE,100,true,230, 350, 30, 18);
		addStaticInteractableComponent(coin);
		
		coin = new StaticInteractable(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_GOLD_COIN)),
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_100)),
				StaticInteractableAttributes.TYPE_SCORE,100,true,270, 350, 30, 18);
		addStaticInteractableComponent(coin);

		coin = new StaticInteractable(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_GOLD_COIN)),
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_100)),
				StaticInteractableAttributes.TYPE_SCORE,100,true,310, 350, 30, 18);
		addStaticInteractableComponent(coin);

		coin = new StaticInteractable(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_GOLD_COIN)),
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_100)),
				StaticInteractableAttributes.TYPE_SCORE,100,true,350, 350, 30, 18);
		addStaticInteractableComponent(coin);

		coin = new StaticInteractable(
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_GOLD_COIN)),
				new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_100)),
				StaticInteractableAttributes.TYPE_SCORE,100,true,390, 350, 30, 18);
		addStaticInteractableComponent(coin);
	}

	public Vector<StaticInteractable> getStaticInteractableItems(){
		return this.staticInteractableGraphics;
	}

	public Vector<StaticItem> getStaticItems(){
		return this.staticGraphics;
	}
	
	public void addBackgroundComponent(MovingItem component) {
		backgroundGraphics.add(component);
	}

	public void addStaticInteractableComponent(StaticInteractable component) {
		staticInteractableGraphics.add(component);
	}
	
	public void removeStaticInteractableComponent(StaticInteractable component) {
		staticInteractableGraphics.remove(component);
	}

//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
//		for (MovingItem boardCollisionDetector : backgroundGraphics) {
//			boardCollisionDetector.paint(g2d);
//		}
//	}

}
