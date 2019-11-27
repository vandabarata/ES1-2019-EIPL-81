package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame {
	private JFrame mainFrame;
	private JPanel centralPanel;
	private JPanel southPanel;
	private JTable excelTable;
	private JButton editButton;
	private JButton addButton;
	private JButton checkQualityButton;
	private JComboBox rulesDropDown;

	public MainFrame(JTable excelTable) {
		mainFrame = new JFrame();
		mainFrame.setSize(700, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout(5,5));
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.excelTable = excelTable;
		addContents();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Calls methods that will create a central panel and a south panel
	 * and adds it to the mainFrame.
	 */
	private void addContents() {
		createCentralPanel();
		mainFrame.add(centralPanel, BorderLayout.CENTER);
		createSouthPanel();
		mainFrame.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the central panel for the mainFrame that will be used 
	 * to display the excel file. Its also created a ScrollPane so that the
	 * excel file can fits in the panel 
	 */
	private void createCentralPanel() {
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		
		JScrollPane excelScrollPane;
		
		if(excelTable.getColumnCount() != 0)
			excelScrollPane = new JScrollPane(excelTable);
		else
			excelScrollPane = new JScrollPane();
		
		centralPanel.add(excelScrollPane);
	}
	
	/**
	 * This method creates the south panel of the main frame and adds to it a
	 * panel that will display the results of reading the excel file, and 
	 * another panel that will display buttons.
	 */
	private void createSouthPanel() {
		southPanel = new JPanel();
		southPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
		southPanel.setLayout(new BorderLayout(5,5));
		
		JPanel fileResultsPanel = new JPanel();
		fileResultsPanel.setLayout(new GridLayout(3,5,5,5));
		
		addContentToFileResultsforPanel(fileResultsPanel);
		southPanel.add(fileResultsPanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2,1,5,5));
		
		addContentToButtonsPanel(buttonsPanel);
		southPanel.add(buttonsPanel, BorderLayout.EAST);
	}
	
	/**
	 * This method creates and adds the add/edit, drop drown with list of rules
	 *  and checkQuality buttons to a panel
	 * @param buttonsPanel is the panel user to display the buttons.
	 */
	private void addContentToButtonsPanel(JPanel buttonsPanel) {

    //TODO Adicionar a Lista de das regras existentes
		
		String[] rulesList = { "rule A", "rule B", "rule C", "rule D", "rule E" };
		rulesDropDown = new JComboBox(rulesList);
		buttonsPanel.add(rulesDropDown);
		editButton = new JButton("Edit");
		buttonsPanel.add(editButton);
		
		addButton = new JButton("Add");
    add_editButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				EditRulePopup m = new EditRulePopup();
				
		}
		});
		buttonsPanel.add(addButton);

		checkQualityButton = new JButton("Check quality");
		buttonsPanel.add(checkQualityButton);
	}

	/**
	 * This method fills a panel with a grid layout of 3 by 5 with JLabels.
	 * Which one of the JLabels is added accordingly with the layout pretended and displays
	 * the results extracted from reading the excel file. 
	 * @param fileResultsPanel is the panel used to add the JLabels and display the results.
	 */
	//TODO: This Method must receive The results of the excel 
	private void addContentToFileResultsforPanel(JPanel fileResultsPanel) {		
		fileResultsPanel.add(new JLabel(""));
		fileResultsPanel.add(new JLabel("DCI"));
		fileResultsPanel.add(new JLabel("DII"));
		fileResultsPanel.add(new JLabel("ADCI"));
		fileResultsPanel.add(new JLabel("ADII"));
		fileResultsPanel.add(new JLabel("iPlasma"));
		fileResultsPanel.add(new JLabel("a"));
		fileResultsPanel.add(new JLabel("b"));
		fileResultsPanel.add(new JLabel("c"));
		fileResultsPanel.add(new JLabel("d"));
		fileResultsPanel.add(new JLabel("PMD"));
		fileResultsPanel.add(new JLabel("e"));
		fileResultsPanel.add(new JLabel("f"));
		fileResultsPanel.add(new JLabel("g"));
		fileResultsPanel.add(new JLabel("h"));
	}
	
	
	//TODO: Usar argumento Excel
	public JTable getExcelTable() {
		return excelTable;
	}

	public JButton getCheckQualityButton() {
		return checkQualityButton;
	}
	
	/**
	 * This method is used to communicate the instance of Edit JButton  to MainController.
	 * The MainController is responsible to run the action of Edit button.
	 * 
	 * @return JButton
	 */
	public JButton getEditButton() {
		return editButton;
	}
	
	/**
	 * This method is used to communicate the instance of Add JButton  to MainController.
	 * The MainController is responsible to run the action of Add button.
	 * 
	 * @return JButton
	 */
	public JButton getAddButton() {
		return addButton;
	}

	/**
	 * This method is used to communicate the instance of Check Quality JButton  to MainController.
	 * The MainController is responsible to run the action of Check Quality button.
	 * 
	 * @return JButton
	 */

	public int getFrameWidth() {
		return mainFrame.getWidth();
	}
	
	
	/**
	 * This method is used to communicate the instance of JComboBox  to MainController.
	 * The MainController is responsible to run the action related with selected rule of rule list showed in JComboBox.
	 * 
	 * @return JComboBox
	 */
	public JComboBox getComboBox() {
		return rulesDropDown;
	}
}
