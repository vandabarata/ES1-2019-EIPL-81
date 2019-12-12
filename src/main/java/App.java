package main.java;

import com.bulenkov.darcula.DarculaLaf;

import main.java.controller.MainController;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * Where the main application is run This application is organized by the MVC
 * model. This can be summarised as such:
 * <p>
 * <b>Model-View-Controller (MVC):</b> The Model is responsible for managing the
 * data of the application. It receives user input from the controller. The View
 * means presentation of the model in a particular format. The Controller
 * receives the input, optionally validates it and then passes the input to the
 * model.
 *
 */
public class App {

	public static void main(String[] args) {
		MainController c = MainController.getMainControllerInstance();

		// sets application look and feel
		try {
			BasicLookAndFeel darcula = new DarculaLaf();
			UIManager.setLookAndFeel(darcula);
		} catch (Exception ignored) {
		}

		c.init();
	}
}
