package main.java.model;

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

	/**
	 * Creates a CodeQualityRule object
	 * 
	 * @param name
	 * @param isDefault
	 * @param rule
	 */
	public CodeQualityRule(String name, String rule, boolean isDefault, boolean isAdvanced) {
		this.name = name;
		this.rule = rule;
		this.isDefault = isDefault;
		this.isAdvanced = isAdvanced;
	}
	
	/**
	 * @param String - new rule's name
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
	 * @param String - new rule's text
	 */
	public void setRule(String rule) {
		if(!isDefault)
			this.rule = rule;
	}
	
	/**
	 * @return String with rule's text
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

}
