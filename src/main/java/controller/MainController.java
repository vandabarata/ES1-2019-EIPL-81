package main.java.controller;

import main.java.gui.MainFrame;
import main.java.model.ExcelImporter;

public class MainController {
	
	private MainFrame gui;
	private ExcelImporter ei;
	
	public MainController(MainFrame gui, String file) {
		this.ei = new ExcelImporter(file);
		this.gui = gui;
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
