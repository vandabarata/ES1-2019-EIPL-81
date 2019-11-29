package test.java.model;

import main.java.controller.MainController;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;


/**
 * @author Diego Souza
 *
 */
class TestValidFormatFile {

	/**
	 * This test compares when the file has valid Excel format if the isValid()
	 * method return True
	 */
	@Test
	void TestValidFormat() {
		MainController controller = new MainController();
		String path_file = "C://Documents/file.xlsx";
		assertTrue(controller.isValid(path_file));
	}

	/**
	 * This test compares when the file hasn't valid Excel format if the isValid()
	 * method return False
	 */
	@Test
	void TestNotValidFormat() {
		MainController controller = new MainController();
		String path_file = "C://Documents/file.pptx";
		assertFalse(controller.isValid(path_file));
	}

}
