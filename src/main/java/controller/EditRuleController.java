package main.java.controller;

import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

public class EditRuleController {

	private EditRulePopup editRulePopup;
	private CodeQualityRule rule;
	
	/**
	 * @param c A CodeQualityRule to be edited in the Gui
	 */
	public EditRuleController(CodeQualityRule r) {
		rule = r;
		editRulePopup = new EditRulePopup(rule);
	}
	
}
