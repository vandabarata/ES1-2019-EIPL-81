package test.java;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.gui.EditRulePopup;
import main.java.gui.MainFrame;
import main.java.model.CodeQualityRule;

@RunWith(JUnitPlatform.class)
class TestEditRulePopup {
	
	EditRulePopup editpopup;
	EditRulePopup addpopup;

	@BeforeEach
	void setUp() throws Exception {
		editpopup = new EditRulePopup(new CodeQualityRule("name", "LOC > 10", true, false));
		addpopup = new EditRulePopup(new CodeQualityRule("", "LAA < 5", false, false));
	}

	@Test
	void testGetCondition() {
		addpopup.getCondition();
	}

	@Test
	void testGetValue() {
		addpopup.getValue();
	}

	@Test
	void testGetComparison() {
		addpopup.getComparison();
	}

	@Test
	void testGetSaveButton() {
		addpopup.getSaveButton();
	}

	@Test
	void testGetDeleteButton() {
		addpopup.getDeleteButton();
	}

	@Test
	void testGetRuleName() {
		String ruleName = editpopup.getRuleName();
		assertEquals("name", ruleName);
	}

	@Test
	void testIsAdvancedMode() {
		boolean newRule = addpopup.isAdvancedMode();
		assertFalse(newRule);
	}

	@Test
	void testGetRawRuleConditions() {
		editpopup.getRawRuleConditions();
		addpopup.getRawRuleConditions();
	}
	
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
		String[] header = {"header1", "header2"};
		String[][] content = {{"cell0",  "cell1"},{"cell2", "cell3"}};
		MainFrame frame = new MainFrame(new JTable(content, header), main.getRulesList());
		int numberOfRules = main.getRulesList().size();
		assertEquals(frame.getRulesComboBox().getItemCount(), numberOfRules);
	}
}
