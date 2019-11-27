package main.java.model;

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