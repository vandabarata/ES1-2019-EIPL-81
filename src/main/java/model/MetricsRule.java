package main.java.model;

/**
 * A class that represents a rule that can be applied to
 * the excel metrics.
 * 
 * @author Lino Silva
 * @version 1.0
 */
public class MetricsRule {

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
	public MetricsRule(String name, boolean isDefault, String rule) {
		this.name = name;
		this.isDefault = isDefault;
		this.rule = rule;
	}

	/**
	 * @return MetricRule name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return MetricRule rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @return MetricRule isDefault
	 */
	public boolean isDefault() {
		return isDefault;
	}

}
