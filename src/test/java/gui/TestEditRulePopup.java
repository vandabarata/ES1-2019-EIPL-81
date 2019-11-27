package test.java.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.gui.EditRulePopup;

/**
 * This test is validating the window size, to ensure the popup is being
 * displayed properly.
 */
class TestEditRulePopup {
	@Test
	void testWindowSizeAtStart() {
		EditRulePopup m = new EditRulePopup(null);
		assert (m.getFrame().getBounds().height == 300);
		assert (m.getFrame().getBounds().width == 685);
	}

	// TODO: Test is not yet implemented. It should validate whether or not the JComboBoxs have the correct number of selectable elements.
	void testJComboBoxesElementNumber() {
		fail("Not implemented.");
	}
}
