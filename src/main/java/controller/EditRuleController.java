package main.java.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;
import main.java.model.Metric;

public class EditRuleController {

	/** GUI for editing and creating rules */
	private EditRulePopup editRulePopup;
	/** The rule being edited or created */
	private CodeQualityRule rule;
	/** MainController singleton */
	private MainController mainC = MainController.getMainControllerInstance();

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

	/**
	 * Add action listeners to Save and Delete buttons from Edit Rule Popup
	 */
	private void initActionListeners() {
		editRulePopup.getSaveButton().addActionListener(e -> onSaveRule());
		editRulePopup.getDeleteButton().addActionListener(e -> onDeleteRule());
	}

	/**
	 * Sets what the Edit Rule Popup delete button's action is. In this case, only
	 * deletes rule if it isn't a default rule and if it is present in the rules
	 * list.
	 */
	private void onDeleteRule() {
		if (rule.isDefault()) {
			editRulePopup.showMessage("You can't delete a default rule!");
		} else {
			ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();

			if (rulesList.contains(rule)) {
				rulesList.remove(rule);
				mainC.updateRulesList(rulesList);
				editRulePopup.showMessage("Rule has been deleted successfully!");
				editRulePopup.getFrame().dispose();
			} else {
				editRulePopup.showMessage("This rule is not in the rules list,\nso it cannot be deleted.");
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

		String newRule = getJavascriptIfStatementString(rawRuleConditions);

		try {
			// Runs pre validation to try to catch some errors
			preValidateJavascriptCode(newRule);
		} catch (ScriptException e) {
			editRulePopup.showMessage("The rule provided has an invalid format. Cannot save it.");
			return;
		}

		if (rule.isDefault() && !isValidDefaultRuleThresholdsUpdate(rule.getName(), newRule)) {
			editRulePopup.showMessage(
					"This is a Default Rule. As such, only the thresholds \n can be edited. And values must be positive.");
			return;
		}

		// updates the list with the new (validated) rule
		// updates the list for the main controller
		if (!rule.isDefault()) {
			rule.setName(newName);
		}
		rule.setRule(newRule);
		rule.setIsAdvanced(true);
		// Gets the rules list from the main controller
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();
		if (!rulesList.contains(rule)) {
			rulesList.add(rule);
		}
		mainC.updateRulesList(rulesList);
		mainC.getMainFrame().updateRulesComboBox(MainController.getMainControllerInstance().getRulesList());
		editRulePopup.showMessage("Rule has been added successfully!");
		editRulePopup.getFrame().dispose();
	}

	/**
	 * Parses the rule string from the Edit Rule Popup to use keywords valid for
	 * Javascript code.
	 * 
	 * @return Returns a javascript-ready string for evaluation.
	 */
	public String getJavascriptIfStatementString(String rawRuleConditions) {
		return rawRuleConditions.replaceAll("IF", "").replaceAll("AND", "&&").replaceAll("OR", "||").trim();
	}

	/**
	 * This method will pre validate the Javascript format rule to find any initial
	 * issues with the statement format. Will add mock data for metrics and evaluate
	 * the string. Throws an exception if the string if founds to be invalid. Runs
	 * normally when no initial issues are found. This is just a prevalidation, and
	 * doesn't eliminate the necessity of using a try/catch when running the rule
	 * against the real data.
	 * 
	 * @param rule An if statement rule in Javascript format to be validated
	 * @throws ScriptException When evaluation of the rule finds an issue, it throws
	 *                         an exception
	 */
	private void preValidateJavascriptCode(String rule) throws ScriptException {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("ECMAScript");
		String jsString = "\"use strict\"; (function() {";
		for (Metric metric : Metric.values()) {
			jsString += "var " + metric + " = 0; ";
		}
		jsString += " return eval('" + rule + "');})()";
		engine.eval(jsString);
	}

	/**
	 * This method validates if the default rules have their format respected and
	 * that only the threshold values have been edited. It uses a regex tailored for
	 * each custom rule to validate the edited rule. In case the rule name provided
	 * isn't of a known default rule, returns false.
	 *
	 * @param ruleName The name of the rule to be validated
	 * @param rule The rule string to be validated
	 * @return boolean If the rule has a valid format
	 */
	private boolean isValidDefaultRuleThresholdsUpdate(String ruleName, String rule) {
		switch (ruleName) {
		case "custom_is_long_method":
			return Pattern.matches("^LOC\\s*>\\s*\\d+\\s*&&\\s*CYCLO\\s*>\\s*\\d+$", rule);
		case "custom_is_feature_envy":
			return Pattern.matches("^ATFD\\s*>\\s*\\d+\\s*&&\\s*LAA\\s*<\\s*((0(.[\\d]+)?)|1)$", rule);
		default:
			return false;
		}
	}
	
	/**
	 * Getter for editRulePopup instance
	 * @return EditRulePopup controlled by this controller instance
	 */
	public EditRulePopup getEditRulePopup() {
		return editRulePopup;
	}

}
