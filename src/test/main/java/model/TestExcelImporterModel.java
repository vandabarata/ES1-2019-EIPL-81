package test.main.java.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.ExcelImporter;
import main.java.model.ExcelRow;

class TestExcelImporterModel {
	
	private ExcelImporter ei;
	private ExcelRow expectedRow;
	
	@BeforeEach
	void setUp() throws Exception {
		this.ei = new ExcelImporter("Long-Method.xlsx");
		
		ArrayList<String> rowData = new ArrayList<String>(
				Arrays.asList("1", "fat", "DocumentParseFixture", "Output()", "3", "1", "0", "1", "FALSE", "FALSE", "FALSE", "FALSE")
		);
		
		this.expectedRow = new ExcelRow(rowData);
	}

	@Test
	void test() {
		ExcelRow row1 = ei.getRow(0);
		assertEquals(expectedRow.toString(), row1.toString());
		
		ExcelRow row2 = ei.getRow(1);
		assertEquals(expectedRow.toString(), row2.toString());
		
		ExcelRow row3 = ei.getRow(2);
		assertNotEquals(expectedRow.toString(), row3.toString());
		
		ExcelRow row4 = ei.getRows().get(0);
		assertEquals(row2.toString(), row4.toString());
	}

}
