package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.controller.MainController;
import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

/**
 * This test is validating the window size, to ensure the popup is being
 * displayed properly.
 */
@RunWith(JUnitPlatform.class)
class TestEditRulePopup {
	@Test
	void testWindowSizeAtStart() {
		EditRulePopup m = new EditRulePopup(new CodeQualityRule("", "", false, false));
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
		int numberOfRules = main.getRulesList().size();
		assertEquals(main.getMainFrame().getRulesComboBox().getItemCount(), numberOfRules);
	}
}
