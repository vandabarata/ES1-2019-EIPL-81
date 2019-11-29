package main.java;

import com.bulenkov.darcula.DarculaLaf;
import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import main.java.controller.MainController;

public class App {

	public static void main(String[] args) {
		MainController c = new MainController();
		
		// sets application look and feel
		try {
			BasicLookAndFeel darcula = new DarculaLaf();
	        	UIManager.setLookAndFeel(darcula);
		} catch (Exception ignored) {}
		
		c.init();
	}
}
