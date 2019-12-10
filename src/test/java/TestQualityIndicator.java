package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.QualityIndicator;

@RunWith(JUnitPlatform.class)
class TestQualityIndicator {

	QualityIndicator quality;

	@BeforeEach
	void setUp() throws Exception {
		String[][] mockExcelData = {{"1", "TRUE", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE"}, {"2", "FALSE", "TRUE", "TRUE", "TRUE", "TRUE", "TRUE"}, {"3", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE", "FALSE"}, {"4","TRUE", "TRUE", "FALSE", "FALSE", "FALSE", "FALSE"}};
		quality = new QualityIndicator(mockExcelData);
	}

	@Test
	void testGetPMDDCI() {
		int PMDDCI = 1;
		assertEquals(PMDDCI, quality.getPMDDCI());
	}

	@Test
	void testGetPMDDII() {
		int PMDDII = 1;
		assertEquals(PMDDII, quality.getPMDDII());
	}

	@Test
	void testGetPMDADCI() {
		int PMDADCI = 1;
		assertEquals(PMDADCI, quality.getPMDADCI());
	}

	@Test
	void testGetPMDADII() {
		int PMDADII = 1;
		assertEquals(PMDADII, quality.getPMDADII());
	}

	@Test
	void testGetIPlasmaDCI() {
		int iPlasmaDCI = 1;
		assertEquals(iPlasmaDCI, quality.getIPlasmaDCI());
	}

	@Test
	void testGetIPlasmaDII() {
		int iPlasmaDII = 1;
		assertEquals(iPlasmaDII, quality.getIPlasmaDII());
	}

	@Test
	void testGetIPlasmaADCI() {
		int iPlasmaADCI = 1;
		assertEquals(iPlasmaADCI, quality.getIPlasmaADCI());
	}

	@Test
	void testGetIPlasmaADII() {
		int iPlasmaADII = 1;
		assertEquals(iPlasmaADII, quality.getIPlasmaADII());
	}

	@Test
	void testGetCustomLongDCI() {
		int customLongDCI = 1;
		assertEquals(customLongDCI, quality.getCustomLongDCI());
	}

	@Test
	void testGetCustomLongADII() {
		int customLongADII = 1;
		assertEquals(customLongADII, quality.getCustomLongADII());
	}

	@Test
	void testGetCustomLongADCI() {
		int customLongADCI = 1;
		assertEquals(customLongADCI, quality.getCustomLongADCI());
	}

	@Test
	void testGetCustomLongDII() {
		int customLongDII = 1;
		assertEquals(customLongDII, quality.getCustomLongDII());
	}

	@Test
	void testGetCustomEnvyDCI() {
		int customEnvyDCI = 1;
		assertEquals(customEnvyDCI, quality.getCustomEnvyDCI());
	}

	@Test
	void testGetCustomEnvyADCI() {
		int customEnvyADCI = 1;
		assertEquals(customEnvyADCI, quality.getCustomEnvyADCI());
	}

	@Test
	void testGetCustomEnvyDII() {
		int customEnvyDII = 1;
		assertEquals(customEnvyDII, quality.getCustomEnvyDII());
	}

	@Test
	void testGetCustomEnvyADII() {
		int customEnvyADII = 1;
		assertEquals(customEnvyADII, quality.getCustomEnvyADII());
	}

}
