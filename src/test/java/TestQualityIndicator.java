package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.QualityIndicator;

/**
 * Tests for the Quality Indicator Model
 *
 */
@RunWith(JUnitPlatform.class)
class TestQualityIndicator {

	/** QualityIndicator object used for testing */
	QualityIndicator quality;

	/**
	 * Initialises a QualityIndicator object for testing that can test every single
	 * case for calculations
	 * 
	 * @throws Exception - Exception is thrown if the object can't be created
	 */
	@BeforeEach
	void setUp() throws Exception {
		String[][] mockExcelData = { { "1", "TRUE", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "2", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE", "TRUE" },
				{ "3", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE" },
				{ "4", "TRUE", "TRUE", "FALSE", "FALSE", "FALSE", "FALSE" } };
		quality = new QualityIndicator(mockExcelData);
	}

	/**
	 * Asserts that the DCI results for the PMD tool match what's expected
	 */
	@Test
	void testGetPMDDCI() {
		int PMDDCI = 1;
		assertEquals(PMDDCI, quality.getPMDDCI());
	}

	/**
	 * Asserts that the DII results for the PMD tool match what's expected
	 */
	@Test
	void testGetPMDDII() {
		int PMDDII = 1;
		assertEquals(PMDDII, quality.getPMDDII());
	}

	/**
	 * Asserts that the ADCI results for the PMD tool match what's expected
	 */
	@Test
	void testGetPMDADCI() {
		int PMDADCI = 1;
		assertEquals(PMDADCI, quality.getPMDADCI());
	}

	/**
	 * Asserts that the ADII results for the PMD tool match what's expected
	 */
	@Test
	void testGetPMDADII() {
		int PMDADII = 1;
		assertEquals(PMDADII, quality.getPMDADII());
	}

	/**
	 * Asserts that the DCI results for the iPlasma tool match what's expected
	 */
	@Test
	void testGetIPlasmaDCI() {
		int iPlasmaDCI = 1;
		assertEquals(iPlasmaDCI, quality.getIPlasmaDCI());
	}

	/**
	 * Asserts that the DII results for the iPlasma tool match what's expected
	 */
	@Test
	void testGetIPlasmaDII() {
		int iPlasmaDII = 1;
		assertEquals(iPlasmaDII, quality.getIPlasmaDII());
	}

	/**
	 * Asserts that the ADCI results for the iPlasma tool match what's expected
	 */
	@Test
	void testGetIPlasmaADCI() {
		int iPlasmaADCI = 1;
		assertEquals(iPlasmaADCI, quality.getIPlasmaADCI());
	}

	/**
	 * Asserts that the ADII results for the iPlasma tool match what's expected
	 */
	@Test
	void testGetIPlasmaADII() {
		int iPlasmaADII = 1;
		assertEquals(iPlasmaADII, quality.getIPlasmaADII());
	}

	/**
	 * Asserts that the DCI results for the custom is_long_method rule match what's
	 * expected
	 */
	@Test
	void testGetCustomLongDCI() {
		int customLongDCI = 1;
		assertEquals(customLongDCI, quality.getCustomLongDCI());
	}

	/**
	 * Asserts that the ADII results for the custom is_long_method rule match what's
	 * expected
	 */
	@Test
	void testGetCustomLongADII() {
		int customLongADII = 1;
		assertEquals(customLongADII, quality.getCustomLongADII());
	}

	/**
	 * Asserts that the ADCI results for the custom is_long_method rule match what's
	 * expected
	 */
	@Test
	void testGetCustomLongADCI() {
		int customLongADCI = 1;
		assertEquals(customLongADCI, quality.getCustomLongADCI());
	}

	/**
	 * Asserts that the DII results for the custom is_long_method rule match what's
	 * expected
	 */
	@Test
	void testGetCustomLongDII() {
		int customLongDII = 1;
		assertEquals(customLongDII, quality.getCustomLongDII());
	}

	/**
	 * Asserts that the DCI results for the custom is_feature_envy rule match what's
	 * expected
	 */
	@Test
	void testGetCustomEnvyDCI() {
		int customEnvyDCI = 1;
		assertEquals(customEnvyDCI, quality.getCustomEnvyDCI());
	}

	/**
	 * Asserts that the ADCI results for the custom is_feature_envy rule match
	 * what's expected
	 */
	@Test
	void testGetCustomEnvyADCI() {
		int customEnvyADCI = 1;
		assertEquals(customEnvyADCI, quality.getCustomEnvyADCI());
	}

	/**
	 * Asserts that the DII results for the custom is_feature_envy rule match what's
	 * expected
	 */
	@Test
	void testGetCustomEnvyDII() {
		int customEnvyDII = 1;
		assertEquals(customEnvyDII, quality.getCustomEnvyDII());
	}

	/**
	 * Asserts that the ADII results for the custom is_feature_envy rule match
	 * what's expected
	 */
	@Test
	void testGetCustomEnvyADII() {
		int customEnvyADII = 1;
		assertEquals(customEnvyADII, quality.getCustomEnvyADII());
	}

}
