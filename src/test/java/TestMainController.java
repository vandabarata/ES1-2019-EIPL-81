package test.java.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.MainController;
import main.java.gui.MainFrame;
import main.java.gui.PopupUploadFile;
import main.java.model.CodeQualityRule;

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
	void testGetRulesList() {
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		CodeQualityRule rule1 = rulesList.get(0);
		CodeQualityRule rule2 = rulesList.get(1);
		assertEquals("is_long_method", rule1.getName());
		assertEquals("is_feature_envy", rule2.getName());
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
		assertNotNull("MainFrame shouldn't be null", frame);
	}

}
