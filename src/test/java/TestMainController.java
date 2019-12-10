package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.MainController;
import main.java.gui.MainFrame;
import main.java.model.CodeQualityRule;
import main.java.model.ExcelRow;

@RunWith(JUnitPlatform.class)
class TestMainController {
	
	MainController mainC;

	@BeforeEach
	void setUp() throws Exception {
		mainC = MainController.getMainControllerInstance();
	}

	@Test
	void testInit() {
		mainC.init();
	}

	@Test
	void testIsValid() {
		String pathFile = "excel.xls";
		assertTrue(mainC.isValid(pathFile));
		String fileX = "excel.xlsx";
		assertTrue(mainC.isValid(fileX));
		String invalidFile = "potato";
		assertFalse(mainC.isValid(invalidFile));
	}
	
	@Test
	void testRegisterVariables() {
		String[] rowData =  {"1", "fat", "GrammarException", "parseMethod()", "3", "45", "1", "0.55", "TRUE", "FALSE", "TRUE", "TRUE"};
		ExcelRow row = new ExcelRow(rowData);
		String variables = mainC.registerVariables(row);
		assertEquals("var ATFD = 1, CYCLO = 45, LOC = 3, LAA = 0.55;", variables);
	}


	@Test
	void testGetRulesList() {
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		CodeQualityRule rule1 = rulesList.get(0);
		CodeQualityRule rule2 = rulesList.get(1);
		assertEquals("custom_is_long_method", rule1.getName());
		assertEquals("custom_is_feature_envy", rule2.getName());
	}
	
	@Test
	void testQualityIndicator() {
		mainC.getQualityIndicator();
	}

	@Test
	void testUpdateRulesList() {
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		CodeQualityRule newRule = new CodeQualityRule("new", "LOC > 10", false, false);
		rulesList.add(newRule);
		mainC.updateRulesList(rulesList);
		assertEquals(rulesList, mainC.getRulesList());
	}

	@Test
	void testGetMainFrame() {
		MainFrame frame = mainC.getMainFrame();
	}

}
