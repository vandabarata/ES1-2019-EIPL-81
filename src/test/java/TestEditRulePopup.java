package test.java;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.MainController;
import main.java.gui.EditRulePopup;
import main.java.gui.MainFrame;
import main.java.model.CodeQualityRule;

/**
 * Tests for the EditRulePopup
 *
 */
@RunWith(JUnitPlatform.class)
class TestEditRulePopup {

	/* An EditRulePopup that will be used to test the rule edition frame */
	EditRulePopup editpopup;

	/* An EditRulePopup that will be used to test the rule addition frame */
	EditRulePopup addpopup;

	@BeforeEach
	void setUp() throws Exception {
		editpopup = new EditRulePopup(new CodeQualityRule("name", "LOC > 10", true, false));
		addpopup = new EditRulePopup(new CodeQualityRule("", "LAA < 5", false, false));
	}

	/**
	 * Tests the condition returned by the EditRulePopup getter for the rule's
	 * condition
	 */
	@Test
	void testGetCondition() {
		assertNotNull(addpopup.getLogicalOperator());
		assertEquals(0, addpopup.getLogicalOperator().getItemCount());
	}

	/**
	 * Tests the metrics combobox returned by the EditRulePopup getter for the
	 * rule's metrics Asserts that it's null in advanced mode (editpopup) Asserts
	 * that it's not null in basic mode (addpopup) And finally asserts that it
	 * specifically returns 4 metrics in basic mode (addpopup)
	 */
	@Test
	void testGetValue() {
		assertNull(editpopup.getMetrics());
		assertNotNull(addpopup.getMetrics());
		assertEquals(4, addpopup.getMetrics().getItemCount());
	}

	/**
	 * Tests the logical operators' combobox returned by the EditRulePopup getter
	 * for rule comparison Asserts that it's not null and returns 6 possible
	 * operators in basic mode (addpopup)
	 */
	@Test
	void testGetComparison() {
		assertNotNull(addpopup.getComparison());
		assertEquals(6, addpopup.getComparison().getItemCount());
	}

	/**
	 * Tests the save button returned by the EditRulePopup getter for saving a rule
	 */
	@Test
	void testGetSaveButton() {
		assertNotNull(addpopup.getSaveButton());
	}

	/**
	 * Tests the delete button returned by the EditRulePopup getter for deleting a
	 * rule
	 */
	@Test
	void testGetDeleteButton() {
		assertNotNull(addpopup.getDeleteButton());

	}

	/**
	 * Tests if a new rule (for which advanced mode is false) returns the correct
	 * boolean
	 */
	@Test
	void testIsAdvancedMode() {
		boolean newRule = addpopup.isAdvancedMode();
		assertFalse(newRule);
	}

	/**
	 * Tests the rule conditions returned by the EditRulePopup getter for the rule's
	 * conditions Asserts that the conditions are the expected ones passed in the
	 * constructor
	 */
	@Test
	void testGetRawRuleConditions() {
		assertEquals("LOC > 10", editpopup.getRawRuleConditions());
		assertEquals("", addpopup.getRawRuleConditions());
	}

	/**
	 * Tests the window size of the EdiRulePopup frame by asserting its height and
	 * width with expected values
	 */
	@Test
	void testWindowSizeAtStart() {
		EditRulePopup m = new EditRulePopup(new CodeQualityRule("", "", true, false));
		assert (m.getFrame().getBounds().height == 300);
		assert (m.getFrame().getBounds().width == 685);
	}

	/**
	 * Asserts that the number of rules displayed on the combobox in the main frame
	 * is the same as the number of existing rules in the controller
	 */
	@Test
	void testJComboBoxesElementNumber() {
		MainController main = MainController.getMainControllerInstance();
		main.init();
		String[] header = { "header1", "header2" };
		String[][] content = { { "cell0", "cell1" }, { "cell2", "cell3" } };
		MainFrame frame = new MainFrame(new JTable(content, header), main.getRulesList());
		int numberOfRules = main.getRulesList().size();
		assertEquals(frame.getRulesComboBox().getItemCount(), numberOfRules);
	}

}
