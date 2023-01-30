package game.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SampledPlayer implements LineListener {
	Clip clip = null;
	File soundFile = null;
	Line.Info linfo = null;
	AudioInputStream ais = null;
	Line line = null;
	FloatControl gainControl = null;
	BooleanControl muteControl = null;

	public SampledPlayer(String filename) {
		soundFile = new File(filename);
		linfo = new Line.Info(Clip.class);

		try {
			ais = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			line = AudioSystem.getLine(linfo);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clip = (Clip) line;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.addLineListener(this);
		try {
			clip.open(ais);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
		setVolume(0.1);
	}

	public void playSampled() {
		Thread play = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("D");
				clip.start();
			}
		});
		
		play.start();
	}

	@Override
	public void update(LineEvent event) {

	}

	public void setVolume(double gain) { // double gain = .5D; // number between
											// 0 and 1 (loudest)
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}

	public void mute(boolean value) {
		muteControl.setValue(value);
	}
}
