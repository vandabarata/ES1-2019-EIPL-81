package main.java;

import main.java.controller.MainController;
import main.java.gui.MainFrame;

public class App {

	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		MainController c = new MainController(m);
		c.init();
	}
}
