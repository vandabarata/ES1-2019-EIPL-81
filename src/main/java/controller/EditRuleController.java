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
		editRulePopup.getSaveButton().addActionListener(e -> onSaveHandler());
		editRulePopup.getDeleteButton().addActionListener(e -> onDeleteHandler());
	}

	/**
	 * Sets a handler for the Edit Rule Popup delete button's action. Triggers a
	 * deleteRule method.
	 */
	public void onDeleteHandler() {
		try {
			deleteRule();
			editRulePopup.showMessage("Rule has been deleted successfully!");
			editRulePopup.getFrame().dispose();
		} catch (Exception e) {
			editRulePopup.showMessage(e.getMessage());
		}
	}

	/**
	 * Tries to delete a rule from the rulesList. Only deletes a rule if it isn't a
	 * default rule and if it is present in the rules list. Throws an exception
	 * otherwise.
	 * 
	 * @throws Exception Exception with the error message of the issue found.
	 */
	public void deleteRule() throws Exception {
		if (rule.isDefault()) {
			throw new Exception("You can't delete a default rule.");
		}
		ArrayList<CodeQualityRule> rulesList = mainC.getRulesList();

		if (rulesList.contains(rule)) {
			rulesList.remove(rule);
			mainC.updateRulesList(rulesList);
		} else {
			throw new Exception("This rule is not in the rules list,\nso it cannot be deleted.");
		}
	}

	/**
	 * Sets a handler for the Edit Rule Popup save button's action. Triggers a
	 * saveRule method.
	 */
	private void onSaveHandler() {
		try {
			saveRule();
			editRulePopup.showMessage("Rule has been added successfully!");
			editRulePopup.getFrame().dispose();
		} catch (Exception e) {
			editRulePopup.showMessage(e.getMessage());
		}
	}

	/**
	 * Tries to save a rule. It checks for the rule's content and name. It won't
	 * save a new rule without a name or set conditions. It also validates the
	 * conditions' validity before saving. Throws an exception otherwise.
	 * 
	 * @throws Exception
	 */
	public void saveRule() throws Exception {
		String newName = editRulePopup.getNameText().getText();

		// Verify if name is valid
		if (newName.isEmpty()) {
			throw new Exception("Please provide a rule name.");
		}

		String rawRuleConditions = editRulePopup.getRawRuleConditions().trim();

		// Check if there is a rule to save
		if (rawRuleConditions.isEmpty()) {
			throw new Exception("You need to set rule conditions for " + newName);
		}

		String newRule = getJavascriptIfStatementString(rawRuleConditions);

		try {
			// Runs pre validation to try to catch some errors
			preValidateJavascriptCode(newRule);
		} catch (ScriptException e) {
			throw new Exception("The rule provided has an invalid format. Cannot save it.");
		}

		if (rule.isDefault() && !isValidDefaultRuleThresholdsUpdate(rule.getName(), newRule)) {
			throw new Exception(
					"This is a Default Rule. As such, only the thresholds \n can be edited. And values must be positive.");
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
	 * @param rule     The rule string to be validated
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
	 * 
	 * @return EditRulePopup controlled by this controller instance
	 */
	public EditRulePopup getEditRulePopup() {
		return editRulePopup;
	}

	/**
	 * Getter for the rule being edit or added
	 * 
	 * @return CodeQualityRule
	 */
	public CodeQualityRule getRule() {
		return rule;
	}

}
