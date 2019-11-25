package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import main.java.gui.MainFrame;
import main.java.gui.Popup_UploadFile;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

/**
 * <h1>Main Controller</h1> Accepts input and converts it to commands and action
 * for the model or view. In addition to dividing the application into these
 * components, the model–view–controller design defines the interactions between
 * them.
 * <p>
 * <b>Note Model–View–Controller (MVC):</b> The Model is responsible for
 * managing the data of the application. It receives user input from the
 * controller. The View means presentation of the model in a particular format.
 * The controller receives the input, optionally validates it and then passes
 * the input to the model.
 */

public class MainController {
	private MainFrame gui;
	private String path;
	private ExcelImporter ei;
	private ArrayList<String[]> excelRows;
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();

	/**
	 * This method is used to initiate the button listener
	 */
	public void init() {
		Popup_UploadFile uploadFile = new Popup_UploadFile();
		JButton import_button = uploadFile.getImportJButton();
		initImportButtonAction(import_button, uploadFile);
	}

	/**
	 * This method is used to run the action of the Import Button.
	 */
	public void initImportButtonAction(JButton import_button, Popup_UploadFile uploadFile) {
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
	 * This method is used to import the file and create a main frame If the file is
	 * valid otherwise it's showed a warning message.
	 */
	public void validateFile(Popup_UploadFile uploadFile) {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			path = selectedFile.getAbsolutePath();

			if (isValid(path)) {
				uploadFile.close();
				this.ei = new ExcelImporter(path);
				this.excelRows = ei.getAllRows();
				this.gui = new MainFrame(createExcelTable());
				editButton(this.gui.get_editButton(), this.gui.getComboBox());
			} else {
				uploadFile.displayErrorMessage("File selected is not a valid Excel format!");
			}
		}
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
			} catch (Exception e) {

			}
		});
	}
	
	/**
	 * This method is used to run the action of the Edit Button 
	 * with the selected rule of drop down
	 */
	public void editButton(JButton editButton, JComboBox checkbox) {
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ruleName = (String)checkbox.getSelectedItem();
				System.out.print(ruleName);
				
				//Correr o pop up do Hugo para Editar Regras e a lógica associada
			}
		});
	}
	
}
