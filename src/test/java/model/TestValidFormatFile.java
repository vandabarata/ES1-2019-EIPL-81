package test.java.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import main.java.controller.MainController;

/**
 * @author Diego Souza
 *
 */
public class TestValidFormatFile {

	/**
	 * This test compares when the file has valid Excel format if the isValid()
	 * method return True
	 */
	@Test
	void TestValidFormat() {
		MainController controller = new MainController();
		String path_file = "C/Documents/file.xlsx";
		assertEquals(true, controller.isValid(path_file));
	}

	/**
	 * This test compares when the file hasn't valid Excel format if the isValid()
	 * method return False
	 */
	@Test
	void TestNotValidFormat() {
		MainController controller = new MainController();
		String path_file = "C/Documents/file.pptx";
		assertEquals(false, controller.isValid(path_file));
	}

}
