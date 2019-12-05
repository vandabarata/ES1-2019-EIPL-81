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
import main.java.model.Metric;

/**
 * <h1>Main Controller</h1> Accepts input and converts it to commands and action
 * for the model or view. In addition to dividing the application into these
 * components, the model-view-controller design defines the interactions between
 * them.
 * <p>
 * <b>Note Model-View-Controller (MVC):</b> The Model is responsible for
 * managing the data of the application. It receives user input from the
 * controller. The View means presentation of the model in a particular format.
 * The controller receives the input, optionally validates it and then passes
 * the input to the model.
 */
public class MainController {
	private MainFrame gui;
	private QualityRulesResultFrame qualityGui;
	private String path;
	private ExcelImporter ei;
	private ArrayList<String[]> excelRows;
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();
	private ArrayList<CodeQualityRule> rulesList = new ArrayList<CodeQualityRule>();
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
	 * @return MainController
	 */
	public static MainController getMainControllerInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	/**
	 * This method is used to initiate the button listener
	 */
	public void init() {
		PopupUploadFile uploadFile = new PopupUploadFile();
		JButton import_button = uploadFile.getImportJButton();
		initImportButtonAction(import_button, uploadFile);
	}

	/**
	 * This method is used to run the action of the Import Button.
	 */
	private void initImportButtonAction(JButton importButton, PopupUploadFile uploadFile) {
		importButton.addActionListener(e -> validateFile(uploadFile));
	}

	/**
	 * This method is used to validate if the selected file is a valid Excel format,
	 */
	public boolean isValid(String pathFile) {
		return pathFile.endsWith(".xlsx") || pathFile.endsWith(".xls");
	}

	/**
	 * This method is used to import the file and create a main frame if the file is
	 * valid, otherwise it will show a warning message.
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
	 * Initialise the MainFrame and support Frames. Create necessary objects to
	 * support it.
	 */
	private void initMainFrame() {
		ei = new ExcelImporter(path);
		excelRows = ei.getAllRows();
		convertExcelRows();
		gui = new MainFrame(createExcelTable(), rulesList);
		qualityGui = new QualityRulesResultFrame();
		gui.getCheckQualityButton().addActionListener(e -> {

			checkCodeQualityAndShow();

		});

		editButton(this.gui.getEditButton(), this.gui.getRulesComboBox());
		addButton(this.gui.getAddButton());
	}

	/**
	 * Formats all data to a valid format to a JTable and returns a JTable with the
	 * cell's content
	 * 
	 * @return JTable
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
	 * This method is used to open the EditRuleController which controls the Rule
	 * Edition GUI
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
	 */
	private void addButton(JButton addButton) {
		addButton.addActionListener(e -> new EditRuleController());
	}

	/**
	 * Verify the code quality based on the Rules created and sends the results to
	 * be displayed in the QualityRulesResultFrame
	 * 
	 * @throws ScriptException
	 */
	private void checkCodeQualityAndShow() {
		String[][] results = null;
		results = getCodeQualityResults();
		String[] colNames = new String[5 + rulesList.size()];
		colNames[0] = "Method ID";
		colNames[1] = "long_method";
		colNames[2] = "feature_envy";
		colNames[3] = "PMD";
		colNames[4] = "iPlasma";
		int iterator = 5;
		for (CodeQualityRule rule : rulesList) {
			colNames[iterator] = rule.getName();
			iterator++;
		}

		if (results != null) {
			qualityGui.fillTable(results, colNames);
			qualityGui.show();
		}
	}

	/**
	 * 
	 * Returns the results of the calculation of each rule, for each method.
	 * 
	 * @return An Array of String arrays where each line is a row with the code
	 *         quality results for a method, and each column is the value of that
	 *         result line for that column
	 */
	private String[][] getCodeQualityResults() {
		String[][] results = new String[excelRowsConverted.size()][5 + rulesList.size()];
		int iterator = 0;
		for (ExcelRow row : excelRowsConverted) {
			String[] qualityRow = new String[4 + excelRowsConverted.size()];
			qualityRow[0] = Integer.toString(row.getId());
			qualityRow[1] = Boolean.toString(row.isIs_long_method());
			qualityRow[2] = Boolean.toString(row.isIs_feature_envy());
			qualityRow[3] = Boolean.toString(row.isPMD());
			qualityRow[4] = Boolean.toString(row.isiPlasma());
			int ruleIterator = 5;

			for (CodeQualityRule rule : rulesList) {
				try {
					qualityRow[ruleIterator] = getResult(rule, row);
					ruleIterator++;
				} catch (ScriptException e) {
					qualityGui.hide();
					JOptionPane.showMessageDialog(null,
							"Invalid rule syntax! Please verify the rule with the name \"" + rule + "\"!");
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
	 * @param rule The rule, the result of which we require.
	 * @param row  The excel row containing the methodID over which we wish to run
	 *             the rule.
	 * @return Returns the result of running the rule over the methodID of the given
	 *         ExcelRow, in string form.
	 * @throws ScriptException
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
	 * @param row The excel row with the values for our metrics.
	 * @return filledRule The Sting of rule initializations to be passed on to a
	 *         javascript engine.
	 */
	public String registerVariables(ExcelRow row) {
		int ATFD = row.getATFD();
		int CYCLO = row.getCYCLO();
		int LOC = row.getLOC();
		float LAA = row.getLAA();
		String filledRule = "var ATFD = " + ATFD + "; " + "var CYCLO = " + CYCLO + "; " + "var LOC = " + LOC + "; "
				+ "var LAA = " + LAA + "; ";
		return filledRule;
	}

	/**
	 * Returns the entire rules list
	 * 
	 * @return ArrayList<CodeQualityRule>
	 */
	public ArrayList<CodeQualityRule> getRulesList() {
		return rulesList;
	}

	/**
	 * Receives an updated list of rules and replaces the old rules list with it
	 * 
	 * @param newRules
	 */
	public void updateRulesList(ArrayList<CodeQualityRule> newRules) {
		rulesList = newRules;
		getMainFrame().updateRulesComboBox(newRules);
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
