package test.main.java.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.model.ExcelRow;

class TestExcelRowModel {
	
	private ExcelRow row;
	
	@BeforeEach
	void setUp() throws Exception {
		
		ArrayList<String> rowData = new ArrayList<String>(
				Arrays.asList("7", "fat", "ReferenceFixture", "Result()", "29", "5", "5", "0.285714", "FALSE", "FALSE", "TRUE", "TRUE")
		);
		
		this.row = new ExcelRow(rowData);
	}

	@Test
	void test() {
		assertEquals(7, row.getId());
		
		assertEquals("fat", row.getPackageName());
		
		assertEquals("ReferenceFixture", row.getClassName());
		
		assertEquals("Result()", row.getMethodName());
		
		assertEquals(29, row.getLOC());
		
		assertEquals(5, row.getCYCLO());
		
		assertEquals(5, row.getATFD());
		
		assertEquals(0.285714f, row.getLAA());
		
		assertFalse(row.isIs_long_method());
		
		assertFalse(row.isiPlasma());
		
		assertTrue(row.isPMD());
		
		assertTrue(row.isIs_feature_envy());
	}

}
