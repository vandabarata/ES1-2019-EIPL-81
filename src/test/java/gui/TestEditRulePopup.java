package test.java.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.gui.EditRulePopup;

class TestEditRulePopup {
	@Test
	void test() {
		EditRulePopup m = new EditRulePopup();
		assert(m.getFrame().getBounds().height == 300);
		assert(m.getFrame().getBounds().width == 685);
	}

}
