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
	private ArrayList<ExcelRow> excelRows;
	
	/**
	 * Creates a IndicatorQuality object based on excelRow.
	 * A IndicatorQuality object also calculate the Quality Indicators from the Excel file
	 * @param excelRows is the list of Excel Rows from the Excel file
	 */
	public QualityIndicator(ArrayList<ExcelRow> excelRows) {
		PMDDCI = 0; 
		PMDDII = 0; 
		PMDADCI = 0; 
		PMDADII = 0;
		iPlasmaDCI = 0;
		iPlasmaDII = 0;
		iPlasmaADCI = 0;
		iPlasmaADII = 0;
		this.excelRows = excelRows;
		
		for (ExcelRow row : excelRows) {
			if(row.getPMDResult() && row.isLongMethod())
				PMDDCI++;
			if(row.getIPlasmaResult() && row.isLongMethod())
				iPlasmaDCI++;
			if(row.getPMDResult() && !row.isLongMethod())
				PMDDII++;
			if(row.getIPlasmaResult() && !row.isLongMethod())
				iPlasmaDII++;
			if(!row.getPMDResult() && !row.isLongMethod())
				PMDADCI++;
			if(!row.getIPlasmaResult() && !row.isLongMethod())
				iPlasmaADCI++;
			if(!row.getPMDResult() && row.isLongMethod())
				PMDADII++;
			if(!row.getIPlasmaResult() && row.isLongMethod())
				iPlasmaADII++;
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
}
