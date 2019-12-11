package main.java.model;

/**
 * A class that represents the quality indicators of the tools and rules for
 * comparison between them
 */

public class QualityIndicator {

	/**
	 * PMDDCI - DCI Indicator - number of faults correctly identified by the PMD
	 * tool
	 */
	private int PMDDCI;

	/**
	 * PMDDII - DII Indicator - number of faults incorrectly identified by the PMD
	 * tool
	 */
	private int PMDDII;

	/**
	 * PMDADCI ADCI Indicator - number of absence of faults correctly identified by
	 * the PMD tool
	 */
	private int PMDADCI;

	/**
	 * PMDADII - ADII Indicator - number of absence of faults incorrectly identified
	 * by the PMD tool
	 */
	private int PMDADII;

	/**
	 * iPlasmaDCI - DCI Indicator - number of faults correctly identified by the
	 * iPlasma tool
	 */
	private int iPlasmaDCI;

	/**
	 * iPlasmaDII - DII Indicator - number of faults incorrectly identified by the
	 * iPlasma tool
	 */
	private int iPlasmaDII;

	/**
	 * iPlasmaADCI ADCI Indicator - number of absence of faults correctly identified
	 * by the iPlasma tool
	 */
	private int iPlasmaADCI;

	/**
	 * iPlasmaADII - ADII Indicator - number of absence of faults incorrectly
	 * identified by the iPlasma tool
	 */
	private int iPlasmaADII;

	/**
	 * customLongDCI - DCI Indicator - number of faults correctly identified by the
	 * customLongMethod rule
	 */
	private int customLongDCI;

	/**
	 * customLongDII - DII Indicator - number of faults incorrectly identified by
	 * the customLongMethod rule
	 */
	private int customLongDII;

	/**
	 * customLongADCI ADCI Indicator - number of absence of faults correctly
	 * identified by the customLongMethod rule
	 */
	private int customLongADCI;

	/**
	 * customLongADII - ADII Indicator - number of absence of faults incorrectly
	 * identified by the customLongMethod rule
	 */
	private int customLongADII;

	/**
	 * customEnvyDCI - DCI Indicator - number of faults correctly identified by the
	 * customFeatureEnvyMethod rule
	 */
	private int customEnvyDCI;

	/**
	 * customEnvyDII - DII Indicator - number of faults incorrectly identified by
	 * the customFeatureEnvy rule
	 */
	private int customEnvyDII;

	/**
	 * customEnvyADCI ADCI Indicator - number of absence of faults correctly
	 * identified by the customFeatureEnvy rule
	 */
	private int customEnvyADCI;

	/**
	 * customEnvyADII - ADII Indicator - number of absence of faults incorrectly
	 * identified by the customFeatureEnvy rule
	 */
	private int customEnvyADII;

	/** results Rows - rows with the rules and tools results */
	private String[][] resultsRows;

	/**
	 * Creates a QualityIndicator object based on excelRows, that will calculate the
	 * Quality Indicators based on information taken from the Excel file
	 * 
	 * @param String[][] resultsRows - matrix holding the quality results for the
	 *                   tools and rules in the excel and defined by the user
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

	/**
	 * Parses the results for the default rules and given tools and calculates the
	 * quality indicators based on the comparison between the real value (the
	 * is_long_method boolean or the is_feature_envy boolean given in the original
	 * excel file) and the tools or rule's results for that indicator
	 */
	private void calculateQualityIndicators() {
		for (String[] row : resultsRows) {
			boolean is_long_method = Boolean.parseBoolean(row[1]);
			boolean is_feature_envy = Boolean.parseBoolean(row[2]);
			boolean PMD_result = Boolean.parseBoolean(row[3]);
			boolean iPlasma_result = Boolean.parseBoolean(row[4]);
			boolean customLong_result = Boolean.parseBoolean(row[5]);
			boolean customEnvy_result = Boolean.parseBoolean(row[6]);

			if (PMD_result && is_long_method)
				PMDDCI++;
			if (iPlasma_result && is_long_method)
				iPlasmaDCI++;
			if (customLong_result && is_long_method)
				customLongDCI++;
			if (customEnvy_result && is_feature_envy)
				customEnvyDCI++;
			if (PMD_result && !is_long_method)
				PMDDII++;
			if (iPlasma_result && !is_long_method)
				iPlasmaDII++;
			if (customLong_result && !is_long_method)
				customLongDII++;
			if (customEnvy_result && !is_feature_envy)
				customEnvyDII++;
			if (!PMD_result && !is_long_method)
				PMDADCI++;
			if (!iPlasma_result && !is_long_method)
				iPlasmaADCI++;
			if (!customLong_result && !is_long_method)
				customLongADCI++;
			if (!customEnvy_result && !is_feature_envy)
				customEnvyADCI++;
			if (!PMD_result && is_long_method)
				PMDADII++;
			if (!iPlasma_result && is_long_method)
				iPlasmaADII++;
			if (!customLong_result && is_long_method)
				customLongADII++;
			if (!customEnvy_result && is_feature_envy)
				customEnvyADII++;
		}
	}

