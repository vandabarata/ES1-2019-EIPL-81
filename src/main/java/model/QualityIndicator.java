package main.java.model;

/**
 * A class that represents the indicators quality of the tools for comparison with iPlasma and PDM
 */

public class QualityIndicator {

	/** PMDDCI - DCI Indicator - number of faults correctly identified by the PMD tool */
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
	/** customLongDCI - DCI Indicator - number of faults correctly identified by the customLongMethod tool */
	private int customLongDCI;
	/** customLongDII - DII Indicator - number of faults incorrectly identified by the customLongMethod tool */
	private int customLongDII;
	/** customLongADCI ADCI Indicator - number of absence of faults correctly identified by the customLongMethod tool */
	private int customLongADCI;
	/**  customLongADII - ADII Indicator - number of absence of faults incorrectly identified by the customLongMethod tool */
	private int customLongADII;
	/** customEnvyDCI - DCI Indicator - number of faults correctly identified by the customFeatureEnvyMethod tool */
	private int customEnvyDCI;
	/** customEnvyDII - DII Indicator - number of faults incorrectly identified by the customFeatureEnvyMethod tool */
	private int customEnvyDII;
	/** customEnvyADCI ADCI Indicator - number of absence of faults correctly identified by the customFeatureEnvyMethod tool */
	private int customEnvyADCI;
	/** customEnvyADII - ADII Indicator - number of absence of faults incorrectly identified by the customFeatureEnvyMethod tool */
	private int customEnvyADII;
	/** excel Rows  - list of Excel Rows from Excel file */
	private String[][] resultsRows;
	
	/**
	 * Creates a QualityIndicator object based on excelRow.
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
		calculateQualityIndicators();
	}
	
	private void calculateQualityIndicators() {
		for (String[] row : resultsRows) {
			boolean is_long_method = Boolean.parseBoolean(row[1]);
			boolean is_feature_envy = Boolean.parseBoolean(row[2]);
			boolean PMD_result = Boolean.parseBoolean(row[3]);
			boolean iPlasma_result = Boolean.parseBoolean(row[4]);
			boolean customLong_result = Boolean.parseBoolean(row[5]);
			boolean customEnvy_result = Boolean.parseBoolean(row[6]);
			
			if(PMD_result && is_long_method)
				PMDDCI++;
			if(iPlasma_result && is_long_method)
				iPlasmaDCI++;
			if(customLong_result && is_long_method)
				customLongDCI++;
			if(customEnvy_result && is_feature_envy)
				customEnvyDCI++;
			if(PMD_result && !is_long_method)
				PMDDII++;
			if(iPlasma_result && !is_long_method)
				iPlasmaDII++;
			if(customLong_result && !is_long_method)
				customLongDII++;
			if(customEnvy_result && !is_feature_envy)
				customEnvyDII++;
			if(!PMD_result && !is_long_method)
				PMDADCI++;
			if(!iPlasma_result && !is_long_method)
				iPlasmaADCI++;
			if(!customLong_result && !is_long_method)
				customLongADCI++;
			if(!customEnvy_result && !is_feature_envy)
				customEnvyADCI++;
			if(!PMD_result && is_long_method)
				PMDADII++;
			if(!iPlasma_result && is_long_method)
				iPlasmaADII++;
			if(!customLong_result && is_long_method)
				customLongADII++;
			if(!customEnvy_result && is_feature_envy)
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

	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the customLongMethod tool
	 */
	public int getCustomLongDCI() {
		return customLongDCI;
	}
	
	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the customLongMethod tool
	 */
	public int getCustomLongADII() {
		return customLongADII;
	}

	/**
	 * @return number of "Ausencia de Defeitos Corretamente Identificados" (ADCI) by the customLongMethod tool
	 */
	public int getCustomLongADCI() {
		return customLongADCI;
	}

	/**
	 * @return  number of "Ausencia de Defeitos Incorretamente Identificados" (ADII) by the customLongMethod tool
	 */
	public int getCustomLongDII() {
		return customLongDII;
	}

	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the customFeatureEnvyMethod tool
	 */
	public int getCustomEnvyDCI() {
		return customEnvyDCI;
	}

	/**
	 * @return number of "Defeitos Incorretamente Identificados" (DII) by the customFeatureEnvyMethod tool
	 */
	public int getCustomEnvyADCI() {
		return customEnvyADCI;
	}

	/**
	 * @return number of "Ausencia de Defeitos Corretamente Identificados" (ADCI) by the customFeatureEnvyMethod tool
	 */
	public int getCustomEnvyDII() {
		return customEnvyDII;
	}

	/**
	 * @return  number of "Ausencia de Defeitos Incorretamente Identificados" (ADII) by the customFeatureEnvyMethod tool
	 */
	public int getCustomEnvyADII() {
		return customEnvyADII;
	}
}
