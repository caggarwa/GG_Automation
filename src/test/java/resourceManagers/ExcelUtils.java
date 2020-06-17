package resourceManagers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import javax.management.RuntimeErrorException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVWriter;

public class ExcelUtils {

	public enum CellBackgroundColor 
	{ 
		NoColor,Red, Green, Yellow, Blue; 
	} 

	String baseUrl;
	String fileName;
	String csvFileName;
	String sheetName;
	File file;
	private XSSFWorkbook  workbook;
	private XSSFSheet sheet;
	String env;




	public void setFileName(String excelFileName, String SheetName) {
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			fileName = System.getProperty("user.dir")+"//Data//" + excelFileName + ".xlsx";
		}
		else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			fileName = System.getProperty("user.dir")+"\\Data\\" + excelFileName + ".xlsx";
		}
		sheetName=SheetName;
	}

	public String getFileName() {
		//		System.out.println("File Name: " + fileName);
		return fileName;

	}

	public String getSheetName() {
		return sheetName;
	}


	public static String getCatalogNumberFromSheet(XSSFRow row, int ColumnIndex) throws Exception{
		String CatalogNumber;
		try {
			CatalogNumber=row.getCell(ColumnIndex).getStringCellValue();
		}
		catch(Exception ex) {
			CatalogNumber=null;
		}
		return CatalogNumber;
	}


	public XSSFSheet openWorkbook() throws Exception  {
		XSSFSheet sheet;
		File file;
		FileInputStream  fileIS = null;

		try {
			file = new File(getFileName());
			//if File doesnot found create a fresh excel
			if (!file.exists()) {
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet(getSheetName());
				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);

			}
			else {
				fileIS = new FileInputStream(file);
				workbook = new XSSFWorkbook(fileIS);

				//If sheet not found then create a new sheet
				if (workbook.getSheet(getSheetName())==null){
					sheet = workbook.createSheet(getSheetName());
					FileOutputStream outputStream = new FileOutputStream(file);
					workbook.write(outputStream);
				}
				else {
					sheet = workbook.getSheet(getSheetName());
					eraseAllCellData(sheet);
				}
			}
			System.out.println(workbook);
		}
		catch(Exception ex) {
			sheet=null;
			ex.printStackTrace();
		}
		finally {
			try {
				if (fileIS!=null)
					fileIS.close();
			}
			catch(Exception ex) {
				throw new Exception(ex.getMessage());
			}
		}

		return sheet;
	}

	public void closeWorkbook() throws IOException {
		if (workbook!=null)
		workbook.close();
		workbook=null;

	}

	public void eraseAllCellData(XSSFSheet XlSheet) {
		try {
			int rowCount = XlSheet.getLastRowNum();
			XSSFRow row;
			XSSFCell cell;
			for (int irow=0;irow<=rowCount;irow++) {
				row=XlSheet.getRow(irow);
				if (row!=null) {
					int colnum=row.getLastCellNum();

					for (int icol=0;icol<colnum;icol++) {
						cell=row.getCell(icol);
						if (row!=null) {
							if (cell!=null)
								row.removeCell(cell);
						}
					}
				}
			}	

			FileOutputStream fileOut = new FileOutputStream(getFileName());
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			row=null;
			cell=null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void writeHeader(XSSFSheet XlSheet, String headerList) throws Exception {

		//		headerList="Environment,ProductName,Cat No In GG,Cat No in Mars,Name in GG,Name in Mars,Species In GG,Species In Mars,Status";
		String list_Header[]=headerList.split(",");
		for (int ilist=0;ilist<list_Header.length;ilist++) {
			writeToCell(XlSheet, 0, ilist, list_Header[ilist], CellBackgroundColor.Blue);
		}

	}

	public void writeToCell(XSSFSheet XlSheet, int rowNum, int colNum,String cellValue,CellBackgroundColor cellColor) throws IOException {
		XSSFCellStyle style =workbook.createCellStyle();
		XSSFRow row = XlSheet.getRow(rowNum);
		if (row==null)
			row=XlSheet.createRow(rowNum);
		
		//Write to cell
		XSSFCell cell = row.getCell(colNum);
		if (cell==null) 
			cell=row.createCell(colNum);
		
		cell.setCellValue(cellValue);

		//set cell color
		if (cellColor.equals(CellBackgroundColor.Green)) 
			style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		else if(cellColor.equals(CellBackgroundColor.Red))
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		else if(cellColor.equals(CellBackgroundColor.Yellow))
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		else if(cellColor.equals(CellBackgroundColor.Blue))
			style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());

		if (!cellColor.equals(CellBackgroundColor.NoColor)) {
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		}

		cell=null;
		row=null;
		XlSheet=null;
		
	}

	public void setCellColor(XSSFSheet XlSheet, int rowNum, int colNum,CellBackgroundColor cellColor) throws Exception {
		try {
		XSSFCellStyle style =workbook.createCellStyle();
		XSSFRow row = XlSheet.getRow(rowNum);

		//Write to cell
		XSSFCell cell = row.getCell(colNum);
		if (cell==null) 
			cell=row.createCell(colNum);

		//set cell color
		
		if (cellColor.equals(CellBackgroundColor.Green)) 
			style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		else if(cellColor.equals(CellBackgroundColor.Red))
			style.setFillForegroundColor(IndexedColors.RED.getIndex());
		else if(cellColor.equals(CellBackgroundColor.Yellow))
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());

		if (!cellColor.equals(CellBackgroundColor.NoColor)) {
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		}

		cell=null;
		row=null;
		XlSheet=null;
		}
		catch(Exception ex) {
			ex.getMessage();
		}
	}


	public void saveWorkbook() throws Exception {
		CommonUtilities.configureWait("normalwait", 5);
		FileOutputStream output_file =new FileOutputStream(new File(getFileName()));
		if (workbook!=null)
			workbook.write(output_file);
		output_file.flush();
		output_file.close();
	}

	public long getSize() {
		long size=ObjectSizeCalculator.getObjectSize(workbook);
		return size;
	}

	public void emptyWorkbook() {
		workbook=new XSSFWorkbook();
	}
	
	
	public void writeDataOnCSVatOnce(List<String[]> data) 
	{ 

		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File(csvFileName); 

		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter writer = new CSVWriter(outputfile); 
			writer.writeAll(data); 

			// closing writer connection 
			writer.close(); 
		} 
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 
	
	public void setCSVFileName(String CsvFileName, String SheetName) {
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			csvFileName = System.getProperty("user.dir")+"//target//" + CsvFileName + "_"+SheetName + ".csv";
		}
		else if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			csvFileName = System.getProperty("user.dir")+"\\target\\" + CsvFileName + "_"+SheetName  + ".csv";
		}
		
		System.out.println(csvFileName);
	}
	
	public String[] setCSVHeader( String headerList) throws Exception {

		String[] list_Header=headerList.split(",");
		
		return list_Header;
		

	}

}
