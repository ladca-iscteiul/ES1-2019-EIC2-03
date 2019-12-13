package ES1_2019_EIC2_03.DefectsDetection.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter {

	private final static ExcelExporter instance = new ExcelExporter();

	private static XSSFSheet sheet;
	private FileInputStream file;
	private XSSFWorkbook workbook;

	private void openStream() {
		try {
			file = new FileInputStream(new File("src\\main\\java\\ES1_2019_EIC2_03\\DefectsDetection\\resources\\Long-Method.xlsx"));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void closeStream() {
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ExcelExporter() {
		openStream();
		closeStream();
	}

	public synchronized int NumRows() {
		int numRows = sheet.getLastRowNum();
		return numRows;
	}

	public synchronized String getElementAt(int r, int c) {
		XSSFCell cell = (XSSFCell) sheet.getRow(r).getCell(c);
		switch (cell.getCellType()) 
		{
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());

		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());

		default:
			break;
		}
		return null;
	}

	public synchronized String getLine(int r) {
		XSSFRow row = sheet.getRow(r);
		String line = "";
		Iterator<Cell> cellIterator = row.cellIterator();
		while(cellIterator.hasNext()) {
			XSSFCell cell = (XSSFCell) cellIterator.next();

			switch (cell.getCellType()) 
			{
			case NUMERIC:
				line += String.valueOf(cell.getNumericCellValue()) + "<-->";
				break;
			case STRING:
				line += cell.getStringCellValue() + "<-->";
				break;
			case BOOLEAN:
				line += String.valueOf(cell.getBooleanCellValue()) + "<-->";
				break;
			}
		}
		return line;
	}

	public synchronized String[][] dataToMatrix(){
		String[][] data = new String[420][11];

		for(int i = 1; i <= sheet.getLastRowNum(); i++ ) {
			data[i-1] = getLine(i).split("<-->");
		}
		return data;
	}

	public static ExcelExporter getInstance() {
		return instance;
	}

}