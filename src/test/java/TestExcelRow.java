package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

@RunWith(JUnitPlatform.class)
class TestExcelRow {

	
	ExcelImporter ei;
	ExcelRow row;
	
	@BeforeEach
	void setUp() throws Exception {
		ei = new ExcelImporter("Long-Method.xlsx");
		String[] rowData = ei.getSingleRow(1);
		row = new ExcelRow(rowData);
	}

	@Test
	void testGetId() {
		int id = row.getId();
		assertEquals(1, id);
	}

	@Test
	void testGetPackageName() {
		String pkg = row.getPackageName();
		assertEquals("fat", pkg);
	}

	@Test
	void testGetClassName() {
		String className = row.getClassName();
		assertEquals("DocumentParseFixture", className);
	}

	@Test
	void testGetMethodName() {
		String method = row.getMethodName();
		assertEquals("Output()", method);
	}

	@Test
	void testGetLOC() {
		int LOC = row.getLOC();
		assertEquals(3, LOC);
	}

	@Test
	void testGetCYCLO() {
		int CYCLO = row.getCYCLO();
		assertEquals(1, CYCLO);
	}

	@Test
	void testGetATFD() {
		int ATFD = row.getATFD();
		assertEquals(0, ATFD);
	}

	@Test
	void testGetLAA() {
		float LAA = row.getLAA();
		assertEquals(1, LAA);
	}

	@Test
	void testIsIs_long_method() {
		boolean is_long_method = row.isLongMethod();
		assertEquals(false, is_long_method);
	}

	@Test
	void testIsiPlasma() {
		boolean iPlasma = row.getIPlasmaResult();
		assertEquals(false, iPlasma);
	}

	@Test
	void testIsPMD() {
		boolean PMD = row.getPMDResult();
		assertEquals(false, PMD);
	}

	@Test
	void testIsIs_feature_envy() {
		boolean is_feature_envy = row.isFeatureEnvy();
		assertEquals(false, is_feature_envy);
	}

	@Test
	void testToString() {
		String rowString = row.toString();
		assertEquals("ID: " + row.getId() + "\nPackage: " + row.getPackageName() + "\nClass: " + row.getClassName() + "\nMethod: " + row.getMethodName()
				+ "\nLOC: " + row.getLOC() + "\nCYCLO: " + row.getCYCLO() + "\nATFD: " + row.getATFD() + "\nLAA: " + row.getLAA() + "\nIs Long Method: "
				+ row.isLongMethod() + "\niPlasma: " + row.getIPlasmaResult() + "\nPMD: " + row.getPMDResult() + "\nIs Feature Envy: " + row.isFeatureEnvy(), rowString);
	}
	

}
