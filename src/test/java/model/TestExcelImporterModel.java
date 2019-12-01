package test.java.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.ExcelImporter;

class TestExcelImporterModel {

	ExcelImporter ei;

	@BeforeEach
	void setUp() throws Exception {
		ei = new ExcelImporter("Long-Method.xlsx");
	}

	@Test
	void testInvalidExcelImporter() {
		ei = new ExcelImporter("potato");
	}

	/**
	 * This test compares the expected content from the first row with the content
	 * from a row returned with the getSingleRow() method
	 */
	@Test
	void testSingleRowContent() {
		String[] expectedRowUnconverted = { "1", "fat", "DocumentParseFixture", "Output()", "3", "1", "0", "1", "FALSE",
				"FALSE", "FALSE", "FALSE" };

		String[] row = ei.getSingleRow(1);

		assertEquals(Arrays.toString(row), Arrays.toString(expectedRowUnconverted));

	}

	/**
	 * Compares the content from a row in a certain sheet with the expected one
	 */
	@Test
	void testSingleRowWithSheetIndex() {
		String[] expectedRowUnconverted = { "1", "fat", "DocumentParseFixture", "Output()", "3", "1", "0", "1", "FALSE",
				"FALSE", "FALSE", "FALSE" };

		String[] rowContent = ei.getSingleRow(0, 1);

		assertEquals(Arrays.toString(rowContent), Arrays.toString((expectedRowUnconverted)));
	}

	/**
	 * This test compares the content from a row with a certain index from
	 * getSingleRow() with the same index in list returned by getAllRows() method
	 */
	@Test
	void testRowIndexContent() {
		String[] row = ei.getSingleRow(1);

		ArrayList<String[]> rowsList = ei.getAllRows();

		assertEquals(Arrays.toString(row), Arrays.toString(rowsList.get(1)));
	}

	/**
	 * Tests if the method getAllRows is returning the expected number of rows
	 */
	@Test
	void testGetAllRows() {
		ArrayList<String[]> allRows = ei.getAllRows(0);
		int numberOfRows = allRows.size();
		assertEquals(421, numberOfRows);
	}

}
