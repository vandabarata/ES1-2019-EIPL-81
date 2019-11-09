package main.java.controller;

import java.util.ArrayList;

import main.java.gui.MainFrame;
import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

public class MainController {

	private MainFrame gui;
	private ExcelImporter ei;
	private ArrayList<String[]> excelRows;
	private ArrayList<ExcelRow> excelRowsConverted = new ArrayList<ExcelRow>();

	public MainController(MainFrame gui, String file) {
		this.ei = new ExcelImporter(file);
		this.excelRows = ei.getAllRows();
		convertExcelRows();
		this.gui = gui;
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
		gui.getCheckQualityButton().addActionListener(e -> {
			// TODO action listener to check quality btn
		});
	}
}
