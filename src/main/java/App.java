package main.java;

import com.bulenkov.darcula.DarculaLaf;

import main.java.controller.MainController;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;

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
