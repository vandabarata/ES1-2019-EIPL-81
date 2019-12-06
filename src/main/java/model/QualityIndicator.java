package main.java.model;

import java.util.ArrayList;

/**
 * A class that represents the indicators quality of the tools for comparison with iPlasma and PDM
 */

public class QualityIndicator {

	/** PMDDCI - DII Indicator - number of faults correctly identified by the PMD tool */
	private int PMDDCI;
	/** PMDDII - DII Indicator - number of faults incorrectly identified by the PMD tool */
	private int PMDDII;
	/** PMDADCI ADCI Indicator - number of absence of faults correctly identified by the PMD tool */
	private int PMDADCI;
	/**  PMDADII - ADII Indicator - number of absence of faults incorrectly identified by the PMD tool */
	private int PMDADII;
	/** iPlasmaDCI - DCI Indicator - number of faults correctly identified by the iPlasma tool */
	private int iPlasmaDCI;
	/** iPlasmaDII - DII Indicator - number of faults incorrectly identified by the iPlasma tool */
	private int iPlasmaDII;
	/** iPlasmaADCI ADCI Indicator - number of absence of faults correctly identified by the iPlasma tool */
	private int iPlasmaADCI;
	/** iPlasmaADII - ADII Indicator - number of absence of faults incorrectly identified by the iPlasma tool */
	private int iPlasmaADII;
	/** excel Rows  - list of Excel Rows from Excel file */
	private String[][] resultsRows;
	private int customLongDCI;
	private int customLongDII;
	private int customLongADCI;
	private int customLongADII;
	private int customEnvyDCI;
	private int customEnvyDII;
	private int customEnvyADCI;
	private int customEnvyADII;
	
	/**
	 * Creates a IndicatorQuality object based on excelRow.
	 * A IndicatorQuality object also calculate the Quality Indicators from the Excel file
	 * @param excelRows is the list of Excel Rows from the Excel file
	 */
	public QualityIndicator(String[][] resultsRows) {
		PMDDCI = 0; 
		PMDDII = 0; 
		PMDADCI = 0; 
		PMDADII = 0;
		iPlasmaDCI = 0;
		iPlasmaDII = 0;
		iPlasmaADCI = 0;
		iPlasmaADII = 0;
		customLongDCI = 0;
		customLongDII = 0;
		customLongADCI = 0;
		customLongADII = 0;
		customEnvyDCI = 0;
		customEnvyDII = 0;
		customEnvyADCI = 0;
		customEnvyADII = 0;
		this.resultsRows = resultsRows;
		
		for (String[] row : resultsRows) {
			if(Boolean.parseBoolean(row[3]) && Boolean.parseBoolean(row[1]))
				PMDDCI++;
			if(Boolean.parseBoolean(row[4]) && Boolean.parseBoolean(row[1]))
				iPlasmaDCI++;
			if(Boolean.parseBoolean(row[5]) && Boolean.parseBoolean(row[1]))
				customLongDCI++;
			if(Boolean.parseBoolean(row[6]) && Boolean.parseBoolean(row[1]))
				customEnvyDCI++;
			if(Boolean.parseBoolean(row[3]) && !Boolean.parseBoolean(row[1]))
				PMDDII++;
			if(Boolean.parseBoolean(row[4]) && !Boolean.parseBoolean(row[1]))
				iPlasmaDII++;
			if(Boolean.parseBoolean(row[5]) && !Boolean.parseBoolean(row[1]))
				customLongDII++;
			if(Boolean.parseBoolean(row[6]) && !Boolean.parseBoolean(row[1]))
				customEnvyDII++;
			if(!Boolean.parseBoolean(row[3]) && !Boolean.parseBoolean(row[1]))
				PMDADCI++;
			if(!Boolean.parseBoolean(row[4]) && !Boolean.parseBoolean(row[1]))
				iPlasmaADCI++;
			if(!Boolean.parseBoolean(row[5]) && !Boolean.parseBoolean(row[1]))
				customLongADCI++;
			if(!Boolean.parseBoolean(row[6]) && !Boolean.parseBoolean(row[1]))
				customEnvyADCI++;
			if(!Boolean.parseBoolean(row[3]) && Boolean.parseBoolean(row[1]))
				PMDADII++;
			if(!Boolean.parseBoolean(row[4]) && Boolean.parseBoolean(row[1]))
				iPlasmaADII++;
			if(!Boolean.parseBoolean(row[5]) && Boolean.parseBoolean(row[1]))
				customLongADII++;
			if(!Boolean.parseBoolean(row[6]) && Boolean.parseBoolean(row[1]))
				customEnvyADII++;
		}
	}
	
	/**
	 * @return number of "Defeitos Corretamente Identificados" (DCI) by the PMD tool
	 */
	public int getPMDDCI() {
		return PMDDCI;
	}
	
	
	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the PMD tool
	 */
	public int getPMDDII() {
		return PMDDII;
	}
	
	
	/**
	 * @return number of "Ausencia de Defeitos Corretamente Identificados" (ADCI) by the PMD tool
	 */
	public int getPMDADCI() {
		return PMDADCI;
	}
	
	/**
	 * @return number of "Ausencia de Defeitos Incorretamente Identificados" (ADII) by the PMD tool

	 */
	public int getPMDADII() {
		return PMDADII;
	}
	
	/**
	 * @return number of "Defeitos Corretamente Identificados" (DCI) by the iPlasma tool
	 */
	public int getIPlasmaDCI() {
		return iPlasmaDCI;
	}
	
	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the iPlasma tool
	 */
	public int getIPlasmaDII() {
		return iPlasmaDII;
	}
	
	/**
	 * @return number of "Ausencia de Defeitos Corretamente Identificados" (ADCI) by the iPlasma tool
	 */
	public int getIPlasmaADCI() {
		return iPlasmaADCI;
	}
	
	/**
	 * @return  number of "Ausencia de Defeitos Incorretamente Identificados" (ADII) by the iPlasma tool
	 */
	public int getIPlasmaADII() {
		return iPlasmaADII;
	}

	public int getCustomLongDCI() {
		return customLongDCI;
	}

	public int getCustomLongADII() {
		return customLongADII;
	}

	public int getCustomLongDII() {
		return customLongDII;
	}

	public int getCustomLongADCI() {
		return customLongADCI;
	}

	public int getCustomEnvyDCI() {
		return customEnvyDCI;
	}

	public int getCustomEnvyADCI() {
		return customEnvyADCI;
	}

	public int getCustomEnvyDII() {
		return customEnvyDII;
	}

	public int getCustomEnvyADII() {
		return customEnvyADII;
	}
}
