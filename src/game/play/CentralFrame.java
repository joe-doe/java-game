package game.play;

import game.data.VitalsAndScore;
import game.data.VitalsAndScorePanel;
import game.entities.Enemy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import java.awt.Color;

public class CentralFrame extends JFrame {

	public static final boolean DEBUGGING = false;
	public static final int APPLICATION_WIDTH = 825;
	public static final int APPLICATION_HIGHT = 730;
	private JPanel contentPane = null;
	private CentralPanel panel = null;
	private VitalsAndScorePanel vitalsAndScorePanel = null;
	public VitalsAndScore vitalsAndScore = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CentralFrame frame = new CentralFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CentralFrame() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 730);

		vitalsAndScore = VitalsAndScore.getInstance();

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		vitalsAndScorePanel = new VitalsAndScorePanel();
		vitalsAndScorePanel.setLayout(null);
		vitalsAndScorePanel.setBackground(Color.BLACK);
		vitalsAndScorePanel.setBounds(12, 12, 800, 60);
		contentPane.add(vitalsAndScorePanel);

		// panel = new CentralPanel(this);
		// panel.setLocation(12, 83);
		// panel.setVisible(true);
		// contentPane.add(panel);
		// panel.hero.addObserver(vitalsAndScorePanel);
		startOver();

		setContentPane(contentPane);
	}

	public void startOver() {
		resetVitalsAndScorePanel();
		if (panel != null) {
			hookObservers(false);
			getContentPane().remove(panel);
			panel.killAllThreads();
			hookObservers(false);
			panel.dispose();
			panel=null;
			System.gc();
		}

		panel = new CentralPanel(this);
		panel.setBorder(null);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BasicInternalFrameTitlePane titlePane =  
			      (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) panel.getUI()).  
			      getNorthPane();
		panel.remove(titlePane);
		panel.setSize(800, 600);
		panel.setLocation(12, 83);
		//panel.setLayout(null);
		panel.setVisible(true);
		
		contentPane.add(panel);
		hookObservers(true);

	}

	public void resetVitalsAndScorePanel() {
		vitalsAndScorePanel.resetProgressBar();
	}

	public void hookObservers(boolean hook) {
		if (hook == true) {
			panel.hero.addObserver(vitalsAndScorePanel);
		} else {
			panel.hero.deleteObservers();
			for(Enemy e : panel.enemies.enemiesVector){
				e.deleteObservers();
			}
		}
	}
}
