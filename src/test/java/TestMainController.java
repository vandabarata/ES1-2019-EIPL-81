package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.MainController;
import main.java.model.CodeQualityRule;
import main.java.model.ExcelRow;

/**
 * Tests for the Main Controller
 *
 */
@RunWith(JUnitPlatform.class)
class TestMainController {

	/** MainController object used for testing */
	MainController mainC;

	/**
	 * Gets the singleton MainController instance
	 * 
	 * @throws Exception - Exception is thrown if it fails to get the singleton
	 *                   instance
	 */
	@BeforeEach
	void setUp() throws Exception {
		mainC = MainController.getMainControllerInstance();
	}

	/**
	 * Validates that the MainController can be initialised with errors
	 */
	@Test
	void testInit() {
		mainC.init();
	}

	/**
	 * Asserts that invalid files aren't accepted and valid ones are
	 */
	@Test
	void testIsValid() {
		String pathFile = "excel.xls";
		assertTrue(mainC.isValid(pathFile));
		String fileX = "excel.xlsx";
		assertTrue(mainC.isValid(fileX));
		String invalidFile = "potato";
		assertFalse(mainC.isValid(invalidFile));
	}

	/**
	 * Validates that the RegisterVariables methods is correctly parsing the
	 * information from the excel file
	 */
	@Test
	void testRegisterVariables() {
		String[] rowData = { "1", "fat", "GrammarException", "parseMethod()", "3", "45", "1", "0.55", "TRUE", "FALSE",
				"TRUE", "TRUE" };
		ExcelRow row = new ExcelRow(rowData);
		String variables = mainC.registerVariables(row);
		assertEquals("var ATFD = 1, CYCLO = 45, LOC = 3, LAA = 0.55;", variables);
	}

	/**
	 * Asserts that the rules list being returned has the expected rules in it
	 */
	@Test
	void testGetRulesList() {
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		CodeQualityRule rule1 = rulesList.get(0);
		CodeQualityRule rule2 = rulesList.get(1);
		assertEquals("custom_is_long_method", rule1.getName());
		assertEquals("custom_is_feature_envy", rule2.getName());
	}

	/**
	 * Validates that the quality indicator object is being correctly initialised
	 */
	@Test
	void testQualityIndicator() {
		mainC.getQualityIndicator();
	}

	/**
	 * Asserts that the rules list is being correctly updated when new rules are
	 * added
	 */
	@Test
	void testUpdateRulesList() {
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		CodeQualityRule newRule = new CodeQualityRule("new", "LOC > 10", false, false);
		rulesList.add(newRule);
		mainC.updateRulesList(rulesList);
		assertEquals(rulesList, mainC.getRulesList());
	}

	/**
	 * Validates that the main fram is being correctly initialised
	 */
	@Test
	void testGetMainFrame() {
		mainC.getMainFrame();
	}

}
