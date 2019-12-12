package main.java.controller;

import java.io.File;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;

import main.java.gui.MainFrame;
import main.java.gui.PopupUploadFile;
import main.java.gui.QualityRulesResultFrame;
import main.java.model.CodeQualityRule;
import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;
import main.java.model.QualityIndicator;

/**
 * Main Controller - Accepts input and converts it to commands and action for
 * the model or view.
 */
public class MainController {

	/** Mainframe where the main application runs */
	private MainFrame gui;

	/** Frame where the code quality check results are presented */
	private QualityRulesResultFrame qualityGui;

	/** String indicating the path where the excel file is located */
	private String path;

	/** Object that deals with importing the excel file */
	private ExcelImporter ei;

	/**
	 * ArrayList with arrays of strings, containing raw data from the excel in
	 * string form
	 */
	private ArrayList<String[]> excelRows;

	/** ArrayList of ExcelRows with all the excel information */
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();

	/** ArrayList of CodeQualityRules, listing all the existent rules */
	private ArrayList<CodeQualityRule> rulesList = new ArrayList<CodeQualityRule>();

	/**
	 * Constants that determine the column where the specified results can be
	 * retrived from
	 */
	private final int METHOD_ID_INDEX = 0;
	private final int PMD_INDEX = 10;
	private final int IPLASMA_INDEX = 9;
	private final int IS_LONG_METHOD__INDEX = 8;
	private final int IS_FEATURE_ENVY__INDEX = 11;

	/**
	 * Object responsible for calculating the quality indicators such as DCI, DII,
	 * ADCI and ADII
	 */
	private QualityIndicator qualityIndicator;

	/** Single instance of the MainController */
	private static MainController instance;

	/**
	 * Singleton MainController - only 1 instance allowed. Creates the default rules
	 * to be used and manages the Main Frame.
	 */
	private MainController() {
		CodeQualityRule is_long_method = new CodeQualityRule("custom_is_long_method", "LOC > 80 && CYCLO > 10", true,
				true);
		CodeQualityRule is_feature_envy = new CodeQualityRule("custom_is_feature_envy", "ATFD > 4 && LAA < 0.42", true,
				true);
		rulesList.add(is_long_method);
		rulesList.add(is_feature_envy);
	}

	/**
	 * Public method that returns the main controller singleton instance
	 * 
	 * @return MainController instance
	 */
	public static MainController getMainControllerInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	/**
	 * Initialises the file upload frame and the button listeners
	 */
	public void init() {
		PopupUploadFile uploadFile = new PopupUploadFile();
		JButton importButton = uploadFile.getImportJButton();
		initImportButtonAction(importButton, uploadFile);
	}

	/**
	 * Initialises the import button's action listener
	 * 
	 * @param importButton - button used to import the file
	 * @param uploadFile   - frame where the file is imported
	 */
	private void initImportButtonAction(JButton importButton, PopupUploadFile uploadFile) {
		importButton.addActionListener(e -> validateFile(uploadFile));
	}

	/**
	 * Validates if the selected file is a valid Excel format
	 * 
	 * @param pathFile - the path to the imported file's location
	 * @return boolean to validate if file is valid or not
	 */
	public boolean isValid(String pathFile) {
		return pathFile.endsWith(".xlsx") || pathFile.endsWith(".xls");
	}

