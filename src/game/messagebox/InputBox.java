package game.messagebox;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class InputBox extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	protected JTextField inputText = null;
	protected JButton okButton = null;
	public JEditorPane editorPane = null;
	int width = 300;
	int hight = 240;

	public InputBox(JComponent parent) {
		setResizable(false);
		setModal(true);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		int xPosition = parent.getLocationOnScreen().x + ((parent.getWidth() - width) / 2);
		int yPosition = parent.getLocationOnScreen().y + (parent.getHeight() - hight) / 2;
		setBounds(xPosition, yPosition, width, hight);

		// getContentPane().setLayout(new BorderLayout());

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setBounds(103, 203, 93, 25);
		contentPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);

		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setBounds(12, 12, 276, 135);
		contentPanel.add(editorPane);
		
				inputText = new JTextField();
				inputText.setText("nobody");
				inputText.setBounds(57, 166, 186, 25);
				contentPanel.add(inputText);
				inputText.setColumns(10);
	}

	public String getInput() {
		return this.inputText.getText();
	}

	public void setMessage(String message) {
		editorPane.setText(message);
	}

	public void setBackgroundColor(Color color) {
		contentPanel.setBackground(color);
	}

	public void setForegroundColor(Color color) {
		contentPanel.setForeground(color);
	}

}
