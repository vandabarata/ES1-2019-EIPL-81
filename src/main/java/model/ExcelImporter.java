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
 * Object that reads an imported Excel file.
 * 
 */
public class ExcelImporter {

	/**
	 * The excel workbook object generated from the information of the imported
	 * excel file
	 */
	private Workbook wb;

	/**
	 * Receives an excel file and parses it to a workbook object, if valid
	 * 
	 * @param String file
	 */
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
	 * @param int sheetIndex - Index of the Excel sheet
	 * @param int rowIndex - Index of the row from the Excel
	 * @return String[] rowContent - An array with the cell values from the chosen
	 *         row
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
	 * @param int rowIndex - Index of the row from the Excel
	 * @return String[] rowContent - An array with the cell values taken from the
	 *         chosen row from the first sheet
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
	 * Gets the data from all rows of the excel file
	 * 
	 * @param int sheetIndex - Index of the Excel sheet
	 * @return ArrayList<String[]> rowsList - A list with an array for each row with
	 *         their cell values
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
	 * Gets the data from all rows from the excel's first sheet
	 * 
	 * @return ArrayList<String[]> rowsList - A list with an array for each row with
	 *         their cell values
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
