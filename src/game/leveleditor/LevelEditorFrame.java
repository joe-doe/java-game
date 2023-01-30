package game.leveleditor;

import game.messagebox.MessageBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;

public class LevelEditorFrame extends JFrame {

	private JPanel contentPane = null;
	private JPanel panel = null;
	private LevelEditorPanel scrollPanel = null;
	private int frameWidth = 820;
	private int frameHeight = 700;
	MessageBox messageBox = null;
	JTable mainTable = null;
	// private int rows = 10;
	// private int columns = 10;
	// private double tileWidth = 10;
	// private double tileHeight = 10;
	private DefaultTableModel mainTableModel = null;
	public JFileChooser fileChooser = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelEditorFrame frame = new LevelEditorFrame();
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
	public LevelEditorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 731);

		fileChooser = new JFileChooser("./");

		messageBox = new MessageBox(this);
		messageBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.shadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		mainTableModel = new DefaultTableModel(100, 100);
		mainTable = new JTable(mainTableModel);
		mainTable.setOpaque(true);
		mainTable.setRowSelectionAllowed(false);
		mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		mainTable.setTableHeader(null);
		mainTable.setBackground(new Color(0, 0, 0, 0));
		mainTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					System.out.println("TILE: " + row + " " + column);
				}
			}
		});
		// mainTable.setPreferredSize(new Dimension(800, 600));

		scrollPanel = new LevelEditorPanel(mainTable, 1000, 1000);
		scrollPanel.setPreferredSize(new Dimension(800, 600));
		scrollPanel.setOpaque(false);
		contentPane.add(scrollPanel);

		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setBackground(UIManager
				.getColor("CheckBoxMenuItem.acceleratorForeground"));
		panel_1.setBounds(10, 642, 815, 45);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Create New Level");
		btnNewButton.setBounds(15, 12, 158, 25);
		panel_1.add(btnNewButton);

		JButton btnSetNewBackground = new JButton("Set New Background");
		btnSetNewBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = fileChooser.showOpenDialog(contentPane);
				switch (selection) {
				case JFileChooser.APPROVE_OPTION:
					scrollPanel.setBackroundImage(fileChooser.getSelectedFile()
							.getAbsolutePath());
					break;
				default:
					break;
				}
			}
		});
		btnSetNewBackground.setBounds(185, 12, 181, 25);
		panel_1.add(btnSetNewBackground);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageBox.setVisible(true);
			}
		});
	}

	public void updateValues(int numberOfRows, int numberOfColumns,
			int tileWidth, int tileHeight, String filename) {
		if (!filename.isEmpty()) {
			scrollPanel.setBackroundImage(filename);
		}
		
		mainTableModel.setColumnCount(numberOfColumns);
		mainTableModel.setRowCount(numberOfRows);
		mainTable.setRowHeight((int) tileHeight);
		TableColumn col = null;

		for (int i = 0; i < numberOfColumns; i++) {
			col = mainTable.getColumnModel().getColumn(i);
			col.setPreferredWidth((int) tileWidth);
		}

	}
}
