package main.java.model;

/**
 * Simple enumerator for comparators. Available comparators are GT (Greater
 * Than), LT (Lesser Than), GE (Greater or Equal Than), LE (Less or Equal than)
 * EQ (Equal) and DF (Different).
 * 
 * @author hugo.barroca
 */
public enum Operator {
	GT(">"), LT("<"), GE(">="), LE("<="), EQ("=="), DF("!=");

	private String symbol;

	private Operator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}