package main.java;

import com.bulenkov.darcula.DarculaLaf;

import main.java.controller.MainController;

import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * Where the main application is run. This application is organized by the MVC
 * software design pattern. This can be summarised as such:
 * <p>
 * <b>Model-View-Controller (MVC):</b>
 * <p>
 * The Model is responsible for managing the data of the application. It
 * receives user input from the controller.
 * <p>
 * The View means presentation of the model in a particular format.
 * <p>
 * The Controller receives the input, optionally validates it and then passes
 * the input to the model. In addition to dividing the application into these
 * components, the model-view-controller design defines the interactions between
 * them.
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
