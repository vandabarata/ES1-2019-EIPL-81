package main.java.model;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelImporter {
	
	private Workbook wb;
	
	public ExcelImporter(String file) {
		try {
			this.wb = WorkbookFactory.create(new FileInputStream(file));
		} catch(Exception ioe) {
			System.out.println("Ficheiro não encontrado");
		    ioe.printStackTrace();
		}
	}
	
	public ExcelRow getRow(int rowIndex) {
		try {
			
			ArrayList<String> rowContent = new ArrayList<String>();
			
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(rowIndex);
	       	
			for (Cell cell : row) {
	       		rowContent.add(new DataFormatter().formatCellValue(cell));
	        }
			
			return new ExcelRow(rowContent);
		} catch(Exception e) {
			
			System.out.println("Invalid row...");
			System.out.println("Showing next valid row...");
			
			return getRow(++rowIndex);
		}
	}
	
	public ArrayList<ExcelRow> getRows() {
		ArrayList<ExcelRow> rowsList = new ArrayList<ExcelRow>();
		ArrayList<String> rowContent = new ArrayList<String>();
		
		Sheet sheet = wb.getSheetAt(0);
		
	    for (Row row : sheet) {
	      	
	        for (Cell cell : row) {
	          	rowContent.add(new DataFormatter().formatCellValue(cell));
	        }
	        try {
	        	rowsList.add(new ExcelRow(rowContent));
	        } catch(Exception e) {
	        	System.out.println("Row number " + row.getRowNum() + " invalid");
	        }
	        rowContent.clear();
	    }
		
		return rowsList;
	}
	
}
