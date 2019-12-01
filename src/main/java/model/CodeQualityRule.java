package main.java.model;

import java.util.Date;

/**
 * A class that represents a rule based on 
 * predetermined or user-defined metrics
 * and thresholds to detect code defects
 * and thus determine code quality
 * 
 * @author Lino Silva
 */
public class CodeQualityRule {

	private String name;
	private String rule;
	private boolean isDefault;
	private boolean isAdvanced;
	private long id;

	/**
	 * Creates a CodeQualityRule object based on:
	 *
	 * @param name - rule's name, shown in UI
	 * @param rule - rule's content, ie, the conditions that define it
	 * @param isDefault - if a rule is default, only its thresholds can be edited
	 * @param isAdvanced - if a rule is advanced, it can never be opened in the basic UI
	 */
	public CodeQualityRule(String name, String rule, boolean isDefault, boolean isAdvanced) {
		this.name = name;
		this.rule = rule;
		this.isDefault = isDefault;
		this.isAdvanced = isAdvanced;
		id = new Date().getTime();
	}
	
	/**
	 * @param String - rule's new name
	 * Only changes the rule's name if it isn't a default rule
	 */
	public void setName(String name) {
		if(!isDefault)
			this.name = name;
	}
	
	/**
	 * @return String - rule's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param String - rule's new content
	 * Only allows to (freely) change a 
	 * rule's content if it's not a default rule
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	/**
	 * @return String with rule's content
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * Returns boolean that determines if a rule is default or not
	 * A default rule is a rule that can't have its metrics edited
	 * only its thresholds
	 * @return Boolean - isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}
	
	/**
	 * Sets the state of the rule, determining if it is an advanced
	 * rule or a basic one
	 * @param Boolean - isAdvanced
	 */
	public void setIsAdvanced(boolean isAdvanced) {
		if(!isDefault)
			this.isAdvanced = isAdvanced;
	}
	
	/**
	 * Returns boolean that determines if a rule is advanced or not
	 * A advanced rule is a rule that can't be edited with the basic
	 * rule editor
	 * @return Boolean - isAdvanced
	 */
	public boolean isAdvanced() {
		return isAdvanced;
	}
	
	/**
	* @return rule's name
	*/
	public String toString() {
		return this.name;
	}

	public boolean equals(CodeQualityRule other) {
		
		return this.id == other.id;
	}

}
