package main.java.controller;

import java.util.ArrayList;

import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;

public class EditRuleController {

	private EditRulePopup editRulePopup;
	private CodeQualityRule rule;
	MainController mainC = MainController.getMainControllerInstance();

	/**
	 * @param rule The Controller receives a CodeQualityRule to be edited in the GUI
	 */
	public EditRuleController(CodeQualityRule rule) {
		this.rule = rule;
		editRulePopup = new EditRulePopup(this.rule);
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
		editRulePopup.getDeleteButton().addActionListener(e -> onDeleteRule());
	}

	/**
	 * Sets what the Edit Rule Popup delete button's action is In this case, only
	 * deletes rule if it isn't default
	 */
	private void onDeleteRule() {
		if (rule.isDefault()) {
			editRulePopup.showMessage("You can't delete a default rule!");
		} else {
			ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();

			if (rulesList.contains(rule)) {
				rulesList.remove(rule);
				mainC.updateRulesList(rulesList);
				mainC.getMainFrame().updateRulesComboBox(MainController.getMainControllerInstance().getRulesList());
				editRulePopup.showMessage("Rule has been deleted!");
				editRulePopup.getFrame().dispose();
			} else {
				editRulePopup.showMessage("There is no rule to delete");
			}

		}
	}

	/**
	 * Determines what the Edit Rule Popup save button's action is Before saving, it
	 * checks for the rule's content and name It won't save a new rule without a
	 * name or set conditions It also validates the conditions' validity before
	 * saving
	 */
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

		// gets the rules list from the main controller
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();

		if (!rulesList.contains(rule)) {
			rule = new CodeQualityRule(newName, newRule, false, editRulePopup.isAdvancedMode());
		} else {
			rulesList.remove(rule);

			if (rule.isDefault()) {
				rule = new CodeQualityRule(rule.getName(), newRule, true, rule.isAdvanced());
			} else {
				rule = new CodeQualityRule(newName, newRule, false, false);
			}

		}

		// updates the list with the new (validated) rule
		// updates the list for the main controller
		rulesList.add(rule);
		mainC.updateRulesList(rulesList);
		mainC.getMainFrame().updateRulesComboBox(MainController.getMainControllerInstance().getRulesList());
		editRulePopup.showMessage("Rule has been added successfully!");
		editRulePopup.getFrame().dispose();
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
