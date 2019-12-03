package test.java;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.EditRuleController;
import main.java.controller.MainController;
import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

class TestEditRuleController {

	CodeQualityRule testRule;
	CodeQualityRule testDefaultRuleLongMethod;
	CodeQualityRule testDefaultRuleFeatureEnvy;
	EditRuleController controller;

	@BeforeEach
	void setUp() throws Exception {
		testRule = new CodeQualityRule("test", "LOC > 10", false, false);
		testDefaultRuleLongMethod = new CodeQualityRule("custom_is_long_method", "LOC > 80 && CYCLO > 10", true, true);
		testDefaultRuleFeatureEnvy = new CodeQualityRule("custom_is_feature_envy", "ATFD > 4 && LAA < 0.42", true,
				true);

	}

	@Test
	void testEditRuleControllerWithRule() {
		controller = new EditRuleController(testRule);
	}

	@Test
	void testEditRuleControllerNoRule() {
		controller = new EditRuleController();
	}

	@Test
	void testDeleteRule() {
		MainController mc = MainController.getMainControllerInstance();
		ArrayList<CodeQualityRule> rulesList = mc.getRulesList();
		rulesList.add(testRule);
		mc.updateRulesList(rulesList);
		controller = new EditRuleController(testRule);
		try {
			controller.deleteRule();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(testRule));
	}

	@Test
	void testDeleteDefaultRule() {
		MainController mc = MainController.getMainControllerInstance();
		ArrayList<CodeQualityRule> rulesList = mc.getRulesList();
		rulesList.add(testDefaultRuleLongMethod);
		mc.updateRulesList(rulesList);
		controller = new EditRuleController(testDefaultRuleLongMethod);

		assertThrows(Exception.class, () -> controller.deleteRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertTrue(updatedRulesList.contains(testDefaultRuleLongMethod));
	}

	@Test
	void testDeleteNoExistentRule() {
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController(testRule);

		assertThrows(Exception.class, () -> controller.deleteRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(testRule));
	}

	@Test
	void testGetJavascriptIfStatementString() {
		String ruleConditions = "LOC > 10 AND LAA == 20 OR LAA != 5";
		controller = new EditRuleController();
		String testJS = controller.getJavascriptIfStatementString(ruleConditions);
		assertEquals("LOC > 10 && LAA == 20 || LAA != 5", testJS);
	}

}
