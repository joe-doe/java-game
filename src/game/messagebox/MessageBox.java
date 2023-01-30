package game.messagebox;

import game.leveleditor.LevelEditorFrame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class MessageBox extends JDialog {
	private JPanel contentPanel = null;
	private JPanel inputPanel = null;
	private JPanel buttonPanel = null;
	public JButton okButton = null;
	private JButton cancelButton = null;
	private JLabel lblWidth = null;

	private int newWidth = 0;
	private int newHeight = 0;
	private JTextField textField;
	private JTextField textField_1;

	private JFileChooser fileChooser = null;
	private JTextField textField_2;
	private JTextField textField_3;
	private LevelEditorFrame parent = null;
	private String imageFilename = null;
	
	// /**
	// * Launch the application.
	// */
	// public static void main(String[] args) {
	// try {
	// MessageBox dialog = new MessageBox(800,600);
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public MessageBox(LevelEditorFrame parent) {
		this.parent = parent;
		setTitle("Select new level data");

		newWidth = parent.getWidth();
		newHeight = parent.getHeight();
		setBounds((newWidth - 450) / 2, (newHeight - 300) / 2, 503, 401);

		fileChooser = new JFileChooser("./");

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		inputPanel = new JPanel();
		inputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		inputPanel.setBounds(10, 10, 479, 300);
		inputPanel.setLayout(null);
		contentPanel.add(inputPanel);

		buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 326, 479, 32);
		buttonPanel.setLayout(null);
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.add(buttonPanel);

		JLabel lblLevelInfo = new JLabel("LEVEL INFO");
		lblLevelInfo.setForeground(UIManager
				.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblLevelInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblLevelInfo.setBounds(12, 0, 139, 27);
		inputPanel.add(lblLevelInfo);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(
				new Color(184, 207, 229)), "Level Size", TitledBorder.LEADING,
				TitledBorder.TOP, null, UIManager
						.getColor("CheckBoxMenuItem.acceleratorForeground")));
		panel.setBounds(12, 35, 455, 133);
		inputPanel.add(panel);
		panel.setLayout(null);

		lblWidth = new JLabel("Width:");
		lblWidth.setBounds(12, 24, 47, 15);
		panel.add(lblWidth);

		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(267, 26, 60, 15);
		panel.add(lblHeight);

		textField = new JTextField();
		textField.setBounds(66, 22, 114, 19);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(329, 24, 114, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblOr = new JLabel("OR");
		lblOr.setBounds(190, 62, 42, 15);
		panel.add(lblOr);
		lblOr.setFont(new Font("Dialog", Font.BOLD, 18));
		lblOr.setForeground(Color.RED);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(348, 96, 95, 25);
		panel.add(btnBrowse);

		JLabel lblLoadDimensionsFrom = new JLabel(
				"Load dimensions from an image background");
		lblLoadDimensionsFrom.setBounds(9, 101, 321, 15);
		panel.add(lblLoadDimensionsFrom);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = fileChooser.showOpenDialog(contentPanel);
				switch (selection) {
				case JFileChooser.APPROVE_OPTION:
					calculateDimentions(fileChooser.getSelectedFile()
							.getAbsolutePath());
					break;
				default:
					break;
				}
			}

			private void calculateDimentions(String selectedFile) {
				imageFilename = selectedFile;
				Image image = new ImageIcon(selectedFile).getImage();
				newWidth = image.getWidth(null);
				newHeight = image.getHeight(null);
				textField.setText("" + newWidth);
				textField_1.setText("" + newHeight);
			}

		});

		JLabel lblTilesInfo = new JLabel("TILES INFO");
		lblTilesInfo.setForeground(UIManager
				.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblTilesInfo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTilesInfo.setBounds(12, 190, 139, 37);
		inputPanel.add(lblTilesInfo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tiles Size",
				TitledBorder.LEADING, TitledBorder.TOP, null, UIManager
						.getColor("CheckBoxMenuItem.acceleratorForeground")));
		panel_1.setBounds(12, 229, 455, 53);
		inputPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("Width:");
		label.setBounds(12, 24, 47, 15);
		panel_1.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(66, 22, 114, 19);
		panel_1.add(textField_2);

		JLabel label_1 = new JLabel("Height:");
		label_1.setBounds(267, 24, 60, 15);
		panel_1.add(label_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(329, 22, 114, 19);
		panel_1.add(textField_3);

		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateParent();
				setVisible(false);
			}

		});
		okButton.setBounds(195, 0, 130, 30);
		buttonPanel.add(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBounds(349, 0, 130, 30);
		buttonPanel.add(cancelButton);

		setContentPane(contentPanel);
	}

	public int getNewWidth() {
		return newWidth;
	}

	public int getNewHeight() {
		return newHeight;
	}

	private void updateParent() {

		newWidth = new Integer(textField.getText());
		newHeight = new Integer(textField_1.getText());
		
		int tileWidth = new Integer(textField_2.getText());
		int tileHeight = new Integer(textField_3.getText());
		int columns = (int)(newWidth/tileWidth);
		int rows = (int)(newHeight/tileHeight);
		
		System.out.println(""+tileWidth+" "+tileHeight+" "+rows+" "+columns+" "+imageFilename);
		parent.updateValues(rows,columns,tileWidth,tileHeight,imageFilename);
	}
}
