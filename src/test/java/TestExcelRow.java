package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

/**
 * Tests for the ExcelRow model
 *
 */
@RunWith(JUnitPlatform.class)
class TestExcelRow {

	/** Initialises an ExcelImporter object for testing */
	ExcelImporter ei;

	/** Initialises an ExcelRow object for testing the data */
	ExcelRow row;

	/**
	 * Takes content from the first row with data for testing
	 * 
	 * @throws Exception - Exception is thrown if the excel file can't be opened
	 */
	@BeforeEach
	void setUp() throws Exception {
		ei = new ExcelImporter("Long-Method.xlsx");
		String[] rowData = ei.getSingleRow(1);
		row = new ExcelRow(rowData);
	}

	/**
	 * Validates the methodID returned by the ExcelRow model
	 */
	@Test
	void testGetId() {
		int id = row.getId();
		assertEquals(1, id);
	}

	/**
	 * Validates the package name returned by the ExcelRow model
	 */
	@Test
	void testGetPackageName() {
		String pkg = row.getPackageName();
		assertEquals("fat", pkg);
	}

	/**
	 * Validates the class name returned by the ExcelRow model
	 */
	@Test
	void testGetClassName() {
		String className = row.getClassName();
		assertEquals("DocumentParseFixture", className);
	}

	/**
	 * Validates the method name returned by the ExcelRow model
	 */
	@Test
	void testGetMethodName() {
		String method = row.getMethodName();
		assertEquals("Output()", method);
	}

	/**
	 * Validates the LOC value returned by the ExcelRow model
	 */
	@Test
	void testGetLOC() {
		int LOC = row.getLOC();
		assertEquals(3, LOC);
	}

	/**
	 * Validates the CYCLO value returned by the ExcelRow model
	 */
	@Test
	void testGetCYCLO() {
		int CYCLO = row.getCYCLO();
		assertEquals(1, CYCLO);
	}

	/**
	 * Validates the ATFD value returned by the ExcelRow model
	 */
	@Test
	void testGetATFD() {
		int ATFD = row.getATFD();
		assertEquals(0, ATFD);
	}

	/**
	 * Validates the LAA value returned by the ExcelRow model
	 */
	@Test
	void testGetLAA() {
		float LAA = row.getLAA();
		assertEquals(1, LAA);
	}

	/**
	 * Asserts if the is_long_method attribute is returning the correct boolean
	 */
	@Test
	void testIsIs_long_method() {
		boolean is_long_method = row.isLongMethod();
		assertEquals(false, is_long_method);
	}

	/**
	 * Validates the iPlasma result value returned by the ExcelRow model
	 */
	@Test
	void testIsiPlasma() {
		boolean iPlasma = row.getIPlasmaResult();
		assertEquals(false, iPlasma);
	}

	/**
	 * Validates the PMD result value returned by the ExcelRow model
	 */
	@Test
	void testIsPMD() {
		boolean PMD = row.getPMDResult();
		assertEquals(false, PMD);
	}

	/**
	 * Asserts if the is_feature_envy attribute is returning the correct boolean
	 */
	@Test
	void testIsIs_feature_envy() {
		boolean is_feature_envy = row.isFeatureEnvy();
		assertEquals(false, is_feature_envy);
	}

	/**
	 * Validates the ExcelRow's model toString content to check it if matches the
	 * intended
	 */
	@Test
	void testToString() {
		String rowString = row.toString();
		assertEquals("ID: " + row.getId() + "\nPackage: " + row.getPackageName() + "\nClass: " + row.getClassName()
				+ "\nMethod: " + row.getMethodName() + "\nLOC: " + row.getLOC() + "\nCYCLO: " + row.getCYCLO()
				+ "\nATFD: " + row.getATFD() + "\nLAA: " + row.getLAA() + "\nIs Long Method: " + row.isLongMethod()
				+ "\niPlasma: " + row.getIPlasmaResult() + "\nPMD: " + row.getPMDResult() + "\nIs Feature Envy: "
				+ row.isFeatureEnvy(), rowString);
	}

}
