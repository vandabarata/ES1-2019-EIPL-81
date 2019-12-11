package main.java.model;

/**
 * Simple enum for comparison operators. Choices are are GT (Greater Than) (>),
 * LT (Lesser Than) (<), GE (Greater or Equal) (>=), LE (Less or Equal) (<=) EQ
 * (Equal) (==) and DF (Different) (!=).
 * 
 */
public enum ComparisonOperator {
	GT(">"), LT("<"), GE(">="), LE("<="), EQ("=="), DF("!=");

	/** String which holds the given operator */
	private String operator;

	/** Construtor that setups the string value of the ComparisonOperator enum */
	private ComparisonOperator(String operator) {
		this.operator = operator;
	}

	/** Returns the string value of a ComparisonOperator enum
	 * 
	 * @return String operator - the value of the enum
	 * */
	public String getOperator() {
		return operator;
	}
}