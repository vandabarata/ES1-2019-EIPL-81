package main.java.model;

import java.util.Date;

/**
 * A class that represents a rule based on predetermined or user-defined metrics
 * and thresholds to detect code defects and thus determine code quality
 * 
 */
public class CodeQualityRule {

	/** Rule's name */
	private String name;

	/** The rule's conditions */
	private String rule;

	/** If the rule is a default rule or new rule added by the user */
	private boolean isDefault;

	/** If the rule should be opened in advanced mode for edition */
	private boolean isAdvanced;

	/** timestamp which allows us to ID the rules */
	private long id;

	/**
	 * Creates a CodeQualityRule object based on:
	 *
	 * @param String  name - rule's name, shown in UI
	 * @param String  rule - rule's content, ie, the conditions that define it
	 * @param boolean isDefault - if a rule is default, only its thresholds can be
	 *                edited
	 * @param boolean isAdvanced - if a rule is advanced, it can never be opened in
	 *                the basic UI
	 */
	public CodeQualityRule(String name, String rule, boolean isDefault, boolean isAdvanced) {
		this.name = name;
		this.rule = rule;
		this.isDefault = isDefault;
		id = new Date().getTime();
		if (isDefault) {
			this.isAdvanced = true;
		} else {
			this.isAdvanced = isAdvanced;
		}
	}

	/**
	 * Changes the rule's name to a new one
	 * 
	 * @param String - rule's new name. Only changes if it isn't a default rule
	 */
	public void setName(String name) {
		if (!isDefault)
			this.name = name;
	}

	/**
	 * Returns the rule's name
	 * 
	 * @return String name - rule's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the rule's conditions
	 * 
	 * @param String - rule's new conditions.
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * Returns the rule's conditions
	 * 
	 * @return String rule - rule's conditions
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Returns boolean that determines if a rule is default or not A default rule is
	 * a rule that can't have its metrics edited only its thresholds
	 * 
	 * @return boolean isDefault - if a rule is default or not
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Sets the state of the rule, determining if it is an advanced rule or a basic
	 * one. If it's a default rule, considers it for edition only in advanced mode.
	 * 
	 * @param boolean isAdvanced - if a rule should be opened in advanced mode
	 */
	public void setIsAdvanced(boolean isAdvanced) {
		if (isDefault) {
			this.isAdvanced = true;
		} else {
			this.isAdvanced = isAdvanced;
		}
	}

	/**
	 * Returns boolean that determines if a rule is advanced or not. An advanced
	 * rule can't be edited with the basic rule editor
	 * 
	 * @return boolean isAdvanced - if a rule should be opened in advanced mode
	 */
	public boolean isAdvanced() {
		return isAdvanced;
	}

	/**
	 * Makes it so that a CodeQualityRule is displayed by its name
	 * 
	 * @return String name - rule's name
	 */
	public String toString() {
		return this.name;
	}

	/**
	 * Establishes a way of comparing rules based on their id
	 * 
	 * @param CodeQualityRule otherRule - receives another rule and compares it to
	 *                        this one based on their id
	 */
	public boolean equals(CodeQualityRule otherRule) {
		return this.id == otherRule.id;
	}

}