	/**
	 * Getter for the amount of DCI calculated for the PMD tool
	 * 
	 * @return int PMDDCI - amount of "Defeitos Corretamente Identificados" (DCI) by
	 *         the PMD tool
	 */
	public int getPMDDCI() {
		return PMDDCI;
	}

	/**
	 * Getter for the amount of DII calculated for the PMD tool
	 * 
	 * @return int PMDDII - amount of "Defeitos Incorretamente Identificados" (DII)
	 *         by the PMD tool
	 */
	public int getPMDDII() {
		return PMDDII;
	}

	/**
	 * Getter for the amount of ADCI calculated for the PMD tool
	 * 
	 * @return int PMDADCI - amount of "Ausência de Defeitos Corretamente
	 *         Identificados" (ADCI) by the PMD tool
	 */
	public int getPMDADCI() {
		return PMDADCI;
	}

	/**
	 * Getter for the amount of ADII calculated for the PMD tool
	 * 
	 * @return int PMDADII - amount of "Ausência de Defeitos Incorretamente
	 *         Identificados" (ADII) by the PMD tool
	 * 
	 */
	public int getPMDADII() {
		return PMDADII;
	}

	/**
	 * Getter for the amount of DCI calculated for the iPlasma tool
	 * 
	 * @return int iPlasmaDCI - amount of "Defeitos Corretamente Identificados"
	 *         (DCI) by the iPlasma tool
	 */
	public int getIPlasmaDCI() {
		return iPlasmaDCI;
	}

	/**
	 * Getter for the amount of DII calculated for the iPlasma tool
	 * 
	 * @return int iPlasmaDII - amount of "Defeitos Incorretamente Identificados"
	 *         (DII) by the iPlasma tool
	 */
	public int getIPlasmaDII() {
		return iPlasmaDII;
	}

	/**
	 * Getter for the amount of ADCI calculated for the iPlasma tool
	 * 
	 * @return int iPlasmaADCI - amount of "Ausência de Defeitos Corretamente
	 *         Identificados" (ADCI) by the iPlasma tool
	 */
	public int getIPlasmaADCI() {
		return iPlasmaADCI;
	}

	/**
	 * Getter for the amount of ADII calculated for the iPlasma tool
	 * 
	 * @return iPlasmaADII - amount of "Ausência de Defeitos Incorretamente
	 *         Identificados" (ADII) by the iPlasma tool
	 */
	public int getIPlasmaADII() {
		return iPlasmaADII;
	}

	/**
	 * Getter for the amount of DCI calculated for the custom_is_long_method rule
	 * 
	 * @return int customLongDCI - amount of "Defeitos Incorretamente Identificados"
	 *         (DII) by the custom_is_long_method rule
	 */
	public int getCustomLongDCI() {
		return customLongDCI;
	}

	/**
	 * Getter for the amount of DII calculated for the custom_is_long_method rule
	 * 
	 * @return int customLongADII - amount of "Defeitos Incorretamente
	 *         Identificados" (DII) by the custom_is_long_method rule
	 */
	public int getCustomLongADII() {
		return customLongADII;
	}

	/**
	 * Getter for the amount of ADCI calculated for the custom_is_long_method rule
	 * 
	 * @return int customLongADCI - amount of "Ausência de Defeitos Corretamente
	 *         Identificados" (ADCI) by the custom_is_long_method rule
	 */
	public int getCustomLongADCI() {
		return customLongADCI;
	}

	/**
	 * Getter for the amount of DII calculated for the custom_is_long_method rule
	 * 
	 * @return int customLongDII - amount of "Ausência de Defeitos Incorretamente
	 *         Identificados" (ADII) by the custom_is_long_method rule
	 */
	public int getCustomLongDII() {
		return customLongDII;
	}

	/**
	 * Getter for the amount of DCI calculatd for the custom_is_feature_envy rule
	 * 
	 * @return int customEnvyDCI - amount of "Defeitos Incorretamente Identificados"
	 *         (DII) by the custom_is_feature_envy rule
	 */
	public int getCustomEnvyDCI() {
		return customEnvyDCI;
	}

	/**
	 * Getter for the amount of ADCI calculated for the custom_is_feature_envy rule
	 * 
	 * @return int customEnvyADCI - amount of "Defeitos Incorretamente
	 *         Identificados" (DII) by the custom_is_feature_envy rule
	 */
	public int getCustomEnvyADCI() {
		return customEnvyADCI;
	}

	/**
	 * Getter for the amount of DII calculated for the custom_is_feature_envy rule
	 * 
	 * @return int customEnvyDII - amount of "Ausência de Defeitos Corretamente
	 *         Identificados" (ADCI) by the custom_is_feature_envy rule
	 */
	public int getCustomEnvyDII() {
		return customEnvyDII;
	}

	/**
	 * Getter for the amount of ADII calculated for the custom_is_feature_envy rule
	 * 
	 * @return int customEnvyADII - amount of "Ausência de Defeitos Incorretamente
	 *         Identificados" (ADII) by the custom_is_feature_envy rule
	 */
	public int getCustomEnvyADII() {
		return customEnvyADII;
	}
}
