package main.java.model;

/**
 * Simple enumerator for comparators. Available comparators are GT (Greater
 * Than), LT (Lesser Than), EQ (Equal) and DF (Different).
 * 
 * @author hugo.barroca
 *
 */
public enum Comparator {
	GT(">"), LT("<"), EQ("=="), DF("!=");

	private String symbol;

	private Comparator(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}