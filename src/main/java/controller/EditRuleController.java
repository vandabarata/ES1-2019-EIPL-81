package main.java.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import main.java.gui.EditRulePopup;
import main.java.model.CodeQualityRule;
import main.java.model.Metric;

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

		String newRule = getJavascriptIfStatementString(rawRuleConditions);

		try {
			// Runs pre validation to try to catch some errors
			preValidateJavascriptCode(newRule);
		} catch (ScriptException e) {
			editRulePopup.showMessage("The rule provided has an invalid format. Cannot save it.");
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
		mainC.getMainFrame()
			.updateRulesComboBox(MainController.getMainControllerInstance().getRulesList());
		editRulePopup.showMessage("Rule has been added successfully!");
		editRulePopup.getFrame().dispose();
	}

	/**
	 * Parses the rule string from the Edit Rule Popup to use keywords valid for Javascript code.
	 * 
	 * @return Returns a javascript-ready string for evaluation.
	 */
	public String getJavascriptIfStatementString(String rawRuleConditions) {
		return rawRuleConditions.replaceAll("IF", "").replaceAll("AND", "&&").replaceAll("OR", "||").trim();
	}

	/**
	 * This method will pre validate the Javascript format rule to find any initial issues with the
	 * statement format. Will add mock data for metrics and evaluate the string. Throws an exception
	 * if the string if founds to be invalid. Runs normally when no initial issues are found. This is
	 * just a prevalidation, and doesn't eliminate the necessity of using a try/catch when running the rule
	 * against the real data.
	 * 
	 * @param rule An if statement rule in Javascript format to be validated
	 * @throws ScriptException When evaluation of the rule finds an issue, it throws an exception
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

}
