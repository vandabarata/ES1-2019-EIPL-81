package test.java.gui;

import javax.swing.*;
import main.java.gui.MainFrame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGUIMainFrame {
	
	private static MainFrame guiWithTable;
	private static MainFrame guiWithEmptyTable;
	
	@BeforeAll
	static void setUp() throws Exception {
		String[] header = {"header1", "header2"};
		String[][] content = {{"cell0",  "cell1"},{"cell2", "cell3"}};

		guiWithTable = new MainFrame(new JTable(content, header), null);
		guiWithEmptyTable = new MainFrame(new JTable(), null);
	}
	
	/**
	 * This tests the creation of a JTable in the GUI comparing the frames width
	 */
	@Test
	void testJTableCreation() {
		assertTrue(guiWithTable.getFrameWidth() > guiWithEmptyTable.getFrameWidth());
	}

}
