package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import main.java.model.ExcelRow;
import main.java.model.QualityIndicator;

/**
 * This test is validating the calculation of quality indicators from Excel file
 */
@RunWith(JUnitPlatform.class)
public class TestQualityIndicator {


	QualityIndicator sample;
	int PMDDCI, PMDDII, PMDADCI, PMDADII, iPlasmaDCI, iPlasmaDII, iPlasmaADCI, iPlasmaADII;
	
	
	/**
	 * This method is necessary to generate a ArrayList of ExcelRow objects
	 * that it's going to used as input in QualityIndicator object
	 */
    public ArrayList<ExcelRow> myArrayListExcelRow() {
    	ArrayList<ExcelRow> excelRows = new ArrayList<ExcelRow>();
    	String [] s1 = {"1", "fat", "GrammerException", "parseMethod()", "3", "45", "1", "3.55", "TRUE", "FALSE", "TRUE", "TRUE"};
    	String [] s2 = {"1", "fat", "GrammerException", "parseMethod()", "3", "5", "12", "3.5", "TRUE", "TRUE", "TRUE", "TRUE"};
    	String [] s3 = {"1", "fat", "GrammerException", "parseMethod()", "3", "45", "1", "3.55", "TRUE", "FALSE", "FALSE", "TRUE"};
    	String [] s4 = {"1", "fat", "GrammerException", "parseMethod()", "3", "5", "12", "3.5", "TRUE", "TRUE", "FALSE", "TRUE"};
    	String [] s5 = {"1", "fat", "GrammerException", "parseMethod()", "3", "45", "1", "3.55", "FALSE", "FALSE", "TRUE", "TRUE"};
    	String [] s6 = {"1", "fat", "GrammerException", "parseMethod()", "3", "5", "12", "3.5", "FALSE", "TRUE", "TRUE", "TRUE"};
    	String [] s7 = {"1", "fat", "GrammerException", "parseMethod()", "3", "45", "1", "3.55", "FALSE", "FALSE", "FALSE", "TRUE"};
    	String [] s8 = {"1", "fat", "GrammerException", "parseMethod()", "3", "5", "12", "3.5", "FALSE", "TRUE", "FALSE", "TRUE"};
    	ExcelRow row1 = new ExcelRow(s1);
    	ExcelRow row2 = new ExcelRow(s2);
    	ExcelRow row3 = new ExcelRow(s3);
    	ExcelRow row4 = new ExcelRow(s4);
    	ExcelRow row5 = new ExcelRow(s5);
    	ExcelRow row6 = new ExcelRow(s6);
    	ExcelRow row7 = new ExcelRow(s7);
    	ExcelRow row8 = new ExcelRow(s8);
    	excelRows.add(row1);
    	excelRows.add(row2);
    	excelRows.add(row3);
    	excelRows.add(row4);
    	excelRows.add(row5);
    	excelRows.add(row6);
    	excelRows.add(row7);
    	excelRows.add(row8);
        return excelRows;
    }
	
	
	@BeforeEach
	void setup() {
		PMDDCI = 0; 
		PMDDII = 0; 
		PMDADCI = 0; 
		PMDADII = 0;
		iPlasmaDCI = 0;
		iPlasmaDII = 0;
		iPlasmaADCI = 0;
		iPlasmaADII = 0;
		sample = new QualityIndicator(myArrayListExcelRow());
	}
	
	/**
	 * Asserts that the number of DCI quality indicator using 
	 * the method of the class is the same as the number of  DCI quality indicator
	 *  of the samples used in test preparation - PMD tool
	 */
	@Test
	void testGetPMDDCI() {
		assertEquals(PMDDCI=2, sample.getPMDDCI());
	}
	
	/**
	 * Asserts that the number of DII quality indicator using 
	 * the method of the class is the same as the number of  DII quality indicator
	 *  of the samples used in test preparation - PMD tool
	 */
	@Test
	void testGetPMDDII() {
		assertEquals(PMDDII=2, sample.getPMDDII());
	}
	
	/**
	 * Asserts that the number of ADCI quality indicator using 
	 * the method of the class is the same as the number of  ADCI quality indicator
	 *  of the samples used in test preparation - PMD tool
	 */
	@Test
	void testGetPMDADCI() {
		assertEquals(PMDADCI=2, sample.getPMDADCI());
	}
	
	/**
	 * Asserts that the number of ADII quality indicator using 
	 * the method of the class is the same as the number of  ADII quality indicator
	 *  of the samples used in test preparation - PMD tool
	 */
	@Test
	void testGetPMDADII() {
		assertEquals(PMDADII=2, sample.getPMDADII());
	}
	
	/**
	 * Asserts that the number of DCI quality indicator using 
	 * the method of the class is the same as the number of  DCI quality indicator
	 *  of the samples used in test preparation - iPlasma tool
	 */
	@Test
	void testGetIPlasmaDCI() {
		assertEquals(iPlasmaDCI=2, sample.getIPlasmaDCI());
	}
	
	/**
	 * Asserts that the number of DII quality indicator using 
	 * the method of the class is the same as the number of  DII quality indicator
	 *  of the samples used in test preparation - iPlasma tool
	 */
	@Test
	void testGetPlasmaDII() {
		System.out.println(sample.getIPlasmaDII());
		assertEquals(iPlasmaDII=2, sample.getIPlasmaDII());
	}
	
	/**
	 * Asserts that the number of ADCI quality indicator using 
	 * the method of the class is the same as the number of ADCI quality indicator
	 *  of the samples used in test preparation - iPlasma tool
	 */
	@Test
	void testGetPlasmaADCI() {
		assertEquals(iPlasmaADCI=2, sample.getIPlasmaADCI());
	}
	
	/**
	 * Asserts that the number of ADII quality indicator using 
	 * the method of the class is the same as the number of  ADII quality indicator
	 *  of the samples used in test preparation - iPlasma
	 */
	@Test
	void testGetPlasmaADII() {
		assertEquals(iPlasmaADII=2, sample.getIPlasmaADII());
	}
}
