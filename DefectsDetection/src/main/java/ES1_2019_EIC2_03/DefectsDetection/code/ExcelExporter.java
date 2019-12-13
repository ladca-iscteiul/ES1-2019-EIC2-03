package ES1_2019_EIC2_03.DefectsDetection.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.helpers.ParserAdapter;

/**
 * Classe que ira representar a Folha Excel, sendo um Singleton pois nao temos
 *  interesse em abrir a folha mais que uma vez*/
public class ExcelExporter {

	private final static ExcelExporter instance = new ExcelExporter();

	private static XSSFSheet sheet;
	private FileInputStream file;
	private XSSFWorkbook workbook;

	/** Metodo que abre a Stream do Ficheiro Excel e guarda a sua informacao na variavel "sheet"*/

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
	/** Metodo que fecha a Stream do Ficheiro Excel */

	private void closeStream() {
		try {
			workbook.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** Metodo usado para abrir o ficheiro Excel, guardar o seu conteudo numa variavel (sheet) e fechar a stream
	 *  */
	public ExcelExporter() {
		openStream();
		closeStream();
	}

	/**Metodo que retorna o numero linhas
	 * @return numero de linhas */
	public synchronized int NumRows() {


		int numRows = sheet.getLastRowNum();

		//closeStream();

		return numRows;
	}
	/**Metodo que retorna o elemento na linha r e coluna c
	 * @param r row
	 * @param c column
	 * @return elemento na localizacao especificada*/
	
	public synchronized String getElementAt(int r, int c) {

		//openStream();

		XSSFCell cell = (XSSFCell) sheet.getRow(r).getCell(c);
		switch (cell.getCellType()) 
		{
		case NUMERIC:

			//closeStream();

			return String.valueOf(cell.getNumericCellValue());

		case STRING:

			//closeStream();

			return cell.getStringCellValue();

		case BOOLEAN:

			//closeStream();

			return String.valueOf(cell.getBooleanCellValue());

		default:
			break;
		}

		//closeStream();

		return null;
	}
	/**Metodo que vai buscar uma linha numero r 
	 * @param r row
	 * @return linha especificada em formato string*/
	public synchronized String getLine(int r) {

		//openStream();

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

		//closeStream();

		return line;
	}
	/**Metodo que transforma a folha Excel guardada na variavel sheet, 
	 * numa matriz de Strings que sera 
	 * usada para construir a tabela "View Excel file" em "File"
	 * @return matriz representante do ficheiro excel
	*/
	public synchronized String[][] dataToMatrix(){

		//openStream();

		String[][] data = new String[420][11];

		for(int i = 1; i <= sheet.getLastRowNum(); i++ ) {
			data[i-1] = getLine(i).split("<-->");
		}

		//closeStream(); 

		return data;
	}
	/**Getter da instacia do ExcelExporter
	 * @return a  instancia de ExcelExporter*/
	public static ExcelExporter getInstance() {
		return instance;
	}

}