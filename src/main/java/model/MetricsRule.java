package main.java.model;

/**
 * A class that represents a rule that can be applied to
 * the excel metrics.
 * 
 * @author Lino Silva
 */
public class CodeQualityRule {

	private String name;
	private boolean isDefault;
	private String rule;

	/**
	 * Creates a MetricsRule object
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
	 * @return CodeQualityRule name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return CodeQualityRule rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	* Returns boolean that determines if a rule is default or not
	* A default rule is a rule that can't have its metrics edited
	* only its thresholds
	 * @return CodeQualityRule boolean isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}

}
