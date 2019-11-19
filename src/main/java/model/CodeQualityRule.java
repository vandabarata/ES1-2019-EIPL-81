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
	private boolean isDefault;
	private String rule;

	/**
	 * Creates a CodeQualityRule object
	 * 
	 * @param name
	 * @param isDefault
	 * @param rule
	 */
	public CodeQualityRule(String name, boolean isDefault, String rule) {
		this.name = name;
		this.isDefault = isDefault;
		this.rule = rule;
	}
	
	/**
	 * @param String - new rule's name
	 */
	public void setName(String name) {
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

}
