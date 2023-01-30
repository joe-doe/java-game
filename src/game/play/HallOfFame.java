package game.play;

import game.artwork.ImageRepository;
import game.data.VitalsAndScore;
import game.messagebox.HallOfFameInputBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class HallOfFame extends JPanel {
	private JTable table = null;
	private String filename = null;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane = null;

	public HallOfFame() {
		setBorder(new LineBorder(Color.ORANGE, 2, true));
		setLayout(null);
		setOpaque(true);
		setBackground(new Color(0, 0, 0, 40));
		setBounds((CentralPanel.PANEL_WIDTH - 450) / 2, (CentralPanel.PANEL_HIGHT - 550) / 2, 450, 550);

		filename = getClass().getResource("/data/hiScores.ser").getFile();

		model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Socre");

		fillData();
		
		model.setRowCount(10);
		
		int rowHight = 400 / 10;
		table = new JTable(model);
		table.setFont(new Font("Dialog", Font.BOLD, 16));
		table.setForeground(Color.RED);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setRowHeight(rowHight);
		table.setOpaque(false);
		table.setBackground(new Color(0, 0, 0, 0));
		table.setTableHeader(null);
		table.setBorder(null);

		tableAlignCenter();
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(75, 70, 300, 400);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setOpaque(false);
		scrollPane.setViewportView(table);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		add(scrollPane);

		JLabel lblHallOfFame = new JLabel(ImageRepository.getInstance().getImageIcon(ImageRepository.IMAGEICON_HALL_OF_FAME));
		lblHallOfFame.setBounds(145, 12, 140, 50);
		add(lblHallOfFame);

		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closePanel();
			}

		});
		btnReturn.setBounds(343, 513, 95, 25);
		add(btnReturn);

	}

	private void tableAlignCenter() {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
	}

	public boolean mustEnterHallOfFame(long score) {
		Vector tmpData = null;

		for (int row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				if (col == 1) {
					Long tmpScore = ((Long) model.getValueAt(row, col));
					if (score > tmpScore) {
						tmpData = new Vector();
						tmpData.add(getUserName());
						tmpData.add(score);
						model.insertRow(row, tmpData);
						writeFile();
						return true;
					}
				}
				//System.out.println(model.getValueAt(row, col));
			}
		}

		return false;
	}

	private String getUserName() {
		HallOfFameInputBox hallOfFameInputBox = new HallOfFameInputBox(this);
		hallOfFameInputBox.setVisible(true);
		return hallOfFameInputBox.getInput();

		// return (String) JOptionPane.showInputDialog(this, "Enter your name",
		// "Customized Dialog", JOptionPane.PLAIN_MESSAGE);
	}

	private void fillData() {
		readFile();

	}

	private void closePanel() {
		this.setVisible(false);
	}

	public void readFile() {
		String line = null;
		String[] lineData = new String[3];
		Vector player = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				player = new Vector();
				lineData = line.split(" ");
				player.add(lineData[0]);
				player.add(new Long(lineData[1]));
				model.addRow(player);
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile() {
		String name = null;
		Long score = null;

		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			for (int row = 0; row < model.getRowCount(); row++) {
				for (int col = 0; col < model.getColumnCount(); col++) {
					if (col == 0) {
						name = (String) model.getValueAt(row, col);
					} else if (col == 1) {
						score = (Long) model.getValueAt(row, col);
					}
				}
				out.println(name + " " + score);
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(VitalsAndScore vitalsAndScore) {
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
			objectOut.writeObject(vitalsAndScore);
			objectOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
