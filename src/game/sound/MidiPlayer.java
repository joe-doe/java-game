package game.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

public class MidiPlayer {
	File soundfile = null;
	Sequence sequence = null;
	Sequencer sequencer = null;

//	Soundbank soundbank = null;
//	Synthesizer synth = null;
//
//	public MidiPlayer(String filename, boolean s) {
//		soundfile = new File(filename);
//
//		try {
//			soundbank = MidiSystem.getSoundbank(new File(filename));
//			synth = MidiSystem.getSynthesizer();
//			synth.open();
//		} catch (MidiUnavailableException e) {
//			e.printStackTrace();
//		} catch (InvalidMidiDataException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		synth.loadAllInstruments(soundbank);
//
//		MidiChannel[] channels = synth.getChannels();
//		channels[0].noteOn(66, 100);
//
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//		}
//
//	}

	public MidiPlayer(String filename) {
		getMidiInfo();
		soundfile = new File(filename);

		try {
			sequence = MidiSystem.getSequence(soundfile);
			sequencer = MidiSystem.getSequencer();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.open();
			sequencer.setSequence(sequence);
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void playMidi() {
		Thread play = new Thread(new Runnable() {

			@Override
			public void run() {
				 sequencer.start();
			}
		});
//		setVolume(0.1);
		play.start();
	}

	public void getMidiInfo() {
		MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
		if (devices.length == 0) {
			System.out.println("No MIDI devices found");
		} else {
			for (MidiDevice.Info dev : devices) {
				System.out.println(dev);
			}
		}
	}

//	public void setVolume(double gain) {
//		if (sequencer instanceof Synthesizer) {
//			Synthesizer synthesizer = (Synthesizer) sequencer;
//			MidiChannel[] channels = synthesizer.getChannels();
//
//			// gain is a value between 0 and 1 (loudest)
//			// double gain = 0.9D;
//			for (int i = 0; i < channels.length; i++) {
//				channels[i].controlChange(7, (int) (gain * 127.0));
//			}
//		}
//	}

}
