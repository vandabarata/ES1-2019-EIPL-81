package test.java;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.EditRuleController;
import main.java.controller.MainController;
import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

/**
 * Tests the TestEditRuleController controller
 */
@RunWith(JUnitPlatform.class)
class TestEditRuleController {

	/** A rule to be used for tests */
	CodeQualityRule testRule;
	/** A default rule for long methods test */
	CodeQualityRule testDefaultRuleLongMethod;
	/** A default rule for feature envy test */
	CodeQualityRule testDefaultRuleFeatureEnvy;
	/** A EditRuleController to be used for testing */
	EditRuleController controller;

	/**
	 * Set up default values for testing
	 */
	@BeforeEach
	void setUp() {
		testRule = new CodeQualityRule("test", "LOC > 10", false, false);
		testDefaultRuleLongMethod = new CodeQualityRule("custom_is_long_method", "LOC > 80 && CYCLO > 10", true, true);
		testDefaultRuleFeatureEnvy = new CodeQualityRule("custom_is_feature_envy", "ATFD > 4 && LAA < 0.42", true,
				true);

	}

	/**
	 * Tests that can instantiate an EditRuleController with a rule to be edited
	 */
	@Test
	void testEditRuleControllerWithRule() {
		controller = new EditRuleController(testRule);
	}

	/**
	 * Tests that can instantiate an EditRuleController with no rule
	 */
	@Test
	void testEditRuleControllerNoRule() {
		controller = new EditRuleController();
	}

	/**
	 * Tests that can delete a regular rule from the rulesList.
	 * 
	 * @throws Exception If fails to delete the rule, throws an exception
	 */
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

	/**
	 * Tests that cannot delete a default rule.
	 */
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

	/**
	 * Tests that cannot delete a rule that is not in the rulesList
	 */
	@Test
	void testDeleteNoExistentRule() {
		MainController mc = MainController.getMainControllerInstance();
		controller = new EditRuleController(testRule);

		assertThrows(Exception.class, () -> controller.deleteRule());

		ArrayList<CodeQualityRule> updatedRulesList = mc.getRulesList();
		assertFalse(updatedRulesList.contains(testRule));
	}

	/**
	 * Tests that can add a new rule to the rules list.
	 * 
	 * @throws Exception If fails to save the rule
	 */
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

	/**
	 * Tests that can edit a rule condition and save it.
	 * 
	 * @throws Exception If fails to save the rule
	 */
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

	/**
	 * Tests that cannot save a rule without a name
	 */
	@Test
	void testAddNewRuleWithoutName() {
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

	/**
	 * Tests that cannot save a rule without rule conditions.
	 */
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

	/**
	 * Tests that cannot save a rule with an invalid string format
	 */
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

	/**
	 * Tests that can edit a default rule's condition and save the changes.
	 * 
	 * @throws Exception If fails to save the rule
	 */
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

	/**
	 * Tests that will not save a default rule if edited anything other than the
	 * thresholds
	 */
	@Test
	void testSaveDefaultRuleWithInvalidFormat() {
		controller = new EditRuleController(testDefaultRuleLongMethod);
		String editedRuleCondition = "LAA < 80 && ATFD > 10";
		EditRulePopup popup = controller.getEditRulePopup();
		popup.getRuleTextArea().setText(editedRuleCondition);

		assertThrows(Exception.class, () -> controller.saveRule());
	}

	/**
	 * Tests that won't validate and save a default rule that doesn't have a
	 * validation mapped to it.
	 */
	@Test
	void testValidateNonMappedDefaultRule() {
		String ruleName = "defaultRuleNameThatDoesntExist";
		String ruleCondition = "ATFD > 20";
		CodeQualityRule newDefaultRule = new CodeQualityRule(ruleName, ruleCondition, true, true);
		controller = new EditRuleController(newDefaultRule);

		assertThrows(Exception.class, () -> controller.saveRule());
	}

	/**
	 * Tests that will convert a string with pseudo code to valid Javascript format.
	 */
	@Test
	void testGetJavascriptIfStatementString() {
		String ruleConditions = "IF LOC > 10 AND LAA == 20 OR LAA != 5";
		controller = new EditRuleController();
		String testJS = controller.getJavascriptIfStatementString(ruleConditions);
		assertEquals("LOC > 10 && LAA == 20 || LAA != 5", testJS);
	}

}
