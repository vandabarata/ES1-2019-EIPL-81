package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import main.java.gui.EditRulePopup;
import main.java.gui.MainFrame;
import main.java.gui.QualityRulesResultFrame;
import main.java.gui.PopupUploadFile;

import java.util.ArrayList;

import javax.swing.JTable;

import main.java.model.CodeQualityRule;
import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

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
	private EditRuleController editRuleController;
	private String path;
	private ExcelImporter ei;
	private ArrayList<String[]> excelRows;
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();
	private ArrayList<CodeQualityRule> rulesList = new ArrayList<CodeQualityRule>();

	/**
	 * MainController constructor. Creates the default rules to be used.
	 */
	public MainController() {
		CodeQualityRule is_long_method = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		CodeQualityRule is_feature_envy = new CodeQualityRule("is_feature_envy", "ATFD > 4 && LAA < 0.42", true, false);
		rulesList.add(is_long_method);
		rulesList.add(is_feature_envy);
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
	public void initImportButtonAction(JButton import_button, PopupUploadFile uploadFile) {
		import_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validateFile(uploadFile);
			}
		});
	}

	/**
	 * This method is used to validate if the selected file is a valid Excel format,
	 */
	public boolean isValid(String path_file) {
		if (path_file.endsWith(".xlsx") || path_file.endsWith(".xls"))
			return true;
		return false;
	}

	/**
	 * This method is used to import the file and create a main frame if the file is
	 * valid, otherwise it will show a warning message.
	 */
	public void validateFile(PopupUploadFile uploadFile) {

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
		gui = new MainFrame(createExcelTable(), rulesList);
		qualityGui = new QualityRulesResultFrame();
		gui.getCheckQualityButton().addActionListener(e -> checkCodeQualityAndShow());

		editButton(this.gui.getEditButton(), this.gui.getComboBox());
		addButton(this.gui.getAddButton());
	}

	/**
	 * Formats all data to a valid format to a JTable
	 * 
	 * @return String matrix with the cell's content
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
	 * @author Lino Silva
	 */
	private void convertExcelRows() {
		excelRows.forEach(element -> {

			try {
				excelRowsConverted.add(new ExcelRow(element));
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "An error occurred while converting the Excel Rows", "Warning",
						JOptionPane.WARNING_MESSAGE);
				throw e;
			}
		});
	}

	/**
	 * This method is used to run the action of the Edit Button with the selected
	 * rule of drop down
	 */
	public void editButton(JButton editButton, JComboBox ruleListBox) {
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				CodeQualityRule rule = (CodeQualityRule) ruleListBox.getSelectedItem();

				if (rule == null) {
					editRuleController = new EditRuleController();

				} else {
					editRuleController = new EditRuleController(rule);
				}

			}
		});
	}

	/**
	 * Sets the add Button in the MainFrame to open an empty Rule Edition Popup
	 */
	private void addButton(JButton addButton) {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editRuleController = new EditRuleController();
			}
		});
	}

	/**
	 * Verify the code quality based on the Rules created and sends the results to
	 * be displayed in the QualityRulesResultFrame
	 */
	private void checkCodeQualityAndShow() {
		String[][] results = getCodeQualityResults();
		// TODO get real column names
		String[] colNames = new String[] { "head 1", "head 2", "head 3" };
		qualityGui.fillTable(results, colNames);
		qualityGui.show();
	}

	/**
	 * @return An Array of String arrays where each line is a row with the code
	 *         quality results for a method, and each column is the value of that
	 *         result line for that column
	 */
	private String[][] getCodeQualityResults() {
		// TODO calculate code quality
		String[][] results = new String[][] { { "col 1", "col 2", "col 3" }, { "col 1", "col 2", "col 3" },
				{ "col 1", "col 2", "col 3" } };
		return results;
	}

}
