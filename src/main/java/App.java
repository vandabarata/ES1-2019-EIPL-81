package main.java;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.bulenkov.darcula.DarculaLaf;
import main.java.controller.MainController;

public class App {

	public static void main(String[] args) {
		MainController c = new MainController();
		
		// sets application look and feel
		try {
			BasicLookAndFeel darcula = new DarculaLaf();
	        	UIManager.setLookAndFeel(darcula);
		} catch (Exception e) {}
		
		c.init();
	}
}
