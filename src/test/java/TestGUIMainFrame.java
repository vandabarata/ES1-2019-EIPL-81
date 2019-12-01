package test.java;

import javax.swing.*;

import main.java.controller.MainController;
import main.java.gui.MainFrame;
import main.java.model.CodeQualityRule;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitPlatform.class)
class TestGUIMainFrame {
	
	private static MainFrame guiWithTable;
	private static MainFrame guiWithEmptyTable;
	
	@BeforeAll
	static void setUp() throws Exception {
		String[] header = {"header1", "header2"};
		String[][] content = {{"cell0",  "cell1"},{"cell2", "cell3"}};

		guiWithTable = new MainFrame(new JTable(content, header), MainController.getMainControllerInstance().getRulesList());
		guiWithEmptyTable = new MainFrame(new JTable(), MainController.getMainControllerInstance().getRulesList());
	}
	
	/**
	 * This tests the creation of a JTable in the GUI comparing the frames width
	 */
	@Test
	void testJTableCreation() {
		assertTrue(guiWithTable.getFrameWidth() > guiWithEmptyTable.getFrameWidth());
	}

}