	/**
	 * Imports the file and creates a main frame if the file is valid. Shows a
	 * warning message otherwise.
	 * 
	 * @param uploadFile - frame where the excel file is imported
	 */
	private void validateFile(PopupUploadFile uploadFile) {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();

			if (isValid(path)) {
				uploadFile.close();
				initMainFrame();
			} else {
				uploadFile.displayErrorMessage("File selected is not a valid Excel format!");
			}
		}
	}

	/**
	 * Initialises the MainFrame and necessary objects to support it, as well as the
	 * buttons' action listeners
	 */
	private void initMainFrame() {
		ei = new ExcelImporter(path);
		excelRows = ei.getAllRows();
		convertExcelRows();
		gui = new MainFrame(createExcelTable(), rulesList);
		gui.getCheckQualityButton().addActionListener(e -> checkCodeQualityAndShow());

		editButton(this.gui.getEditButton(), this.gui.getRulesComboBox());
		addButton(this.gui.getAddButton());
	}

	/**
	 * Formats all data to a valid format to a JTable and returns a JTable with the
	 * cells' content
	 * 
	 * @return JTable - Excel Table with the cells' contents, coverted from the
	 *         initial excel file
	 */
	private JTable createExcelTable() {
		String[][] dataForTable = new String[excelRows.size() - 1][excelRows.get(1).length];

		for (int i = 0; i < dataForTable.length; i++) {
			for (int j = 0; j < dataForTable[i].length; j++) {
				dataForTable[i][j] = excelRows.get(i + 1)[j].toString();
			}
		}

		return new JTable(dataForTable, excelRows.get(0));
	}

	/**
	 * Converts all the valid rows into ExcelRow model
	 * 
	 * Starts at index 1 because we only want to convert the table content and not
	 * the header
	 */
	private void convertExcelRows() {
		for (int i = 1; i < excelRows.size(); i++) {
			try {
				excelRowsConverted.add(new ExcelRow(excelRows.get(i)));
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occurred while converting the Excel Rows", "Warning",
						JOptionPane.WARNING_MESSAGE);
				throw e;
			}
		}
	}

	/**
	 * Opens the EditRuleController which controls the Rule Edition GUI
	 * 
	 * @param editButton  - button used to open a new window to edit the rules
	 * @param ruleListBox - the combobox which lists all the available rules
	 */
	private void editButton(JButton editButton, JComboBox<CodeQualityRule> ruleListBox) {

		editButton.addActionListener(e -> {

			CodeQualityRule rule = (CodeQualityRule) ruleListBox.getSelectedItem();

			if (rule == null) {
				new EditRuleController();

			} else {
				new EditRuleController(rule);
			}
		});
	}

	/**
	 * Sets the add Button in the MainFrame to open an empty Rule Edition Popup
	 * 
	 * @param addButton - button that opens a new window to add new rules
	 */
	private void addButton(JButton addButton) {
		addButton.addActionListener(e -> new EditRuleController());
	}

	/**
	 * Verify the code quality based on the Rules created and sends the results to
	 * be displayed in the QualityRulesResultFrame
	 * 
	 */
	private void checkCodeQualityAndShow() {
		String[][] results = null;
		results = getCodeQualityResults();
		String[] colNames = new String[5 + rulesList.size()];
		colNames[0] = excelRows.get(0)[METHOD_ID_INDEX];
		colNames[1] = excelRows.get(0)[IS_LONG_METHOD__INDEX];
		colNames[2] = excelRows.get(0)[IS_FEATURE_ENVY__INDEX];
		colNames[3] = excelRows.get(0)[PMD_INDEX];
		colNames[4] = excelRows.get(0)[IPLASMA_INDEX];
		int iterator = 5;
		for (CodeQualityRule rule : rulesList) {
			colNames[iterator] = rule.getName();
			iterator++;
		}

		if (results != null) {
			qualityIndicator = new QualityIndicator(results);
			if (qualityGui != null) {
				qualityGui.hide();
			}
			qualityGui = new QualityRulesResultFrame();
			qualityGui.fillFrame(results, colNames, qualityIndicator);
			qualityGui.show();
		}
	}

	/**
	 * 
	 * Returns the results of the calculation of each rule, for each method.
	 * 
	 * @return A matrix of strings where each line is a row with the code quality
	 *         results for a method, and each column is a rule or tool, for which
	 *         we're presenting results
	 */
	private String[][] getCodeQualityResults() {
		String[][] results = new String[excelRowsConverted.size()][5 + rulesList.size()];
		int iterator = 0;
		for (ExcelRow row : excelRowsConverted) {
			String[] qualityRow = new String[4 + excelRowsConverted.size()];
			qualityRow[0] = Integer.toString(row.getId());
			qualityRow[1] = Boolean.toString(row.isLongMethod());
			qualityRow[2] = Boolean.toString(row.isFeatureEnvy());
			qualityRow[3] = Boolean.toString(row.getPMDResult());
			qualityRow[4] = Boolean.toString(row.getIPlasmaResult());
			int ruleIterator = 5;

			for (CodeQualityRule rule : rulesList) {
				try {
					qualityRow[ruleIterator] = getResult(rule, row);
					ruleIterator++;
				} catch (ScriptException e) {
					JOptionPane.showMessageDialog(null,
							"Invalid rule syntax! Please verify the conditions for the rule  \"" + rule + "\"!");
					return null;
				}
			}
			results[iterator] = qualityRow;
			iterator++;
		}
		return results;

	}

	/**
	 * Runs a rule over an excelRow and returns the result.
	 * 
	 * @param rule - The rule, the result of which we require.
	 * @param row  - The excel row containing the methodID over which we wish to run
	 *             the rule.
	 * @return result - Returns the result of running the rule over the methodID of
	 *         the given ExcelRow, in string form.
	 * @throws ScriptException - An exception is thrown if there are invalid rule
	 *                         conditions
	 */
	private String getResult(CodeQualityRule rule, ExcelRow row) throws ScriptException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("ECMAScript");
		String filledRule = registerVariables(row);
		Object result = null;
		result = engine.eval(filledRule + "eval('" + rule.getRule() + "');");
		return Boolean.toString(Boolean.TRUE.equals(result));
	}

	/**
	 * Creates and returns a string ready to be passed on to a javascript engine,
	 * which initializes all the necessary metric variables.
	 * 
	 * @param row - The excel row with the values for our metrics.
	 * @return String filledRule - The String of metrics turned into variables to
	 *         use in the JS engine for running the rules.
	 */
	public String registerVariables(ExcelRow row) {
		int ATFD = row.getATFD();
		int CYCLO = row.getCYCLO();
		int LOC = row.getLOC();
		float LAA = row.getLAA();
		String filledRule = "var ATFD = " + ATFD + ", " + "CYCLO = " + CYCLO + ", " + "LOC = " + LOC + ", " + "LAA = "
				+ LAA + ";";
		return filledRule;
	}

	/**
	 * Returns the entire rules list
	 * 
	 * @return List with all the rules
	 */
	public ArrayList<CodeQualityRule> getRulesList() {
		return rulesList;
	}

	/**
	 * Returns the QualityIndicator object
	 * 
	 * @return QualityIndicator - Object that manages the code quality results
	 */
	public QualityIndicator getQualityIndicator() {
		return qualityIndicator;
	}

	/**
	 * Receives an updated list of rules and replaces the old rules list with it
	 * 
	 * @param newRules - New list of rules to consider
	 */
	public void updateRulesList(ArrayList<CodeQualityRule> newRules) {
		rulesList = newRules;
	}

	/**
	 * Returns the main frame
	 * 
	 * @return MainFrame
	 */
	public MainFrame getMainFrame() {
		return gui;
	}

}
