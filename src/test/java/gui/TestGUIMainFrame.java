package test.java.gui;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.gui.MainFrame;

class TestGUIMainFrame {
	
	private static MainFrame guiWithTable;
	private static MainFrame guiWithEmptyTable;
	
	@BeforeAll
	static void setUp() throws Exception {
		String[] header = {"header1", "header2"};
		String[][] content = {{"cell0",  "cell1"},{"cell2", "cell3"}};

		guiWithTable = new MainFrame(new JTable(content, header));
		guiWithEmptyTable = new MainFrame(new JTable());
	}
	
	/**
	 * This tests the creation of a JTable in the GUI comparing the frames width
	 */
	@Test
	void testJTableCreation() {
		assertEquals(true, guiWithTable.getFrameWidth() > guiWithEmptyTable.getFrameWidth());
		
		assertNotEquals(guiWithTable.getFrameWidth(), guiWithEmptyTable.getFrameWidth());
	}

}
