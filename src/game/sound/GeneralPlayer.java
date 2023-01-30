package game.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GeneralPlayer extends Thread {

	private String filename;

	public GeneralPlayer(String filename) {
		super();
		this.filename = filename;
	}

	public GeneralPlayer(){
		
	}
	@Override
	public void run() {

		File file = new File(filename);

		AudioInputStream in = null;
		try {
			in = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioInputStream din = null;
		AudioFormat baseFormat = in.getFormat();
		AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(), false);
		din = AudioSystem.getAudioInputStream(decodedFormat, in);

		// play it...
		try {
			rawplay(decodedFormat, din);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private synchronized void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException, LineUnavailableException {
		byte[] data = new byte[4096];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(data, 0, data.length);
				if (nBytesRead != -1) {
					nBytesWritten = line.write(data, 0, nBytesRead);
				}

			}
			// Stop
			line.drain();
			line.stop();
			line.close();
			din.close();
		}

	}

	private synchronized SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);

		return res;
	}

	public void ConvertFileToAIFF(String inputPath, String outputPath) {
		AudioFileFormat inFileFormat;
		File inFile;
		File outFile;
		try {
			inFile = new File(inputPath);
			outFile = new File(outputPath);
		} catch (NullPointerException ex) {
			System.out.println("Error: one of the ConvertFileToAIFF" + " parameters is null!");
			return;
		}
		try {
			// query file type
			inFileFormat = AudioSystem.getAudioFileFormat(inFile);
			if (inFileFormat.getType() != AudioFileFormat.Type.AIFF) {
				// inFile is not AIFF, so let's try to convert it.
				AudioInputStream inFileAIS = AudioSystem.getAudioInputStream(inFile);
				inFileAIS.reset(); // rewind
				if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.AIFF, inFileAIS)) {
					// inFileAIS can be converted to AIFF.
					// so write the AudioInputStream to the
					// output file.
					AudioSystem.write(inFileAIS, AudioFileFormat.Type.AIFF, outFile);
					System.out.println("Successfully made AIFF file, " + outFile.getPath() + ", from " + inFileFormat.getType() + " file, " + inFile.getPath() + ".");
					inFileAIS.close();
					return; // All done now
				} else
					System.out.println("Warning: AIFF conversion of " + inFile.getPath() + " is not currently supported by AudioSystem.");
			} else
				System.out.println("Input file " + inFile.getPath() + " is AIFF." + " Conversion is unnecessary.");
		} catch (UnsupportedAudioFileException e) {
			System.out.println("Error: " + inFile.getPath() + " is not a supported audio file type!");
			return;
		} catch (IOException e) {
			System.out.println("Error: failure attempting to read " + inFile.getPath() + "!");
			return;
		}
	}
	
	public void a(String audiofile){
	    AudioInputStream stream=null;
		try {
			stream = AudioSystem.getAudioInputStream(new File(audiofile));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	        stream = AudioSystem.getAudioInputStream(new URL(
	      //      "http://hostname/audiofile"));

	        AudioFormat format = stream.getFormat();
	        if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	          format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format
	              .getSampleRate(), format.getSampleSizeInBits() * 2, format
	              .getChannels(), format.getFrameSize() * 2, format.getFrameRate(),
	              true); // big endian
	          stream = AudioSystem.getAudioInputStream(format, stream);
	        }

	        SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, stream
	            .getFormat(), ((int) stream.getFrameLength() * format.getFrameSize()));
	        SourceDataLine line = null;
			try {
				line = (SourceDataLine) AudioSystem.getLine(info);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				line.open(stream.getFormat());
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        line.start();

	        int numRead = 0;
	        byte[] buf = new byte[line.getBufferSize()];
	        try {
				while ((numRead = stream.read(buf, 0, buf.length)) >= 0) {
				  int offset = 0;
				  while (offset < numRead) {
				    offset += line.write(buf, offset, numRead - offset);
				  }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        line.drain();
	        line.stop();
	}
}
