package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class MainFrame {
	private JFrame mainFrame;
	private JPanel centralPanel;
	private JPanel southPanel;
	private JTable excelTable;
	private JButton add_editButton;
	private JButton checkQualityButton;

	// TODO: receber por argumento o ficheiro excel
	public MainFrame(JTable excelTable) {
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.excelTable = excelTable;
		
		addContents();
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void addContents() {
		createCentralPanel();
		mainFrame.add(centralPanel, BorderLayout.CENTER);
		
		createSouthPanel();
		mainFrame.add(southPanel, BorderLayout.SOUTH);
	}

	private void createCentralPanel() {
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		
		JScrollPane excelScrollPane = new JScrollPane(excelTable);

		centralPanel.add(excelScrollPane);
	}

	private void createSouthPanel() {
		southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		
		JPanel fileResultsPanel = new JPanel();
		fileResultsPanel.setLayout(new GridLayout(3, 5));
		
		addContentToFileResultsforPanel(fileResultsPanel);
		southPanel.add(fileResultsPanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		
		addContentToButtonsPanel(buttonsPanel);
		southPanel.add(buttonsPanel, BorderLayout.EAST);
	}

	private void addContentToButtonsPanel(JPanel buttonsPanel) {
		add_editButton = new JButton("Add/ Edit Rules");
		buttonsPanel.add(add_editButton);

		checkQualityButton = new JButton("Check quality");
		buttonsPanel.add(checkQualityButton);
	}

	// This Method must receive The results of the excel
	private void addContentToFileResultsforPanel(JPanel fileResultsPanel) {
		fileResultsPanel.add(new JLabel(""));
		fileResultsPanel.add(new JLabel("DCI"));
		fileResultsPanel.add(new JLabel("DII"));
		fileResultsPanel.add(new JLabel("ADCI"));
		fileResultsPanel.add(new JLabel("ADII"));
		fileResultsPanel.add(new JLabel("     iPlasma  "));
		fileResultsPanel.add(new JLabel("a"));
		fileResultsPanel.add(new JLabel("b"));
		fileResultsPanel.add(new JLabel("c"));
		fileResultsPanel.add(new JLabel("d"));
		fileResultsPanel.add(new JLabel("     PMD"));
		fileResultsPanel.add(new JLabel("e"));
		fileResultsPanel.add(new JLabel("f"));
		fileResultsPanel.add(new JLabel("g"));
		fileResultsPanel.add(new JLabel("h"));
	}

//TODO: Usar argumento Excel

	public JTable getExcelTable() {
		return excelTable;
	}

	public JButton getAdd_editButton() {
		return add_editButton;
	}

	public JButton getCheckQualityButton() {
		return checkQualityButton;
	}

}
