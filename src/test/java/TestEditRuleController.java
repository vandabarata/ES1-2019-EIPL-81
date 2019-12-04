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
	void testDeleteRule() throws Exception {
		MainController mc = MainController.getMainControllerInstance();
		ArrayList<CodeQualityRule> rulesList = mc.getRulesList();
		rulesList.add(testRule);
		mc.updateRulesList(rulesList);
		controller = new EditRuleController(testRule);

		controller.deleteRule();

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
	void testAddNewRule() throws Exception {
		String newName = "New Rule";
		String newRuleConditions = "LOC > 10";
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController();

		EditRulePopup popup = controller.getEditRulePopup();
		popup.getNameText().setText(newName);
		popup.getAdvancedModeButton().doClick();
		popup.getRuleTextArea().setText(newRuleConditions);

		controller.saveRule();

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertTrue(updatedRulesList.contains(controller.getRule()));
	}
	
	@Test
	void testEditAndSaveRule() throws Exception {
		String newRuleConditions = "LOC > 10";
		MainController mc = MainController.getMainControllerInstance();
		ArrayList<CodeQualityRule> rulesList = mc.getRulesList();
		rulesList.add(testRule);
		mc.updateRulesList(rulesList);
		controller = new EditRuleController(testRule);

		EditRulePopup popup = controller.getEditRulePopup();
		popup.getAdvancedModeButton().doClick();
		popup.getRuleTextArea().setText(newRuleConditions);
		
		controller.saveRule();

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		int index = updatedRulesList.indexOf(testRule);
		assertTrue(updatedRulesList.get(index).getRule().equals(newRuleConditions));
	}

	@Test
	void testAddNewRuleWithoutName() throws Exception {
		String newRuleConditions = "LOC > 10";
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController();

		EditRulePopup popup = controller.getEditRulePopup();
		popup.getAdvancedModeButton().doClick();
		popup.getRuleTextArea().setText(newRuleConditions);

		assertThrows(Exception.class, () -> controller.saveRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(controller.getRule()));
	}

	@Test
	void testAddNewRuleWithoutRuleCondition() {
		String newName = "New Rule";
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController();

		EditRulePopup popup = controller.getEditRulePopup();
		popup.getNameText().setText(newName);

		assertThrows(Exception.class, () -> controller.saveRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(controller.getRule()));
	}

	@Test
	void testAddNewRuleWithInvalidFormat() {
		String newName = "New Rule";
		String newRuleConditions = "INVALID_STRING";
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController();

		EditRulePopup popup = controller.getEditRulePopup();
		popup.getNameText().setText(newName);
		popup.getAdvancedModeButton().doClick();
		popup.getRuleTextArea().setText(newRuleConditions);

		assertThrows(Exception.class, () -> controller.saveRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(controller.getRule()));
	}

	@Test
	void testSaveDefaultRule() throws Exception {
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController(testDefaultRuleFeatureEnvy);
		String editedRuleCondition = "ATFD > 20 && LAA < 0.69";
		EditRulePopup popup = controller.getEditRulePopup();
		popup.getRuleTextArea().setText(editedRuleCondition);

		controller.saveRule();

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		int index = updatedRulesList.indexOf(testDefaultRuleFeatureEnvy);
		assertTrue(updatedRulesList.get(index).getRule().equals(editedRuleCondition));
	}

	@Test
	void testSaveDefaultRuleWithInvalidFormat() {
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController(testDefaultRuleLongMethod);
		String editedRuleCondition = "LAA < 80 && ATFD > 10";
		EditRulePopup popup = controller.getEditRulePopup();
		popup.getRuleTextArea().setText(editedRuleCondition);

		assertThrows(Exception.class, () -> controller.saveRule());
	}

	@Test
	void testValidateNonMappedDefaultRule() {
		String ruleName = "defaultRuleNameThatDoesntExist";
		String ruleCondition = "ATFD > 20";
		CodeQualityRule newDefaultRule = new CodeQualityRule(ruleName, ruleCondition, true, true);
		controller = new EditRuleController(newDefaultRule);

		assertThrows(Exception.class, () -> controller.saveRule());
	}

	@Test
	void testGetJavascriptIfStatementString() {
		String ruleConditions = "LOC > 10 AND LAA == 20 OR LAA != 5";
		controller = new EditRuleController();
		String testJS = controller.getJavascriptIfStatementString(ruleConditions);
		assertEquals("LOC > 10 && LAA == 20 || LAA != 5", testJS);
	}

}
