package game.data;

import game.artwork.ImageRepository;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

public class VitalsAndScorePanel extends JPanel implements Observer {

	GradientProgerssBar progressBar = null;
	JLabel lblScore = null;
	JLabel score = null;
	JLabel lblHighScore = null;
	JLabel highScore = null;
	JPanel lifesPanel = null;
	String filename = null;
	
	public VitalsAndScorePanel() {
		setLayout(null);

		filename = getClass().getResource("/data/hiScore.ser").getFile();
		
		lblScore = new JLabel("");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setIcon(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_VITALSANDSCORE_SCORE));
		lblScore.setForeground(Color.YELLOW);
		lblScore.setBounds(10, 0, 140, 36);
		add(lblScore);

		score = new JLabel("0");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setForeground(Color.ORANGE);
		score.setFont(new Font("Dialog", Font.BOLD, 18));
		score.setBounds(10, 38, 140, 15);
		add(score);

		lblHighScore = new JLabel("");
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setIcon(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_VITALSANDSCORE_HISCORE));
		lblHighScore.setForeground(Color.YELLOW);
		lblHighScore.setBounds(162, 0, 169, 36);
		add(lblHighScore);

		highScore = new JLabel("0");
		highScore.setHorizontalAlignment(SwingConstants.CENTER);
		highScore.setFont(new Font("Dialog", Font.BOLD, 18));
		highScore.setForeground(Color.ORANGE);
		highScore.setBounds(162, 38, 169, 15);
		loadHiScore();
		add(highScore);

		progressBar = new GradientProgerssBar(240, "", Color.GREEN);
		add(progressBar);

		lifesPanel = new JPanel();
		lifesPanel.setBackground(Color.BLACK);
		lifesPanel.setBounds(338, 11, 212, 42);
		add(lifesPanel);
		lifesPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_VITALSANDSCORE_LIFE));
		lblNewLabel.setBounds(0, 0, 70, 41);
		lifesPanel.add(lblNewLabel);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_VITALSANDSCORE_LIFE));
		label_2.setBounds(70, 0, 70, 41);
		lifesPanel.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_VITALSANDSCORE_LIFE));
		label_3.setBounds(140, 0, 70, 41);
		lifesPanel.add(label_3);

	}

	@Override
	public void update(Observable o, Object arg) {
		progressBar.setValue(VitalsAndScore.getInstance().getCurrentLifeLevel());
		score.setText(new Long(VitalsAndScore.getInstance().getScore()).toString());
		if (VitalsAndScore.getInstance().getScore() > (new Long(highScore.getText()))) {
			highScore.setText(new Long(VitalsAndScore.getInstance().getScore()).toString());
			writeHiScore(VitalsAndScore.getInstance().getScore());
		}
	}

	private void loadHiScore() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filename));
			highScore.setText(br.readLine());
	
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeHiScore(long newHighScore) {
		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			out.println(newHighScore);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetProgressBar(){
		progressBar.reset();
	}
}
