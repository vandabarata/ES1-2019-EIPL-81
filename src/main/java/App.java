package main.java;

import main.java.controller.MainController;
import main.java.gui.MainFrame;

public class App {

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		String file = "Long-Method.xlsx";
		MainController c = new MainController(m, file);
		c.init();
	}
}
