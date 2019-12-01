package main.java;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;
import main.java.controller.MainController;

public class App {

	public static void main(String[] args) {
		MainController c = MainController.getMainControllerInstance();
		
		// sets application look and feel
		try {
			BasicLookAndFeel darcula = new DarculaLaf();
			UIManager.setLookAndFeel(darcula);
		} catch (Exception ignored) {}
		
		c.init();
	}
}
