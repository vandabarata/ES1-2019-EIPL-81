package main.java.model;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * A class that reads an Excel file.
 * 
 * @author Lino Silva
 */
public class ExcelImporter {

	private Workbook wb;

	public ExcelImporter(String file) {
		try {
			this.wb = WorkbookFactory.create(new FileInputStream(file));
		} catch (Exception ioe) {
			System.out.println("Invalid file");
		}
	}

	/**
	 * Gets the data from a row of the excel in a selected sheet
	 * 
	 * @param sheetIndex Index of the Excel sheet
	 * @param rowIndex   Index of the row from the Excel
	 * @return An array with the cell values from the row chosen
	 */
	public String[] getSingleRow(int sheetIndex, int rowIndex) {
		ArrayList<String> rowContent = new ArrayList<String>();

		Sheet sheet = wb.getSheetAt(sheetIndex);

		Row row = sheet.getRow(rowIndex);

		for (Cell cell : row) {
			rowContent.add(new DataFormatter().formatCellValue(cell));
		}

		return rowContent.toArray(new String[0]);
	}
	
	/**
	 * Gets the data from a row of the excel from the first sheet
	 * 
	 * @param rowIndex Index of the row from the Excel
	 * @return An array with the cell values from the row chosen of the first sheet
	 */
	public String[] getSingleRow(int rowIndex) {
		ArrayList<String> rowContent = new ArrayList<String>();

		Sheet sheet = wb.getSheetAt(0);

		Row row = sheet.getRow(rowIndex);

		for (Cell cell : row) {
			rowContent.add(new DataFormatter().formatCellValue(cell));
		}

		return rowContent.toArray(new String[0]);
	}

	/**
	 * Gets the data from all rows of the excel
	 * 
	 * @param sheetIndex Index of the Excel sheet
	 * @return A list with an array for each row with it's cell's values
	 */
	public ArrayList<String[]> getAllRows(int sheetIndex) {
		ArrayList<String[]> rowsList = new ArrayList<String[]>();
		ArrayList<String> rowContent = new ArrayList<String>();

		Sheet sheet = wb.getSheetAt(sheetIndex);

		for (Row row : sheet) {

			for (Cell cell : row) {
				rowContent.add(new DataFormatter().formatCellValue(cell));
			}
			rowsList.add(rowContent.toArray(new String[0]));
			
			rowContent.clear();
		}

		return rowsList;
	}

	/**
	 * Gets the data from all row of the excel's first sheet
	 * 
	 * @return A list with an array for each row of the first sheet with it's cell's
	 *         values
	 */
	public ArrayList<String[]> getAllRows() {
		ArrayList<String[]> rowsList = new ArrayList<String[]>();
		ArrayList<String> rowContent = new ArrayList<String>();

		Sheet sheet = wb.getSheetAt(0);

		for (Row row : sheet) {

			for (Cell cell : row) {
				rowContent.add(new DataFormatter().formatCellValue(cell));
			}

			rowsList.add(rowContent.toArray(new String[0]));

			rowContent.clear();
		}

		return rowsList;
	}

}
