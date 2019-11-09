package test.java.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

class TestExcelImporterModel {

	private ExcelImporter ei;

	@BeforeEach
	void setUp() throws Exception {
		this.ei = new ExcelImporter("Long-Method.xlsx");
	}

	@Test
	void testRowsContent() {
		String[] expectedRowUnconverted = { "1", "fat", "DocumentParseFixture", "Output()", "3", "1", "0", "1", "FALSE", "FALSE",
				"FALSE", "FALSE" };
		
		String[] row = ei.getSingleRow(1);
		assertEquals(Arrays.toString(row), Arrays.toString(expectedRowUnconverted));
		
		ArrayList<String[]> rowsList = ei.getAllRows();
		assertEquals(Arrays.toString(row), Arrays.toString(expectedRowUnconverted));
		
		assertEquals(Arrays.toString(rowsList.get(1)), Arrays.toString(row));
		
		assertNotEquals(Arrays.toString(rowsList.get(2)), Arrays.toString(row));
	}
	
	@Test
	void testConvertionToExcelRowModel() {
		String[] expectedRowUnconverted = { "2", "fat", "DocumentParseFixture", "Structure()", "3", "1", "0", "1", "FALSE", "FALSE",
				"FALSE", "FALSE" };
		
		ExcelRow expectedRowConverted = new ExcelRow(expectedRowUnconverted);
		
		ExcelRow rowConverted = new ExcelRow(ei.getSingleRow(0, 2));

		assertEquals(rowConverted.toString(), expectedRowConverted.toString());
	}

}
