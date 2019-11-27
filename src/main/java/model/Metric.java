package main.java.model;

/**
 * Metric enumerator, for convenience. The current existing metrics are LOC
 * (Lines of Code), CYCLO (McCabe's Cyclomatic Number), ATFD (Access To Foreign
 * Data) and LAA (Locality of Attribute Accesses).
 *
 * @author Hugo Barroca
 */
public enum Metric {
	LOC, CYCLO, ATFD, LAA;
}
