package main.java.model;

import java.util.ArrayList;

/**
 * A class that represents the indicators quality of the tools in comparison with iPlasma and PDM
 */

public class QualityIndicator {

	private int PMDDCI, PMDDII, PMDADCI, PMDADII, iPlasmaDCI, iPlasmaDII, iPlasmaADCI, iPlasmaADII;
	private ArrayList<ExcelRow> excelRows;
	
	/**
	 * Creates a IndicatorQuality object based on:
	 *
	 * @param PMDDCI - DCI Indicator - number of faults correctly identified by the PMD tool
	 * @param PMDDII - DII Indicator - number of faults incorrectly identified by the PMD tool
	 * @param PMDADCI ADCI Indicator - number of absence of faults correctly identified by the PMD tool
	 * @param PMDADII - ADII Indicator - number of absence of faults incorrectly identified by the PMD tool
	 * @param iPlasmaDCI - DCI Indicator - number of faults correctly identified by the iPlasma tool
	 * @param iPlasmaDII - DII Indicator - number of faults incorrectly identified by the iPlasma tool
	 * @param iPlasmaADCI ADCI Indicator - number of absence of faults correctly identified by the iPlasma tool
	 * @param iPlasmaADII - ADII Indicator - number of absence of faults incorrectly identified by the iPlasma tool
	 * 
	 * A IndicatorQuality object also calculate the Quality Indicators from the Excel file
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
			if(row.isPMD() && row.isIs_long_method())
				PMDDCI++;
			if(row.isiPlasma() && row.isIs_long_method())
				iPlasmaDCI++;
			if(row.isPMD() && !row.isIs_long_method())
				PMDDII++;
			if(row.isiPlasma() && !row.isIs_long_method())
				iPlasmaADII++;
			if(!row.isPMD() && !row.isIs_long_method())
				PMDADCI++;
			if(!row.isiPlasma() && !row.isIs_long_method())
				iPlasmaADCI++;
			if(!row.isPMD() && row.isIs_long_method())
				PMDADII++;
			if(!row.isiPlasma() && row.isIs_long_method())
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
	 * @return number of "Ausência de Defeitos Incorretamente Identificados" (ADCI) by the PMD tool
	 */
	public int getPMDADCI() {
		return PMDADCI;
	}
	
	/**
	 * @return number of "AusÃªncia de Defeitos Incorretamente Identificados" (ADII) by the PMD tool
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
	 * @return number of "Ausência de Defeitos Corretamente Identificados" (ADCI) by the iPlasma tool
	 */
	public int getIPlasmaADCI() {
		return iPlasmaADCI;
	}
	
	/**
	 * @return  number of "Ausência de Defeitos Incorretamente Identificados" (ADII) by the iPlasma tool
	 */
	public int getIPlasmaADII() {
		return iPlasmaADII;
	}
}
