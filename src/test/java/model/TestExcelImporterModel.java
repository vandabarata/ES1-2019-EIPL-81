package test.java.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

/**
 * @author Lino Silva
 *
 */
class TestExcelImporterModel {

	private static ExcelImporter ei;
	
	@BeforeAll
	static void setUp() throws Exception {
		ei = new ExcelImporter("Long-Method.xlsx");
	}
	
	/**
	 * This test compares the expected content from the first row with the content from a row
	 * returned with the getSingleRow() method 
	 */
	@Test
	void testSingleRowContent() {
		String[] expectedRowUnconverted = { "1", "fat", "DocumentParseFixture", "Output()", "3", "1", "0", "1", "FALSE", "FALSE",
				"FALSE", "FALSE" };
		
		String[] row = ei.getSingleRow(1);
		
		assertEquals(Arrays.toString(row), Arrays.toString(expectedRowUnconverted));
		
	}
	
	/**
	 * This test compares the content from a row with a certain index from getSingleRow()
	 * with the same index in list returned by getAllRows() method
	 */
	@Test
	void testRowIndexContent() {
		String[] row = ei.getSingleRow(1);

		ArrayList<String[]> rowsList = ei.getAllRows();
		
		assertEquals(Arrays.toString(row), Arrays.toString(rowsList.get(1)));
	}

}
