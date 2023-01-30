package game.artwork;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class ImageRepository {

	public static final int IMAGE_BACKGROUND_1 = 1;
	public static final int IMAGE_BACKGROUND_2 = 2;
	public static final int IMAGE_BACKGROUND_3 = 3;
	public static final int IMAGE_GAMEOVER = 4;
	public static final int IMAGE_PROGRESSBAR_MASK = 5;
	public static final int IMAGE_ENEMY_RIP = 6;
	public static final int IMAGE_ENEMY1_LEFT = 7;
	public static final int IMAGE_ENEMY1_RIGHT = 8;
	public static final int IMAGE_ENEMY2_LEFT = 9;
	public static final int IMAGE_ENEMY2_RIGHT = 10;
	public static final int IMAGE_ENEMY3_LEFT = 11;
	public static final int IMAGE_ENEMY3_RIGHT = 12;
	public static final int IMAGE_HERO_LEFT = 13;
	public static final int IMAGE_HERO_RIGHT = 14;
	public static final int IMAGE_HERO_STOP = 15;
	public static final int IMAGE_HERO_RIP = 16;

	public static final int IMAGEICON_FIREBALL_LEFT = 1;
	public static final int IMAGEICON_FIREBALL_RIGHT = 2;
	public static final int IMAGEICON_VITALSANDSCORE_SCORE = 3;
	public static final int IMAGEICON_VITALSANDSCORE_HISCORE = 4;
	public static final int IMAGEICON_VITALSANDSCORE_LIFE = 5;
	public static final int IMAGEICON_CLOUD_SINGLE = 6;
	public static final int IMAGEICON_CLOUD_DOUBLE = 7;
	public static final int IMAGEICON_HALL_OF_FAME = 9;
	public static final int IMAGEICON_GOLD_COIN = 10;
	public static final int IMAGEICON_100 = 11;
	public static final int IMAGEICON_HEART_SMALL = 12;
	public static final int IMAGEICON_HEART_BIG = 13;

	public static final int MOVE_RIGHT_SEQUENCE = 1000;
	public static final int MOVE_LEFT_SEQUENCE= 1001;
	
	private static ImageRepository instance = null;

	private Image returnImage = null;
	private Image background1 = null;
	private Image background2 = null;
	private Image background3 = null;
	private Image gameOver = null;
	private Image progressBarMask = null;
	private Image enemyRIP = null;
	private Image enemy1Left = null;
	private Image enemy1Right = null;
	private Image enemy2Left = null;
	private Image enemy2Right = null;
	private Image enemy3Left = null;
	private Image enemy3Right = null;
	private Image heroRight = null;
	private Image heroLeft = null;
	private Image heroStop = null;
	private Image heroRIP = null;

	private ImageIcon returnImageIcon = null;
	private ImageIcon fireballLeft = null;
	private ImageIcon fireballRight = null;
	private ImageIcon vitalsAndScore_score = null;
	private ImageIcon vitalsAndScore_hiscore = null;
	private ImageIcon vitalsAndScore_life = null;
	private ImageIcon cloudSingle = null;
	private ImageIcon cloudDouble = null;
	private ImageIcon hallOfFame = null;
	private ImageIcon goldCoin = null;
	private ImageIcon points100 = null;
	private ImageIcon heartSmall = null;
	private ImageIcon heartBig = null;

	private List<Image> returnImageSequenceList = null;
	private List<Image> moveRightImageSequenceList = null;
	private List<Image> moveLeftImageSequenceList = null;
	
	private ImageRepository() {
		background1 = new ImageIcon(getClass().getResource("/images/Background/back1.png")).getImage();
		background2 = new ImageIcon(getClass().getResource("/images/Background/treesBackground.png")).getImage();
		background3 = new ImageIcon(getClass().getResource("/images/Background/grass.png")).getImage();
		gameOver = new ImageIcon(getClass().getResource("/images/GameOver/gameOver.png")).getImage();
		progressBarMask = new ImageIcon(getClass().getResource("/images/VitalsAndScore/maskFinal.png")).getImage();
		enemyRIP = new ImageIcon(getClass().getResource("/images/Enemies/rip.gif")).getImage();
		enemy1Left = new ImageIcon(getClass().getResource("/images/Enemies/Enemy1/1_L.png")).getImage();
		enemy1Right = new ImageIcon(getClass().getResource("/images/Enemies/Enemy1/1_R.png")).getImage();
		enemy2Left = new ImageIcon(getClass().getResource("/images/Enemies/Enemy2/2_L.png")).getImage();
		enemy2Right = new ImageIcon(getClass().getResource("/images/Enemies/Enemy2/2_R.png")).getImage();
		enemy3Left = new ImageIcon(getClass().getResource("/images/Enemies/Enemy3/3_L.png")).getImage();
		enemy3Right = new ImageIcon(getClass().getResource("/images/Enemies/Enemy3/3_R.png")).getImage();
		heroLeft = new ImageIcon(getClass().getResource("/images/Hero/Left.png")).getImage();
		heroRight = new ImageIcon(getClass().getResource("/images/Hero/Right.png")).getImage();
		heroStop = new ImageIcon(getClass().getResource("/images/Hero/Stop.png")).getImage();
		heroRIP = new ImageIcon(getClass().getResource("/images/Hero/rip.gif")).getImage();

		fireballLeft = new ImageIcon(getClass().getResource("/images/Various/fireball_Left.gif"));
		fireballRight = new ImageIcon(getClass().getResource("/images/Various/fireball_Right.gif"));
		vitalsAndScore_score = new ImageIcon(getClass().getResource("/images/VitalsAndScore/score.png"));
		vitalsAndScore_hiscore = new ImageIcon(getClass().getResource("/images/VitalsAndScore/highScore.png"));
		vitalsAndScore_life = new ImageIcon(getClass().getResource("/images/VitalsAndScore/life.png"));
		cloudSingle = new ImageIcon(getClass().getResource("/images/Background/cloudSingle.png"));
		cloudDouble = new ImageIcon(getClass().getResource("/images/Background/cloudDouble.png"));
		hallOfFame = new ImageIcon(getClass().getResource("/images/GameOver/HallOfFame.png"));
		goldCoin = new ImageIcon(getClass().getResource("/images/Animations/dollar.gif"));
		points100 = new ImageIcon(getClass().getResource("/images/Animations/100.png"));
		heartSmall= new ImageIcon(getClass().getResource("/images/Animations/heartSmall.gif"));
		heartBig = new ImageIcon(getClass().getResource("/images/Animations/heartBig.gif"));
		
		moveRightImageSequenceList = new ArrayList<Image>();
		moveLeftImageSequenceList = new ArrayList<Image>();
		File movingRightSequenceFolder = new File(getClass().getResource("/images/Hero/moveRight/").getPath());
		File movingLeftSequenceFolder = new File(getClass().getResource("/images/Hero/moveLeft/").getPath());

		String[] movingRightSequenceFilenames = movingRightSequenceFolder.list();
		List<String> movingRightSequenceList = new ArrayList<String>();
		java.util.Collections.addAll(movingRightSequenceList, movingRightSequenceFilenames); 
		java.util.Collections.sort(movingRightSequenceList);

		String[] movingLeftSequenceFilenames = movingLeftSequenceFolder.list();
		List<String> movingLeftSequenceList = new ArrayList<String>();
		java.util.Collections.addAll(movingLeftSequenceList, movingLeftSequenceFilenames); 
		java.util.Collections.sort(movingLeftSequenceList);

		for(String filename:movingRightSequenceList){
			System.out.println(filename);
			moveRightImageSequenceList.add(new ImageIcon(getClass().getResource("/images/Hero/moveRight/"+filename)).getImage());
		}
		
		for(String filename:movingLeftSequenceList){
			moveLeftImageSequenceList.add(new ImageIcon(getClass().getResource("/images/Hero/moveLeft/"+filename)).getImage());
		}
		
	}

	public static ImageRepository getInstance() {
		if (instance == null) {
			instance = new ImageRepository();
		}
		return instance;
	}

	public Image getImage(int index) {
		switch (index) {
		case IMAGE_BACKGROUND_1:
			returnImage = background1;
			break;
		case IMAGE_BACKGROUND_2:
			returnImage = background2;
			break;
		case IMAGE_BACKGROUND_3:
			returnImage = background3;
			break;
		case IMAGE_GAMEOVER:
			returnImage = gameOver;
			break;
		case IMAGE_PROGRESSBAR_MASK:
			returnImage = progressBarMask;
			break;
		case IMAGE_ENEMY_RIP:
			returnImage = enemyRIP;
			break;
		case IMAGE_ENEMY1_LEFT:
			returnImage = enemy1Left;
			break;
		case IMAGE_ENEMY1_RIGHT:
			returnImage = enemy1Right;
			break;
		case IMAGE_ENEMY2_LEFT:
			returnImage = enemy2Left;
			break;
		case IMAGE_ENEMY2_RIGHT:
			returnImage = enemy2Right;
			break;
		case IMAGE_ENEMY3_LEFT:
			returnImage = enemy3Left;
			break;
		case IMAGE_ENEMY3_RIGHT:
			returnImage = enemy3Right;
			break;
		case IMAGE_HERO_LEFT:
			returnImage = heroLeft;
			break;
		case IMAGE_HERO_RIGHT:
			returnImage = heroRight;
			break;
		case IMAGE_HERO_STOP:
			returnImage = heroStop;
			break;
		case IMAGE_HERO_RIP:
			returnImage = heroRIP;
			break;
		// case IMAGE_BACKGROUND_2:
		// returnImage = background2;
		// break;
		// case IMAGE_BACKGROUND_2:
		// returnImage = background2;
		// break;

		default:
			break;
		}
		return returnImage;
	}

	public ImageIcon getImageIcon(int index) {
		switch (index) {
		case IMAGEICON_FIREBALL_LEFT:
			returnImageIcon = fireballLeft;
			break;
		case IMAGEICON_FIREBALL_RIGHT:
			returnImageIcon = fireballRight;
			break;
		case IMAGEICON_VITALSANDSCORE_SCORE:
			returnImageIcon = vitalsAndScore_score;
			break;
		case IMAGEICON_VITALSANDSCORE_HISCORE:
			returnImageIcon = vitalsAndScore_hiscore;
			break;
		case IMAGEICON_VITALSANDSCORE_LIFE:
			returnImageIcon = vitalsAndScore_life;
			break;
		case IMAGEICON_CLOUD_SINGLE:
			returnImageIcon = cloudSingle;
			break;
		case IMAGEICON_CLOUD_DOUBLE:
			returnImageIcon = cloudDouble;
			break;
		case IMAGEICON_HALL_OF_FAME:
			returnImageIcon = hallOfFame;
			break;
		case IMAGEICON_GOLD_COIN:
			returnImageIcon = goldCoin;
			break;
		case IMAGEICON_100:
			returnImageIcon = points100;
			break;
		case IMAGEICON_HEART_SMALL:
			returnImageIcon = heartSmall;
			break;
		case IMAGEICON_HEART_BIG:
			returnImageIcon = heartBig;
			break;
		default:
			break;
		}
		return returnImageIcon;
	}
	

	public List<Image> getImageSequenceList(int index) {
		switch (index) {
		case MOVE_RIGHT_SEQUENCE:
			returnImageSequenceList=moveRightImageSequenceList;
			break;
		case MOVE_LEFT_SEQUENCE:
			returnImageSequenceList=moveLeftImageSequenceList;
			break;
		default:
			break;
		
		}
		return returnImageSequenceList;
	}
	
}
