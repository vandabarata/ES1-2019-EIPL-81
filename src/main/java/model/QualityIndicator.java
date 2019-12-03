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
	 * @param PMDDCI - DCI Indicator - number of faults correctly identified of PDM tool
	 * @param PMDDII - DII Indicator - number of faults incorrectly indetified of PDM tool
	 * @param PMDADCI ADCI Indicator - number of absence of faults correctly indetified of PDM tool
	 * @param PMDADII - ADII Indicator - number of absence of faults incorrectly indetified of PDM tool
	 * @param iPlasmaDCI - DCI Indicator - number of faults correctly identified of iPlasma tool
	 * @param iPlasmaDII - DII Indicator - number of faults incorrectly indetified of iPlasma tool
	 * @param iPlasmaADCI ADCI Indicator - number of absence of faults correctly indetified of iPlasma tool
	 * @param iPlasmaADII - ADII Indicator - number of absence of faults incorrectly indetified of iPlasma tool
	 * 
	 * A IndicatorQuality object also compute the Quality Indicators 
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
	 * @return DCI Indicator of PDM tool
	 */
	public int getPMDDCI() {
		return PMDDCI;
	}
	
	/**
	 * @return DII Indicator of PDM tool
	 */
	public int getPMDDII() {
		return PMDDII;
	}
	
	/**
	 * @return ADCI Indicator of PDM tool
	 */
	public int getPMDADCI() {
		return PMDADCI;
	}
	
	/**
	 * @return ADII Indicator of PDM tool
	 */
	public int getPMDADII() {
		return PMDADII;
	}
	
	/**
	 * @return DCI Indicator of iPlasma tool
	 */
	public int getIPlasmaDCI() {
		return iPlasmaDCI;
	}
	
	/**
	 * @return DII Indicator of iPlasma tool
	 */
	public int getIPlasmaDII() {
		return iPlasmaDII;
	}
	
	/**
	 * @return ADCI Indicator of iPlasma tool
	 */
	public int getIPlasmaADCI() {
		return iPlasmaADCI;
	}
	
	/**
	 * @return ADII Indicator of iPlasma tool
	 */
	public int getIPlasmaADII() {
		return iPlasmaADII;
	}
}
