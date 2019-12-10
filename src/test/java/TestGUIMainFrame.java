package test.java;

import javax.swing.*;

import main.java.controller.MainController;
import main.java.gui.MainFrame;
import main.java.model.CodeQualityRule;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

@RunWith(JUnitPlatform.class)
class TestGUIMainFrame {
	
	private static MainFrame guiWithTable;
	private static MainFrame guiWithEmptyTable;
	private MainFrame frame;
	
	@BeforeEach
	void setUp() {
		MainController main = MainController.getMainControllerInstance();
		frame = new MainFrame(new JTable(), main.getRulesList());
	}
	
	/**
	 * This tests the creation of a JTable in the GUI comparing the frames width
	 */
	@Test
	void testJTableCreation() {
		String[] header = {"header1", "header2"};
		String[][] content = {{"cell0",  "cell1"},{"cell2", "cell3"}};
		guiWithTable = new MainFrame(new JTable(content, header), MainController.getMainControllerInstance().getRulesList());
		assertEquals(2, guiWithTable.getExcelTable().getColumnCount());
	}
	
	/**
	 * Tests if after MainFrame construction the buttons were created and are not null
	 */
	@Test
	final void testGetCheckQualityButton() {
		assertNotNull(frame.getCheckQualityButton());
		assertNotNull(frame.getEditButton());
		assertNotNull(frame.getAddButton());
	}
	
	/**
	 * Tests getFrameWidth method
	 */
	@Test
	final void testGetFrameWidth() {
		assertEquals(700, frame.getFrameWidth());
	}

	/**
	 * Test getRulesComboBox method by checking if its created
	 */
	@Test
	final void testGetRulesComboBox() {
		assertNotNull(frame.getRulesComboBox());
	}

	/**
	 * Tests updateRulesComboBox by creating 2 new rules and checking if the updated ComboBox has the 
	 * same number of rules.
	 */	
	@Test
	final void testUpdateRulesComboBox() {
		CodeQualityRule	ruleDefault = new CodeQualityRule("is_long_method", "LOC > 80 && CYCLO > 10", true, false);
		CodeQualityRule potatoRule = new CodeQualityRule("is_potato", "ATFD > 4 && LAA < 0.42", false, false);
		ArrayList<CodeQualityRule> codeQualityArray = new ArrayList<CodeQualityRule>();
		codeQualityArray.add(ruleDefault);
		codeQualityArray.add(potatoRule);
		frame.updateRulesComboBox(codeQualityArray);
		assertEquals(2, frame.getRulesComboBox().getItemCount());
		
	}	
}



