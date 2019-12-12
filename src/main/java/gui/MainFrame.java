package main.java.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import main.java.model.CodeQualityRule;

/**
 * The Main Frame where the excel information is displayed and from where the
 * user can edit and add rules, as well as calculate the code quality results
 *
 */
public class MainFrame {

	/** The frame of our MainFrame GUI */
	private JFrame mainFrame;

	/** The central panel in the MainFrame */
	private JPanel centralPanel;

	/** The panel on the bottom of the MainFrame */
	private JPanel southPanel;

	/** The Excel Table where the user can visualize the imported file's contents */
	private JTable excelTable;

	/** The JButton used for rule edition */
	private JButton editButton;

	/** The JButton used for addition of new rules */
	private JButton addButton;

	/** The JButton used for checking the code quality */
	private JButton checkQualityButton;

	/** The list of available rules */
	private ArrayList<CodeQualityRule> rulesList;

	/** The JComboBox containing all the rules in the rulesList */
	private JComboBox<Object> rulesDropDown;

	/**
	 * The mainframe constructor receives an excel table, generated from the given
	 * excel file, as well as a list containing all the rules and quality
	 * indicators, stored in the main controller
	 * 
	 * @param excelTable - A JTable generated from the imported excel file
	 * @param rulesList  - a list with the available rules - starts with 2 default
	 *                   rules
	 * 
	 */
	public MainFrame(JTable excelTable, ArrayList<CodeQualityRule> rulesList) {
		mainFrame = new JFrame();
		mainFrame.setSize(700, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout(5, 5));
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.excelTable = excelTable;
		this.rulesList = rulesList;
		addContents();
		mainFrame.setVisible(true);

	}

	/**
	 * Calls methods that will create a central panel and a south panel and adds it
	 * to the mainFrame.
	 */
	private void addContents() {
		createCentralPanel();
		mainFrame.add(centralPanel, BorderLayout.CENTER);
		createSouthPanel();
		mainFrame.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the central panel for the mainFrame that will be used to display the
	 * excel file. Its also created a ScrollPane so that the excel file can fits in
	 * the panel
	 */
	private void createCentralPanel() {
		centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());

		JScrollPane excelScrollPane;

		if (excelTable.getColumnCount() != 0)
			excelScrollPane = new JScrollPane(excelTable);
		else
			excelScrollPane = new JScrollPane();

		centralPanel.add(excelScrollPane);
	}

	/**
	 * Creates the south panel of the main frame and adds to it a panel that will
	 * display buttons.
	 */
	private void createSouthPanel() {
		southPanel = new JPanel();
		southPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
		southPanel.setLayout(new BorderLayout(5, 5));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1, 5, 5));

		addContentToButtonsPanel(buttonsPanel);
		southPanel.add(buttonsPanel, BorderLayout.EAST);
	}

	/**
	 * Creates and adds the add and edit buttons, dropdown with list of rules and
	 * checkQuality buttons to a panel
	 * 
	 * @param buttonsPanel - the panel user to display the buttons.
	 */
	private void addContentToButtonsPanel(JPanel buttonsPanel) {

		Object[] rules = rulesList.toArray();
		rulesDropDown = new JComboBox<Object>(rules);

		buttonsPanel.add(rulesDropDown);
		editButton = new JButton("Edit");
		buttonsPanel.add(editButton);

		addButton = new JButton("Add");
		buttonsPanel.add(addButton);

		checkQualityButton = new JButton("Check quality");
		buttonsPanel.add(checkQualityButton);
	}

	/**
	 * Returns the excel table generated from the given excel file as a JTable
	 * 
	 * @return JTable excelTable
	 */
	public JTable getExcelTable() {
		return excelTable;
	}

	/**
	 * Returns the JButton to check code quality
	 * 
	 * @return JButton checkQualityButton
	 */
	public JButton getCheckQualityButton() {
		return checkQualityButton;
	}

	/**
	 * Returns the JButton for rule edition
	 * 
	 * @return JButton editButton
	 */
	public JButton getEditButton() {
		return editButton;
	}

	/**
	 * Returns the JButton for adding new rules
	 * 
	 * @return JButton addButton
	 */
	public JButton getAddButton() {
		return addButton;
	}

	/**
	 * Returns the MainFrame's width
	 * 
	 * @return int width
	 */
	public int getFrameWidth() {
		return mainFrame.getWidth();
	}

	/**
	 * Returns the JComboBox holding the list of available rules
	 * 
	 * @return JComboBox rulesDropDown
	 */
	public JComboBox getRulesComboBox() {
		return rulesDropDown;
	}

	/**
	 * Receives an updated Rules List and updates the ComboBox
	 * 
	 * @param updatedRulesList - receives an updated list of rules
	 * 
	 */
	public void updateRulesComboBox(ArrayList<CodeQualityRule> updatedRulesList) {
		rulesDropDown.removeAllItems();

		Object[] updatedRules = updatedRulesList.toArray();

		for (Object rule : updatedRules) {
			rulesDropDown.addItem(rule);
		}
	}
}
