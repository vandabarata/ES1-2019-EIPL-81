package main.java.controller;

import java.util.ArrayList;

import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

public class EditRuleController {

	private EditRulePopup editRulePopup;
	private CodeQualityRule rule;

	/**
	 * @param r A CodeQualityRule to be edited in the Gui
	 */
	public EditRuleController(CodeQualityRule r) {
		rule = r;
		editRulePopup = new EditRulePopup(rule);
		initActionListeners();
	}
	
	/**
	 * If no rule has been selected, creates a new empty rule, basic by default
	 */
	public EditRuleController() {
		this(new CodeQualityRule("", "", false, false));
	}

	private void initActionListeners() {
		editRulePopup.getSaveButton().addActionListener(e -> onSaveRule());
	}

	private void onSaveRule() {
		String newName = editRulePopup.getRuleName();

		// Verify if name is valid
		if (newName.isEmpty()) {
			editRulePopup.showMessage("Please insert a rule name!");
			return;
		}
		
		String rawRuleConditions = editRulePopup.getRawRuleConditions().trim();
		
		// Check if there is a rule to save
		if (rawRuleConditions.isEmpty()) {
			editRulePopup.showMessage("You need to set rule conditions for " + newName);
			return;
		}

		String newRule = getJavascriptString(rawRuleConditions);
		
		editRulePopup.showMessage("Rule has been added successfuly!");
	}

	/**
	 * 
	 * @return Returns a javascript-ready string for evaluation.
	 */
	public String getJavascriptString(String rawRuleConditions) {
		String javascriptString = rawRuleConditions.replaceAll("IF", "").replaceAll("AND", "&&").replaceAll("OR", "||");
		return javascriptString;
	}
	
	

}
