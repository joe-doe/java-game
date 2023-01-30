package game.messagebox;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.border.BevelBorder;

public class HallOfFameInputBox extends InputBox {

	public HallOfFameInputBox(JComponent parent) {
		super(parent);
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackgroundColor(Color.ORANGE);
		editorPane.setBackground(Color.ORANGE);
		setMessage("<div align='center'><h2><b>CONGRATULATIONS!</b></h2><br><br>You made it to the Hall Of Fame !<br>Enter your name</div>");
	}

}
