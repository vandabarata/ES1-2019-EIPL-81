package main.java.model;

public enum Operators {
	GT(">"),
	LT("<"),
	EQ("=="),
	DF("!=");
	
	private String symbol;
	
	private Operators(String symbol) {
		this.symbol = symbol;
	}
}
