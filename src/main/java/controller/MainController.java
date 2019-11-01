package main.java.controller;

import main.java.gui.MainFrame;

public class MainController {
	
	private MainFrame gui;
	
	public MainController(MainFrame gui) {
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
