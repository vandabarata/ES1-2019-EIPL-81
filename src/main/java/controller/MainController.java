package main.java.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

import main.java.gui.MainFrame;
import main.java.gui.QualityRulesResultFrame;
import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

public class MainController {

	private MainFrame gui;
	private QualityRulesResultFrame qualityGui;
	private ExcelImporter ei;
	private ArrayList<String[]> excelRows;
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();

	public MainController(String file) {
		this.ei = new ExcelImporter(file);
		this.excelRows = ei.getAllRows();
		this.gui = new MainFrame(createExcelTable());
		this.qualityGui = new QualityRulesResultFrame();
	}
	
	/**
	 * Formats all data to a valid format to a JTable
	 * 
	 * @return String matrix with the cell's content
	 */
	private JTable createExcelTable() {
		String[][] dataForTable = new String[excelRows.size()-1][excelRows.get(1).length];
		
		for(int i = 0; i < dataForTable.length; i++) {
			for(int j = 0; j < dataForTable[i].length; j++) {
				dataForTable[i][j] = excelRows.get(i+1)[j].toString();
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

	public void init() {
		// TODO add action listeners to gui
		gui.getAdd_editButton().addActionListener(e -> {
			// TODO action listener to add/edit btn
		});
		gui.getCheckQualityButton().addActionListener(e -> checkCodeQualityAndDisplay());
	}
	
	private void checkCodeQualityAndDisplay() {
		String[][] results = getCodeQualityResults();
		// TODO get real column names
		String[] colNames = new String[] {"head 1", "head 2", "head 3"};
		qualityGui.fillTable(results, colNames);
		qualityGui.show();
	}
	
	private String[][] getCodeQualityResults() {
		// TODO calculate code quality
		String[][] results = new String[][] {{"col 1", "col 2", "col 3"}, {"col 1", "col 2", "col 3"}, {"col 1", "col 2", "col 3"}};
		return results;
	}
}
